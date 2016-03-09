package com.education.calculator;

import com.education.calculator.request.Request;
import com.education.calculator.request.SingleValueRequest;
import com.education.calculator.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.POST,
        consumes = "application/json",
        produces = "application/json")
public class CalculatorService {

    private CalculatorManager manager;

    @Autowired
    public void setManager(CalculatorManager manager) {
        this.manager = manager;
    }

    @RequestMapping("/sum")
    Response sum(@RequestBody Request request) {
        return manager.sum(request);
    }

    @RequestMapping("/difference")
    Response difference(@RequestBody Request request) {
        return manager.difference(request);
    }

    @RequestMapping("/multiplication")
    Response multiplication(@RequestBody Request request) {
        return manager.multiplication(request);
    }

    @RequestMapping("/division")
    Response division(@RequestBody Request request) {
        return manager.division(request);
    }

    @RequestMapping("/sqrt")
    Response sqrt(@RequestBody SingleValueRequest request) {
        return manager.sqrt(request);
    }

    @RequestMapping("/pow")
    Response sup(@RequestBody SingleValueRequest request) {
        return manager.pow(request);
    }

    @RequestMapping("/operations")
    Response operations() {
        return manager.getOperations();
    }
}