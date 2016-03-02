package com.pack;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorService {

    @RequestMapping("/home")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
