package com.traderobot.www.view;


import com.traderobot.www.dto.PriceResponse;
import com.traderobot.www.dto.QuikRequest;
import com.traderobot.www.impl.DataBaseServiceImpl;
import com.traderobot.www.intf.ControlConsoleService;
import com.traderobot.www.intf.PriceService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.traderobot.www.dto.Commands.GET_PRICES;
import static com.traderobot.www.model.Property.ACCOUNT;

@Named
@Getter
@Setter
@ViewScoped
@Slf4j
public class ControlPanelView {


    private final DataBaseServiceImpl dataBaseService;

    private final ControlConsoleService controlConsoleService;

    private final PriceService priceService;
    /**
     * Номер торгового счета
     */
    private String account;

    /**
     * Тип опциона
     */
    private String typeOption;

    /**
     * Коды базового актива
     */
    private List<String> baseActiveCodes = new ArrayList<>();


    /**
     * Выбранный код базового актива
     */
    private String baseActiveCodeSelected;

    /**
     * Дата экспирации
     */
    private List<String> expireDates = new ArrayList<>();


    /**
     * Выбранная дата экспирации
     */
    private String expireDateSelected;

    /**
     * Серия опционов
     */
    private List<String> seriesOptions = new ArrayList<>();

    /**
     * Выбранный опцион
     */
    private String optionSelected;


    /**
     * Флаг включения ввода кода базового актива
     */
    private boolean codeBaseDisable = true;

    /**
     * Флаг включения ввода кода базового актива
     */
    private boolean expirationDateDisable = true;

    /**
     * Флаг включения ввода кода выбора опциона
     */
    private boolean seriesOptionsDisable = true;

    private String offer;

    private String theor;

    private String bid;

    /**
     * Стоимость шага цены
     */
    private String stepPrice;

    /**
     * Шаг цены
     */
    private String step="0";


    /**
     * Шаг от теоретической цены минимальный
     */
    private double priceMin;

    /**
     * Шаг от теоретической цены максимальный
     */
    private double priceMax;

    /**
     * Флаг включения изменения шага
     */
    private boolean stepTeorDisable = true;

    /**
     * Цена не выше/ниже которой покупать/продавать
     */
    private String price;

    /**
     * Количество контрактов
     */
    private String contract;

    /**
     * Часть
     */
    private String part;

    @Inject
    public ControlPanelView(DataBaseServiceImpl dataBaseService, ControlConsoleService controlConsoleService, PriceService priceService) {
        this.dataBaseService = dataBaseService;
        this.controlConsoleService = controlConsoleService;
        this.priceService = priceService;
    }

    @PostConstruct
    private void init() {
        this.setAccount(dataBaseService.getPropertyByKey(ACCOUNT));
        this.baseActiveCodes = List.of("");
        this.expireDates.add("");
        this.seriesOptions.add("");
        //this.expireDates.add("25.04.24");
        // this.expireDates=controlConsoleService.getOptionDates();
        // this.expireDateSelected=controlConsoleService.getSelectedOptionDate();

    }


    public void handleChangeTypeOption() {
        this.codeBaseDisable = false;
        if (StringUtils.isNotEmpty(this.baseActiveCodeSelected)) {
            //При изменении типа опциона меняются только серии опционов
            this.seriesOptions.clear();
            this.seriesOptions.add(" ");
            this.seriesOptions.addAll(controlConsoleService.getOptions(baseActiveCodeSelected, typeOption));
        } else {
            this.baseActiveCodeSelected = "";
            this.expireDateSelected = "";
            this.baseActiveCodes = List.of("", "RI", "Si", "SR", "GZ", "BR");
        }

    }


    public void handleChangeCodeBase() {
        if (StringUtils.isNotEmpty(typeOption)) {
            this.expirationDateDisable = false;
            this.expireDates.clear();
            this.seriesOptions.clear();
            this.expireDates.add(" ");
            this.expireDateSelected=" ";
            this.expireDates.addAll(controlConsoleService.getOptionDates(baseActiveCodeSelected, typeOption));
            this.seriesOptions.add(" ");
            this.optionSelected=" ";
            this.seriesOptions.addAll(controlConsoleService.getOptions(baseActiveCodeSelected, typeOption));

        }

    }

    public void handleChangeExpirationDate() {
        if (StringUtils.isNotBlank(expireDateSelected)) {
            this.seriesOptionsDisable = false;
            this.seriesOptions.clear();
            this.seriesOptions.add(" ");
            this.seriesOptions.addAll(controlConsoleService.getOptions(baseActiveCodeSelected, typeOption, expireDateSelected));
        }

    }

    public void handleChangeSeriesOptions() {
        log.info("Выбранный опцион {}", this.optionSelected);
        if (StringUtils.isNotEmpty(this.optionSelected)) {
            this.stepTeorDisable = false;
            checkPrice();
        }
    }

    public void checkPrice() {
        if (StringUtils.isNotBlank(optionSelected)) {

            QuikRequest quikRequest = QuikRequest.newBuilder().addCommand(GET_PRICES.name()).addOptionCode(optionSelected).build();
            PriceResponse priceResponse = priceService.getPrices(quikRequest);
            this.theor = getValue(priceResponse.getTheor());
            this.offer = getValue(priceResponse.getOffer());
            this.bid = getValue(priceResponse.getBid());
            this.stepPrice = getValue(priceResponse.getPriceStep());
            this.step = getValue(priceResponse.getStep());
        } else {
            Random rand = new Random();
            this.theor = "";
            this.offer = "";
            this.bid = "";
            this.stepPrice = "";
            this.step = "";
        }
    }


    public void buy(){
        log.info("Покупка");
    }

    public void sell(){
        log.info("Продажа");
    }

    private String getValue(String price) {
        return StringUtils.isNotBlank(price) ? price : "";

    }
}
