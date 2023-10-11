package ru.stomprf.qrdiscount.service;

public interface OTPService {
    String generateOTP(String phoneNumber);

    boolean validateOTP(String phoneNumber, String otp);

}
