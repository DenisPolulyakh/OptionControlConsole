package com.traderobot.www.intf;


import com.traderobot.www.dto.QuikRequest;
import com.traderobot.www.dto.QuikResponse;

public interface SocketGateway {

        QuikResponse send(QuikRequest quikRequest);
}
