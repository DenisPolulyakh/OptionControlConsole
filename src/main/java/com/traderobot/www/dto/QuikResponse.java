package com.traderobot.www.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuikResponse {
    private String payload;
    private String errorText;
    private boolean success;

    public static QuikResponse success(String payload) {
        return new QuikResponse(payload,"", true);
    }

    public static QuikResponse fail(String errorText) {
        return new QuikResponse("", errorText, true);
    }
}
