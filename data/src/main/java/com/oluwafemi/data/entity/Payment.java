package com.oluwafemi.data.entity;

import lombok.Data;

@Data
public class Payment {

    private String reference;
    private Integer amount;
    private String currency;

}