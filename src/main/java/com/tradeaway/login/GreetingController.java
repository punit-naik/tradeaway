package com.tradeaway.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/welcome")
    public String welcome() {//Welcome page, non-rest
        return "Welcome to Tradeaway.";
    }


}
