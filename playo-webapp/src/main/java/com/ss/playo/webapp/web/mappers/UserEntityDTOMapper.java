package com.ss.playo.webapp.web.mappers;

import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.webapp.web.dtos.UserDTO;
import com.ss.playo.webapp.persistence.dao.model.User;
import org.springframework.stereotype.Component;


@Component
public class UserEntityDTOMapper implements IEntityDTOMapper<User, UserDTO> {

    @Override
    public UserDTO fromEntityToDTO(User entity) {
        return null;
    }

    @Override
    public User fromDTOToEntity(UserDTO dto) {
        User user = new User();
        user.setEmailId(dto.getEmailId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        return user;
    }
}
