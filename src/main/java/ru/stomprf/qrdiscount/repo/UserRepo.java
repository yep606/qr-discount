package ru.stomprf.qrdiscount.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stomprf.qrdiscount.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findUserByPhoneNumber(String phoneNumber);

}
