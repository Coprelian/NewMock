package com.example.newMock.Model;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ResponseDTO {

    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private BigDecimal balance;
    private BigDecimal maxLimit;
//    public String getrqUID() {
//        return rqUID;
//    }
//
//    public void String setrqUID(String rqUID) {
//        this.rqUID = rqUID;
//    }
}


//	"rqUID": "58dgtf565j8547f64ke7",
//            "clientId": "3050000000000000000",
//            "account": "30500000000000000001",
//            "currency": "RU",
//            "balance": "16000.00",
//            "maxLimit": "50000.00"