package ru.stomprf.qrdiscount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.stomprf.qrdiscount.model.User;
import ru.stomprf.qrdiscount.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User findUserByPhoneNumber(String phoneNum){
        return userRepo.findUserByPhoneNumber(phoneNum).get();
    }

    public User getAuthenticatedUser(UserDetails userDetails) {
        String phoneNumber = userDetails.getUsername();
        return findUserByPhoneNumber(phoneNumber);
    }
    public User registerUser(User user, String name) {
        if (user.isRegistered())
            return user;
        user.setName(name);
        user.setRegistered(true);
        userRepo.save(user);
        return user;
    }
}
