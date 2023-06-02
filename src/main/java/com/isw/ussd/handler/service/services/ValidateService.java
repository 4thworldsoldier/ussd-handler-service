package com.isw.ussd.handler.service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isw.ussd.handler.service.models.Data;
import com.isw.ussd.handler.service.payload.BvnRequest;
import com.isw.ussd.handler.service.payload.BvnResponse;
import com.isw.ussd.handler.service.payload.BvnUssdResponse;
import com.isw.ussd.handler.service.repo.DataRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.util.HashMap;
import java.util.UUID;

@Service
@RestControllerAdvice
public class ValidateService {

    private static final Logger logger = LoggerFactory.getLogger(ValidateService.class);

    @Autowired
    DataRepo dataRepo;

    @Value("${service.url}")
    private String apiURL;

    @Value("${service.clientId}")
    private String clientId;

//

    public BvnUssdResponse bvnValidate(String Bvn) throws JsonProcessingException /*throws JsonProcessingException*/{

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        BvnUssdResponse bvnUssdResponse;
        Data data;

        HttpHeaders headers = new HttpHeaders();
        headers.add("clientId",clientId);
        headers.setContentType(MediaType.APPLICATION_JSON);


        try {
            String ref = randomString();
            BvnRequest bvnRequest = new BvnRequest(Bvn,ref);
            String bvnReq = objectMapper.writeValueAsString(bvnRequest);

            HttpEntity<String> entity = new HttpEntity<>(bvnReq, headers);
            ResponseEntity<BvnResponse> responseEntity = restTemplate.postForEntity(
                    apiURL,entity,BvnResponse.class
            );

            BvnResponse bvnResponse = responseEntity.getBody();
            data = bvnResponse.getData();
            bvnUssdResponse = bvnResponseBuilder(data);

        }

        // are the catch block too much ??
        // should i ditch the exception handler class and just set setErrorMessage and setErrorCode and setSuccess
        //in the catch block??
        catch (ResourceAccessException | HttpClientErrorException e /*| HttpClientErrorException e*/){
            logger.error("An error has occured " + e.getMessage());
            throw e;
        }
        catch ( JsonProcessingException | HttpMessageNotReadableException e){
            logger.error("An error has occured " + e.getMessage());
            throw e;
        }
        catch (Exception  e){
            logger.error("An error has occured " + e.getMessage());
            throw e;
        }

        return bvnUssdResponse;
    }

    public BvnUssdResponse bvnResponseBuilder(Data data){


        BvnUssdResponse bvnUssdResponse = new BvnUssdResponse();
        String dataString = data.toString();
        try {

            //dataRepo.save(data);

            bvnUssdResponse.setSuccess(true);
            bvnUssdResponse.setErrorMessage(null);

            bvnUssdResponse.setSessionData(new HashMap<>());

            bvnUssdResponse.getSessionData().put("data",dataString);
            //bvnUssdResponse.getSessionData().put("firstName",data.getFirstName());
            String[] keyValuePairs = dataString.split(", ");
            //keyValuePairs = null;
            for (String keyValuePair : keyValuePairs) {
                String[] parts = keyValuePair.split("=", 2);
                String key = parts[0].trim();
                String value = parts[1].trim();
                //sessionData.put(key, value);
                bvnUssdResponse.getSessionData().put(key, value);

                logger.info("value" + value);
                //logger.info("value{0}",value);

            }
        }
        catch (NullPointerException e){
            logger.error("An error has occured " + e.getMessage());
            throw e;
        }
        catch ( IndexOutOfBoundsException e){
            logger.error("An error has occured " + e.getMessage());
            throw e;
        }
        catch (Exception e){
           //bvnUssdResponse.setErrorMessage(e.getMessage());
           //bvnUssdResponse.setSuccess(false);
            logger.error("An error has occured " + e.getMessage());
            throw e;
        }


        return bvnUssdResponse;
    }

    private String randomString(){
        return UUID.randomUUID().toString();
    }
}
