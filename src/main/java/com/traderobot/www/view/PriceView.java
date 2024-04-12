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
import java.util.Random;

import static com.traderobot.www.model.Property.ACCOUNT;

@Named
@Getter
@Setter
@ViewScoped
@Slf4j
public class PriceView {


    /**
     * Теоретическая цена
     */
    private String theor;


    /**
     * Цена предложения
     */
    private String offer;

    /**
     * Цена спроса
     */
    private String bid;

    public void checkPrice(){
        Random rand = new Random();
        this.theor = ""+rand.nextInt();
        this.offer = ""+rand.nextInt();
        this.bid = ""+rand.nextInt();
    }


}
