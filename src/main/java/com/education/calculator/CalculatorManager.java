package com.education.calculator;

import com.education.calculator.dao.OperationDao;
import com.education.calculator.model.Operation;
import com.education.calculator.request.Request;
import com.education.calculator.request.SingleValueRequest;
import com.education.calculator.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class CalculatorManager {
    private OperationDao dao;

    @Autowired
    public void setDao(OperationDao dao) {
        this.dao = dao;
    }

    public Response sum(Request request) {
        Response response = new Response();
        Double sum = request.getFirstValue() + request.getSecondValue();
        String result = getResult(sum);
        response.setResult(result);

        dao.create(getOperation(request, result, "+", true));
        return response;
    }

    public Response difference(Request request) {
        Response response = new Response();
        Double difference = request.getFirstValue() - request.getSecondValue();
        String result = getResult(difference);
        response.setResult(result);

        dao.create(getOperation(request, result, "-", true));
        return response;

    }

    public Response multiplication(Request request) {
        Response response = new Response();
        Double multiplication = request.getFirstValue() * request.getSecondValue();
        String result = getResult(multiplication);
        response.setResult(result);

        dao.create(getOperation(request, result, "*", true));
        return response;
    }

    public Response division(Request request) {
        Response response = new Response();
        Double division = getDivisionValue(request);
        String result = getResult(division);
        response.setResult(result);

        dao.create(getOperation(request, result, "/", true));
        return response;
    }

    private Double getDivisionValue(Request request) {
        Double division;
        if (request.getSecondValue() != 0) {
            division = request.getFirstValue() / request.getSecondValue();
        } else {
            String errorMessage = "Error " + request.getFirstValue() + " / by zero";
            dao.create(getOperation(request, errorMessage, "/", false));
            throw new ArithmeticException(errorMessage);
        }
        return division;
    }

    public Response sqrt(SingleValueRequest request) {
        Response response = new Response();
        double sqrt = Math.sqrt(request.getValue());
        String result = getResult(sqrt);
        response.setResult(result);

        dao.create(getSqrtOperation(request, result, "sqrt", true));
        return response;
    }

    private Operation getSqrtOperation(SingleValueRequest request, String result, String operator, boolean state) {
        StringBuilder expression = new StringBuilder(operator);
        expression.append("(")
                .append(request.getValue())
                .append(")")
                .append(" = ")
                .append(result);
        Operation operation = new Operation();
        operation.setExpression(expression.toString());
        operation.setState(state);
        return operation;
    }

    public Response pow(SingleValueRequest request) {
        Response response = new Response();
        double pow = Math.pow(request.getValue(), 2);
        String result = getResult(pow);
        response.setResult(result);

        dao.create(getSqrtOperation(request, result, "pow 2", true));
        return response;
    }

    public Response getOperations() {
        Response response = new Response();
        List<Operation> operations = dao.getAll();
        Collections.reverse(operations);
        response.setOperations(operations);
        return response;
    }

    private Operation getOperation(Request request, String result, String operator, boolean state) {
        StringBuilder expression = new StringBuilder();
        expression.append(request.getFirstValue())
                .append(operator)
                .append(request.getSecondValue())
                .append(" = ")
                .append(result);
        Operation operation = new Operation();
        operation.setExpression(expression.toString());
        operation.setState(state);
        return operation;
    }

    private String getResult(Double sum) {
        String responseValue;
        if (isInteger(sum)) {
            responseValue = String.valueOf(sum.intValue());
        } else {
            responseValue = String.valueOf(sum);
        }
        return responseValue;
    }

    private boolean isInteger(Double sum) {
        return sum % 1 == 0;
    }
}