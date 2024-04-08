package com.traderobot.www.impl;


import com.traderobot.www.dbservice.DataService;
import com.traderobot.www.intf.DataBaseService;
import com.traderobot.www.model.Property;
import com.traderobot.www.model.QProperty;
import com.traderobot.www.repository.PropertyRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataBaseServiceImpl implements DataBaseService {

    @Autowired
    DataService dataService;

    @Autowired
    PropertyRepository propertyRepository;

    public String getPropertyByKey(String key, String defaultValue) {
        Property property = dataService.selectFromWhere(QProperty.property, QProperty.class, p -> p.key.eq(key)).fetchFirst();
        if (property != null) {
            return property.getValue();
        } else {
            return StringUtils.isEmpty(defaultValue) ? "" : defaultValue;
        }
    }

    @Override
    public String getPropertyByKey(String key) {
        return getPropertyByKey(key, "");
    }
}
