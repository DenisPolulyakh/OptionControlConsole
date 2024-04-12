package com.traderobot.www.intf;


import com.traderobot.www.dto.PriceRequest;
import com.traderobot.www.dto.PriceResponse;
import com.traderobot.www.dto.QuikRequest;

import java.util.List;

public interface PriceService {



    /**
     * Получение параметров опроса цены
     */
    PriceResponse getPrices(QuikRequest quikRequest);

}
