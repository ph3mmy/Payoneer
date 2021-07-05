package com.oluwafemi.domain;

import lombok.Data;

@Data
public class PaymentNetwork implements DomainObject{

    private String code;
    private String method;
    private String label;
    private String logoUrl;

}
