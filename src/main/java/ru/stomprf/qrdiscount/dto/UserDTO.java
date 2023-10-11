package ru.stomprf.qrdiscount.dto;

import lombok.Data;
import ru.stomprf.qrdiscount.model.Discount;
import ru.stomprf.qrdiscount.model.Role;

@Data
public class UserDTO {
    private Long id;
    private String phoneNumber;
    private String name;
    private Role role;
    private Discount discount;
    private boolean isRegistered;
}
