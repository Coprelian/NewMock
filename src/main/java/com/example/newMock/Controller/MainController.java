package com.example.newMock.Controller;

import com.example.newMock.Model.RequestDTO;
import com.example.newMock.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

@RestController
public class MainController {

    private Logger log = LoggerFactory.getLogger(MainController.class);

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "/info/postBalancees",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO){
        try {
            String clientId = requestDTO.getClientId();
            char firstDigit = clientId.charAt(0);
            BigDecimal maxLimit;
            Random random = new Random();

            ResponseDTO responseDTO = new ResponseDTO();

            if (firstDigit == '8') {
                maxLimit = new BigDecimal(2000);
                responseDTO.setCurrency("US");
                responseDTO.setBalance(BigDecimal.valueOf(random.nextDouble(maxLimit.doubleValue())));
            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000);
                responseDTO.setCurrency("EU");
                responseDTO.setBalance(BigDecimal.valueOf(random.nextDouble(maxLimit.doubleValue())));
            } else {
                responseDTO.setCurrency("RU");
                maxLimit = new BigDecimal(50000);
                responseDTO.setBalance(BigDecimal.valueOf(random.nextDouble(maxLimit.doubleValue())));
            }

            //ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setRqUID(requestDTO.getRqUID());
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            //responseDTO.setCurrency("RU");
            //responseDTO.setBalance(new BigDecimal(777));
            responseDTO.setMaxLimit(maxLimit);

            log.error("************** RequestDTO ***********************" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("************** ResponseDTO ***********************" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString((responseDTO)));

            return responseDTO;
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
