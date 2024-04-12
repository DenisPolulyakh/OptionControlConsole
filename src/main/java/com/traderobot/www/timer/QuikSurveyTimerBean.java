package com.traderobot.www.timer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class QuikSurveyTimerBean {



    public void checkOffersAsks() {
        log.info("[checkOffersAsks] Started {}", LocalDateTime.now());

    }

}
