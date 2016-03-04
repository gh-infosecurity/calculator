package com.pack.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    private String error;

    public String getError() {
        return error;
    }

    @JsonProperty
    public void setError(String error) {
        this.error = error;
    }
}
