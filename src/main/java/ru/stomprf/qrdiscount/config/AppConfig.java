package ru.stomprf.qrdiscount.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.stomprf.qrdiscount.model.Discount;
import ru.stomprf.qrdiscount.model.Role;
import ru.stomprf.qrdiscount.model.User;
import ru.stomprf.qrdiscount.repo.UserRepo;

import java.util.Optional;

@Configuration
public class AppConfig {

    @Autowired
    private UserRepo userRepo;

    @Bean
    public UserDetailsService userDetailsService() {
        return phoneNumber -> {
            Optional<User> dbUser = userRepo.findUserByPhoneNumber(phoneNumber);
            if (dbUser.isEmpty()) {

                User user = new User();
                user.setPhoneNumber(phoneNumber);
                user.setRole(Role.USER);
                user.setRegistered(false);
                user.setDiscount(Discount.FIVE);

                userRepo.save(user);

                return new org.springframework.security.core.userdetails.User(phoneNumber,
                        "", user.getAuthorities());
            }
            return new org.springframework.security.core.userdetails.User(dbUser.get().getPhoneNumber(),
                    dbUser.get().getPassword(), dbUser.get().getAuthorities());
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
