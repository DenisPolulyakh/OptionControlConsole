package com.traderobot.www.view;


import com.traderobot.www.impl.DataBaseServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import static com.traderobot.www.model.Property.ACCOUNT;

@Named
@Getter
@Setter
@ViewScoped
public class ControlPanelView {

  @Autowired
  DataBaseServiceImpl dataBaseService;

  private String account;

  @PostConstruct
  private void setValue() {

    this.setAccount(dataBaseService.getPropertyByKey(ACCOUNT));
  }

}
