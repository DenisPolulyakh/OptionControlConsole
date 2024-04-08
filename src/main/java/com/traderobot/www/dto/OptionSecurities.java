package com.traderobot.www.dto;

import java.util.List;

public class OptionSecurities {
    List<OptionSecurity> optionSecurityList;

    public List<OptionSecurity> getOptionList() {
        return optionSecurityList;
    }

    public void setOptionList(List<OptionSecurity> optionSecurityList) {
        this.optionSecurityList = optionSecurityList;
    }
}
