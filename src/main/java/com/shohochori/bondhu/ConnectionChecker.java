package com.shohochori.bondhu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectionChecker {

    @GetMapping("/")
    public String checkConnection() {
        return "Connection is working";
    }
}
