package com.isw.ussd.handler.service.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isw.ussd.handler.service.payload.BvnUssdRequest;
import com.isw.ussd.handler.service.payload.BvnUssdResponse;
import com.isw.ussd.handler.service.services.ValidateService;
import com.isw.ussd.handler.service.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/Validate")
@RestControllerAdvice
public class ValidateController {

    @Autowired
    ValidateService validateService;

    @PostMapping("/Bvn")
    public ResponseEntity<BvnUssdResponse> BvnValidator(@Validated @RequestBody BvnUssdRequest bvnUssdRequest) throws JsonProcessingException /*throws JsonProcessingException*/ {
        BvnUssdResponse bvnUssdResponse = new BvnUssdResponse();
        String bvn = bvnUssdRequest.getSessionData().get("bvn");

        if (bvn == null || bvn.isEmpty()) {
            bvnUssdResponse.setSuccess(false);
            bvnUssdResponse.setErrorMessage("Bvn Number is required");
            return ResponseEntity.ok(bvnUssdResponse);
        }
        bvnUssdResponse = validateService.bvnValidate(bvn);

        return ResponseEntity.ok(bvnUssdResponse);
    }
}