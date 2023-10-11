package ru.stomprf.qrdiscount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.stomprf.qrdiscount.dto.AuthRequest;
import ru.stomprf.qrdiscount.dto.AuthResponse;
import ru.stomprf.qrdiscount.dto.OtpResponse;
import ru.stomprf.qrdiscount.model.User;
import ru.stomprf.qrdiscount.util.JWTUtil;

@Service
public class AuthenticationService {

    @Autowired
    private OTPService otpService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    public ResponseEntity<OtpResponse> sendOtp(String phoneNum) {

        try {
            String otp = otpService.generateOTP(phoneNum);
            System.out.println("OTP: " + otp);
            return ResponseEntity.ok(new OtpResponse("Otp sent successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new OtpResponse(e.getMessage()));
        }
    }

    public ResponseEntity<AuthResponse> verifyOtp(AuthRequest authRequest) {
        AuthResponse authResponse;
        try {
            if (otpService.validateOTP(authRequest.getPhoneNum(), authRequest.getOtp())) {
                authResponse = createJwt(authRequest);
//                otpService.clearOtp(authenticationRequest.getPhoneNo()); CHECK THIS!
            } else {
                authResponse = new AuthResponse("Otp is either expired or incorrect");
            }
            return ResponseEntity.ok(authResponse);

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthResponse(e.getMessage()));
        }
    }

    public AuthResponse createJwt(AuthRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getPhoneNum(), "")
            );

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getPhoneNum());
        String jwt = jwtTokenUtil.generateToken(userDetails);
        User user = userService.findUserByPhoneNumber(authenticationRequest.getPhoneNum());
        return new AuthResponse("Otp verified successfully", jwt, user.isRegistered());
    }
}
