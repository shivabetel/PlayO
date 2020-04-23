package com.ss.playo.webapp.web.mappers;

import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.webapp.persistence.dao.model.Privilege;
import com.ss.playo.webapp.persistence.dao.model.Role;
import com.ss.playo.webapp.service.IRoleService;
import com.ss.playo.webapp.web.dtos.UserDTO;
import com.ss.playo.webapp.persistence.dao.model.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Component
public class UserEntityDTOMapper implements IEntityDTOMapper<User, UserDTO> {

    IRoleService roleService;

    public UserEntityDTOMapper(IRoleService roleService) {
        this.roleService = roleService;
    }

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
        user.setRoleSet(new HashSet<Role>(Arrays.asList(createRoleifNotExisting("ROLE_USER"))));
        return user;
    }

    private Role createRoleifNotExisting(String roleName){
       Optional<Role> role =  roleService.findByName(roleName);
       return role.isPresent() ? role.get() : new Role(roleName, new HashSet<Privilege>(Arrays.asList(new Privilege("CAN_READ", "CAN READ"))));

    }
}
