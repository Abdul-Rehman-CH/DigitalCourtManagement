package com.court.digitalcourtmanagement.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homecontroller {

    @GetMapping("/")
    public String home() {
        return "Court system is running";
    }

    @GetMapping("/hello")
    public String sayhello() {
        return ("Hello");
    }

}
