package com.education.calculator.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleValueRequest {

    private Double value;

    public Double getValue() {
        return value;
    }

    @JsonProperty
    public void setValue(Double value) {
        this.value = value;
    }
}