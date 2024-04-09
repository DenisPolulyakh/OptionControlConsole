package com.traderobot.www.impl;

import com.traderobot.www.dto.OptionSecurity;
import com.traderobot.www.intf.ControlConsoleService;
import com.traderobot.www.intf.OptionCache;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Service
public class ControlConsoleServiceImpl implements ControlConsoleService {

    private List<OptionSecurity> optionSecurities = new ArrayList<>();

    Map<LocalDate, List<String>> sortedOptions = new TreeMap<>();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final List<String> optionDates = new ArrayList<>();

    private final List<String> options = new ArrayList<>();

    private String selectedOptionDate;

    private final OptionCache optionCache;

    public ControlConsoleServiceImpl(OptionCache optionCache) {
        this.optionCache = optionCache;
    }

    public List<String> getOptionDates(String codeBase, String typeOption) {
        pullSelectOptions(codeBase,typeOption);
        return optionDates;
    }

    public List<String> getOptions(String codeBase, String typeOption) {
        return options;
    }

    public String getSelectedOptionDate(String codeBase, String typeOption) {
        return selectedOptionDate;
    }

    private void pullSelectOptions(String codeBase, String typeOption) {
        optionDates.clear();
        options.clear();
        optionSecurities = optionCache.getOptions();
        Map<LocalDate, List<String>> mapOptionDate = optionSecurities.stream().filter(o -> o.getSecCode().substring(0, 2).equalsIgnoreCase(codeBase) && o.getOptiontype().equalsIgnoreCase(typeOption)).collect(Collectors.groupingBy(OptionSecurity::getDateExpire, mapping(OptionSecurity::getSecCode, toList())));
        sortedOptions.clear();
        sortedOptions.putAll(mapOptionDate);
        List<String> dates = new ArrayList<>();
        for (LocalDate date : sortedOptions.keySet()) {
            dates.add(date.format(formatter));
        }
        if (sortedOptions.size() > 0) {
            LocalDate firstLocalDate = sortedOptions.keySet().stream().findFirst().get();
            selectedOptionDate = firstLocalDate.format(formatter);
            optionDates.addAll(dates);
            options.addAll(sortedOptions.get(firstLocalDate));
        }
    }
}
