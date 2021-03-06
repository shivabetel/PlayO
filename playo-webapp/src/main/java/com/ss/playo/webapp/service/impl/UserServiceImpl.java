package com.ss.playo.webapp.service.impl;

import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.common.service.AbstractService;
import com.ss.playo.common.web.exception.UserAlreadyExistsException;
import com.ss.playo.webapp.web.dtos.UserDTO;
import com.ss.playo.webapp.persistence.dao.IUserJPADAO;
import com.ss.playo.webapp.persistence.dao.model.User;
import com.ss.playo.webapp.persistence.dao.model.VerificationToken;
import com.ss.playo.webapp.service.IUserService;
import com.ss.playo.webapp.service.IVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Calendar;
import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractService<User, String> implements IUserService {


    IUserJPADAO userJPADAO;


    IEntityDTOMapper<User, UserDTO> dtoMapper;


    IVerificationTokenService verificationTokenService;

    public UserServiceImpl(IUserJPADAO userJPADAO, IEntityDTOMapper<User, UserDTO> dtoMapper, IVerificationTokenService verificationTokenService) {
        this.userJPADAO = userJPADAO;
        this.dtoMapper = dtoMapper;
        this.verificationTokenService = verificationTokenService;
    }

    @Override
    public User register(UserDTO userDTO) {
        if(userDTO != null){
           Optional<User> user = getDAO().findByEmailId(userDTO.getEmailId());
           if(user.isPresent()){
               throw new UserAlreadyExistsException("There is an account already exists with the email adress: " + userDTO.getEmailId());
           }
           else{
               return this.save(dtoMapper.fromDTOToEntity(userDTO));


           }
        }
        return null;
    }

    @Override
    public void verifyRegistration(String token) {

        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if(verificationToken != null){
            Calendar calendar = Calendar.getInstance();
            if(verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime() <=0){
                throw new ValidationException("Token expired");
            }
            else{
                User user = verificationToken.getUser();
                user.setEnabled(true);
                getDAO().save(user);
            }
        }else{
            throw new ValidationException("Not a valid token");
        }

    }

    @Override
    public Optional<User> findByEmailId_Enabled(String emailId) {
        return getDAO().findByEmailId_Enabled(emailId);
    }

    @Override
    public Optional<User> findByEmailId(String emailId) {
        return getDAO().findById(emailId);
    }

    @Override
    protected IUserJPADAO getDAO() {
        return userJPADAO;
    }
}
