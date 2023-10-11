package ru.stomprf.qrdiscount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stomprf.qrdiscount.dto.AuthRequest;
import ru.stomprf.qrdiscount.dto.AuthResponse;
import ru.stomprf.qrdiscount.dto.OtpResponse;
import ru.stomprf.qrdiscount.service.AuthenticationService;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    @GetMapping ( "/otp/{phoneNum}")
    public ResponseEntity<OtpResponse> getOtp(@PathVariable String phoneNum) {

        return authService.sendOtp(phoneNum);
    }

    @PostMapping ( "/verifyOtp")
    public ResponseEntity<AuthResponse> verifyOtp(@RequestBody AuthRequest authenticationRequest) {
        return authService.verifyOtp(authenticationRequest);
    }
}
