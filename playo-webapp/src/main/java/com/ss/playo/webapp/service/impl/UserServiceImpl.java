package com.ss.playo.webapp.service.impl;

import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.common.service.AbstractService;
import com.ss.playo.common.web.exception.UserAlreadyExistsException;
import com.ss.playo.webapp.dtos.UserDTO;
import com.ss.playo.webapp.persistence.dao.IUserJPADAO;
import com.ss.playo.webapp.persistence.dao.model.User;
import com.ss.playo.webapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractService<User, String> implements IUserService {

    @Autowired
    IUserJPADAO userJPADAO;

    @Autowired
    IEntityDTOMapper<User, UserDTO> userEntityDTOMapper;

    @Override
    public void register(UserDTO userDTO) throws UserAlreadyExistsException {
        if(userDTO != null){
           Optional<User> user = userJPADAO.findByEmailId(userDTO.getEmailId());
           if(user.isPresent()){
               throw new UserAlreadyExistsException("There is an account already exists with the email adress: " + userDTO.getEmailId());
           }
           else{
               this.save(userEntityDTOMapper.fromDTO(userDTO));
           }
        }
    }

    @Override
    protected IJPADAO<User, String> getDAO() {
        return userJPADAO;
    }
}
