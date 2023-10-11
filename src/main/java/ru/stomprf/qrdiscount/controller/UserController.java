package ru.stomprf.qrdiscount.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.stomprf.qrdiscount.dto.UserDTO;
import ru.stomprf.qrdiscount.mapper.UserMapper;
import ru.stomprf.qrdiscount.model.User;
import ru.stomprf.qrdiscount.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper mapper;

    @GetMapping("/get")
    public ResponseEntity<UserDTO> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getAuthenticatedUser(userDetails);

        return ResponseEntity.ok(mapper.convertToDTO(user));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@AuthenticationPrincipal UserDetails userDetails,
                                                @RequestBody JsonNode jsonName) {
        User user = userService.getAuthenticatedUser(userDetails);
        String name = jsonName.get("name").asText();
        User registeredUser = userService.registerUser(user, name);
        return ResponseEntity.ok(mapper.convertToDTO(registeredUser));
    }
}
