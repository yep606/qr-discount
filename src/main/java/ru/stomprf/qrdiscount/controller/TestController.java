package ru.stomprf.qrdiscount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stomprf.qrdiscount.repo.UserRepo;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/hello")
    public String home() {
        return "Hello from test controller";
    }
}
