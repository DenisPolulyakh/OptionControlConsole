package com.traderobot.www.view;


import com.traderobot.www.impl.DataBaseServiceImpl;
import com.traderobot.www.intf.ControlConsoleService;
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

import static com.traderobot.www.model.Property.ACCOUNT;

@Named
@Getter
@Setter
@ViewScoped
@Slf4j
public class ControlPanelView {


    private final DataBaseServiceImpl dataBaseService;

    private final ControlConsoleService controlConsoleService;
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

    private String offerQuik;

    private String theorQuik;

    private String offerQuik;



    /**
     * Шаг от теоретической цены
     */
    private int stepTeor;

    /**
     * Флаг включения изменения шага
     */
    private boolean stepTeorDisable = true;

    @Inject
    public ControlPanelView(DataBaseServiceImpl dataBaseService, ControlConsoleService controlConsoleService) {
        this.dataBaseService = dataBaseService;
        this.controlConsoleService = controlConsoleService;
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
        if(StringUtils.isNotEmpty(this.baseActiveCodeSelected)){
            //При изменении типа опциона меняются только серии опционов
            this.seriesOptions.clear();
            this.seriesOptions.add(" ");
            this.seriesOptions.addAll(controlConsoleService.getOptions(baseActiveCodeSelected, typeOption));
        }else {
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
            this.expireDates.addAll(controlConsoleService.getOptionDates(baseActiveCodeSelected, typeOption));
            this.seriesOptions.add(" ");
            this.seriesOptions.addAll(controlConsoleService.getOptions(baseActiveCodeSelected, typeOption));
            this.seriesOptionsDisable = false;
        }

    }

    public void handleChangeExpirationDate() {
        if (StringUtils.isNotEmpty(expireDateSelected)) {
            this.seriesOptionsDisable = false;
            if (seriesOptions.isEmpty()) {
                this.seriesOptions.add(" ");
                this.seriesOptions.addAll(controlConsoleService.getOptions(baseActiveCodeSelected, typeOption));
            }
        }

    }

    public void handleChangeSeriesOptions() {
        log.info("Выбранный опцион {}", this.optionSelected);
        if (StringUtils.isNotEmpty(this.optionSelected)) {
            this.stepTeorDisable = false;
        }
    }
}
