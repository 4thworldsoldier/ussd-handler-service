package com.isw.ussd.handler.service.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class BvnUssdRequest {

    public BvnUssdRequest() {
        this.sessionData = new HashMap<>();
    }
    //private String id;
    //private String msisdn;
    //private String sessionId;
    //private String query;
    //private String input;
    private Map<String, String> sessionData;

    //private boolean success;
    //private String errorCode;
    //private String errorMessage;

}
