package com.education.calculator;

import com.education.calculator.dao.OperationDao;
import com.education.calculator.model.Operation;
import com.education.calculator.request.Request;
import com.education.calculator.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.POST,
        consumes = "application/json",
        produces = "application/json")
public class CalculatorService {

    @Autowired
    private OperationDao dao;

    @RequestMapping("/sum")
    Response sum(@RequestBody Request request) {
        Response response = new Response();
        Double sum = request.getFirstValue() + request.getSecondValue();
        String result = getResult(sum);
        response.setResult(result);

        dao.create(getOperation(request, result, "+", true));
        return response;
    }

    @RequestMapping("/difference")
    Response difference(@RequestBody Request request) {
        Response response = new Response();
        Double difference = request.getFirstValue() - request.getSecondValue();
        String result = getResult(difference);
        response.setResult(result);

        dao.create(getOperation(request, result, "-", true));
        return response;
    }

    @RequestMapping("/multiplication")
    Response multiplication(@RequestBody Request request) {
        Response response = new Response();
        Double multiplication = request.getFirstValue() * request.getSecondValue();
        String result = getResult(multiplication);
        response.setResult(result);

        dao.create(getOperation(request, result, "*", true));
        return response;
    }

    @RequestMapping("/division")
    Response division(@RequestBody Request request) {
        Response response = new Response();
        Double division = getDivisionValue(request);
        String result = getResult(division);
        response.setResult(result);

        dao.create(getOperation(request, result, "/", true));
        return response;
    }

    @RequestMapping("/operations")
    Response operations() {
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