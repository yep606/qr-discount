package ru.stomprf.qrdiscount.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.stomprf.qrdiscount.dto.UserDTO;
import ru.stomprf.qrdiscount.model.User;

@Component
public class UserMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}

