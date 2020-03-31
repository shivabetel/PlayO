package com.ss.playo.webapp.mappers;

import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.webapp.dtos.UserDTO;
import com.ss.playo.webapp.persistence.dao.model.User;
import org.springframework.stereotype.Component;


@Component
public class UserEntityDTOMapper implements IEntityDTOMapper<User, UserDTO> {

    @Override
    public UserDTO fromEntity(User entity) {
        return null;
    }

    @Override
    public User fromDTO(UserDTO dto) {
        User user = new User();
        user.setEmailId(dto.getEmailId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        return user;
    }
}
