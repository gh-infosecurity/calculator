package com.education.calculator;

import com.education.calculator.request.Request;
import com.education.calculator.response.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.POST,
        consumes = "application/json",
        produces = "application/json")
public class CalculatorService {

    @RequestMapping("/sum")
    Response sum(@RequestBody Request request) {
        Response response = new Response();
        Double sum = request.getFirstValue() + request.getSecondValue();
        response.setResult(getResult(sum));
        return response;
    }

    @RequestMapping("/difference")
    Response difference(@RequestBody Request request) {
        Response response = new Response();
        Double difference = request.getFirstValue() - request.getSecondValue();
        response.setResult(getResult(difference));
        return response;
    }

    @RequestMapping("/multiplication")
    Response multiplication(@RequestBody Request request) {
        Response response = new Response();
        Double multiplication = request.getFirstValue() * request.getSecondValue();
        response.setResult(getResult(multiplication));
        return response;
    }

    @RequestMapping("/division")
    Response division(@RequestBody Request request) {
        Response response = new Response();
        Double division = getDivisionValue(request.getFirstValue(), request.getSecondValue());
        response.setResult(getResult(division));
        return response;
    }

    private Double getDivisionValue(Double firstValue, Double secondValue) {
        Double division;
        if (secondValue != 0) {
            division = firstValue / secondValue;
        } else {
            throw new ArithmeticException("Error " + firstValue + " / by zero");
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