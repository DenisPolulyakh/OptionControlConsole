package com.traderobot.www.intf;

import java.util.List;

public interface ControlConsoleService {
    List<String> getOptionDates(String codeBase, String typeOption);

    List<String> getOptions(String codeBase, String typeOption);

    List<String> getOptions(String codeBase, String typeOption, String expirationDate);

    String getSelectedOptionDate(String codeBase, String typeOption);
}
