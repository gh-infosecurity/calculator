package com.education.calculator.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

    private Double firstValue;
    private Double secondValue;

    public Double getFirstValue() {
        return firstValue;
    }

    @JsonProperty(value = "first")
    public void setFirstValue(Double firstValue) {
        this.firstValue = firstValue;
    }

    public Double getSecondValue() {
        return secondValue;
    }

    @JsonProperty(value = "second")
    public void setSecondValue(Double secondValue) {
        this.secondValue = secondValue;
    }
}