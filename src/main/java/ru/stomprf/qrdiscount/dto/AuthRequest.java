package ru.stomprf.qrdiscount.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequest implements Serializable {
    private String otp;
    private String phoneNum;
}
