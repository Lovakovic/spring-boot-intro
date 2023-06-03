package hr.tvz.lovakovic.studapp.mapper;

import hr.tvz.lovakovic.studapp.model.User;
import hr.tvz.lovakovic.studapp.model.UserDTO;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        UserDTO userDTO= new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setRole(String.valueOf(user.getRole()));

        return userDTO;
    }
}
