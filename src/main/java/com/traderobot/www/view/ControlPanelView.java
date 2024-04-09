package com.traderobot.www.view;


import com.traderobot.www.impl.DataBaseServiceImpl;
import com.traderobot.www.intf.ControlConsoleService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.traderobot.www.model.Property.ACCOUNT;

@Named
@Getter
@Setter
@ViewScoped
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
     * Флаг включения ввода кода базового актива
     */
    private boolean codeBaseDisable = true;

    /**
     * Флаг включения ввода кода базового актива
     */
    private boolean expirationDateDisable = true;

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
        //this.expireDates.add("25.04.24");
        // this.expireDates=controlConsoleService.getOptionDates();
        // this.expireDateSelected=controlConsoleService.getSelectedOptionDate();

    }


    public void handleChangeTypeOption() {
        this.codeBaseDisable = false;
        this.baseActiveCodeSelected="";
        this.expireDateSelected="";
        this.baseActiveCodes = List.of("","RI", "Si", "SR", "GZ", "BR");
    }


    public void handleChangeCodeBase() {
        if (StringUtils.isNotEmpty(typeOption)) {
            this.expirationDateDisable = false;
            this.expireDates.clear();
            this.expireDates.add(" ");
            this.expireDates.addAll(controlConsoleService.getOptionDates(baseActiveCodeSelected, typeOption));
        }
    }
}
