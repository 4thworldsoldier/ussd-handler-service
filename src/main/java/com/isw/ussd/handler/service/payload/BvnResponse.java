package com.isw.ussd.handler.service.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isw.ussd.handler.service.models.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BvnResponse {
    //@JsonProperty("httpHeaders")
    //private HttpHeaders httpHeaders;
    @JsonProperty("httpStatusCode")
    private int httpStatusCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Data data;

    @JsonProperty("otherParams")
    private Object otherParams;
}

