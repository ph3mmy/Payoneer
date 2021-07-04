package com.oluwafemi.data.entity;

import java.util.List;
import lombok.Data;

@Data
public class Applicable {

    private String recurrence;
    private Boolean redirect;
    private String code;
    private String method;
    private String registration;
    private Links links;
    private String operationType;
    private String label;
    private String grouping;
    private Boolean selected;
    private List<InputElements> inputElements;

}