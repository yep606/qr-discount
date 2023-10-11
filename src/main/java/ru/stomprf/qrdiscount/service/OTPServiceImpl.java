package ru.stomprf.qrdiscount.service;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.HOTPGenerator;
import com.bastiaanjansen.otp.SecretGenerator;
import org.springframework.stereotype.Service;

@Service
public class OTPServiceImpl implements OTPService {

    //SHIT
    private byte[] secret = SecretGenerator.generate();
    //SHIT
    private HOTPGenerator hotp = new HOTPGenerator.Builder(secret).build();
    int counter = 5;
    private String code;
    @Override
    public String generateOTP(String phoneNumber) {
        //Testing
        int passwordLength = hotp.getPasswordLength();
        HMACAlgorithm algorithm = hotp.getAlgorithm();
        System.out.println("OTP GENERATE INFO: " + passwordLength + " : " + algorithm);

        code = hotp.generate(counter);

        return code;
    }

    @Override
    public boolean validateOTP(String phoneNumber, String otp) {
        // Database access, counter, etc...
        boolean isValid = hotp.verify(code, counter);
        System.out.println("INSIDE validateOTP: " + isValid + " : " + phoneNumber);

        return isValid;
    }
}
