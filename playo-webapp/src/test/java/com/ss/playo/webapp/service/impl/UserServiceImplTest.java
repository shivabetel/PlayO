package com.ss.playo.webapp.service.impl;

import com.ss.playo.common.interfaces.IDto;
import com.ss.playo.common.interfaces.IEntity;
import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.common.service.IService;
import com.ss.playo.common.web.exception.UserAlreadyExistsException;
import com.ss.playo.webapp.persistence.dao.IUserJPADAO;
import com.ss.playo.webapp.persistence.dao.model.User;
import com.ss.playo.webapp.service.IUserService;
import com.ss.playo.webapp.web.dtos.UserDTO;
import com.ss.playo.webapp.web.mappers.UserEntityDTOMapper;
import com.ss.test.common.service.AbstractServiceUnitTest;
import javafx.beans.binding.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.Serializable;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest extends AbstractServiceUnitTest<User, String> {

    UserServiceImpl instance;

    @MockBean
    IUserJPADAO userJPADAO;

    @MockBean
    IEntityDTOMapper<User, UserDTO> dtoMapper;


    @BeforeEach
    void setUp() {
        instance = new UserServiceImpl(userJPADAO, dtoMapper, null);
        //userJPADAO = Mockito.mock(IUserJPADAO.class);

    }

    @Test
    void whenRegisterIsCalled() throws Exception{
        //given
        UserDTO userDTO = new UserDTO("password", "password", "shiva.betel@gmail.com", "shivakumar", "GM");
        User user = createNewEntity(userDTO);//dtoMapper.fromDTOToEntity(userDTO);


       when(dtoMapper.fromDTOToEntity(userDTO)).thenReturn(user);
        when(getDAO().findByEmailId(userDTO.getEmailId())).thenReturn(Optional.empty());
        when(getDAO().save(user)).thenReturn(user);

        //when
        User registerUser =instance.register(userDTO);

        //then
        assertEquals(user, registerUser);
    }


    @Test
    void findByEmailId(){
        //given
        String emailId = "shiva.betel@gmail.com";
        when(getDAO().findByEmailId(emailId)).thenReturn(Optional.of(new User()));

        //when and then
        assertNotNull(instance.findByEmailId(emailId).orElseGet(() -> null));

    }
    @Override
    public IService<User, String> getApi() {
        return instance;
    }

    @Override
    protected IUserJPADAO getDAO() {
        return userJPADAO;
    }

    @Override
    protected User createNewEntity(IDto dto) {
        UserDTO userDTO = (UserDTO)dto;
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmailId(userDTO.getEmailId());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}