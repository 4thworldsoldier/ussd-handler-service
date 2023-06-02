package com.isw.ussd.handler.service.payload;

import com.isw.ussd.handler.service.models.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class BvnUssdResponse {

    public BvnUssdResponse(String errorMessage,String errorCode, boolean success ){
        super();
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.success = success;
    }

    public BvnUssdResponse() {
        this.sessionData = new HashMap<>();
    }
    //private String id;
    //private String msisdn;
    //private String sessionId;
    //private String query;
    //private String input;
    private Map<String, String> sessionData;

    private boolean success;
    private String errorCode;
    private String errorMessage;

   // public void setSessionData(String sd, String we) {
    //}
}
