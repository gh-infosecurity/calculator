package com.education.calculator.response;

import com.education.calculator.model.Operation;

import java.util.List;

public class Response {
    private String result;
    private List<Operation> operations;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
