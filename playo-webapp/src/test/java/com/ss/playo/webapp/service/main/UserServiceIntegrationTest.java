package com.ss.playo.webapp.service.main;

import com.ss.playo.common.interfaces.IDto;
import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.common.web.exception.UserAlreadyExistsException;
import com.ss.playo.webapp.persistence.dao.model.User;
import com.ss.playo.webapp.service.IUserService;
import com.ss.playo.webapp.web.dtos.UserDTO;
import com.ss.playo.webapp.web.mappers.UserEntityDTOMapper;
import com.ss.test.common.service.AbstractServiceIntegrationUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;


public class UserServiceIntegrationTest  extends AbstractServiceIntegrationUnitTest<User, String> {

    @Autowired
    IUserService userService;

    @Autowired
    UserEntityDTOMapper userEntityDTOMapper;

    @Test
    void whenRegister_IntegrationTest(){
        UserDTO userDTO = new UserDTO("password", "password", "shiva.betel@gmail.com", "shivakumar", "GM");
         User existingResource =  getApi().register(userDTO);
         Optional<User> retrievedResource =  getApi().findById(userDTO.getEmailId());
         assertThat(existingResource.getEmailId(), comparesEqualTo(retrievedResource.orElseGet(() -> new User()).getEmailId()));
    }

    @Test
    void whenTriesToRegisterWithDupEmailId_throwsExeption(){
        UserDTO userDTO = new UserDTO("password", "password", "shiva.betel@gmail.com", "shivakumar", "GM");
        getApi().register(userDTO);


        assertThrows(Exception.class, () -> {
            getApi().register(userDTO);
        });

    }

    @Test
    void whenFindByEmailIdPerformed(){
        //given
        UserDTO userDTO = new UserDTO("password", "password", "shiva.betel@gmail.com", "shivakumar", "GM");
        getApi().save(userEntityDTOMapper.fromDTOToEntity(userDTO));

        assertNotNull(getApi().findByEmailId(userDTO.getEmailId()).orElseGet(() -> null));
    }

    @Test
    void whenFindByEmailIdEnabledPerformed(){
        UserDTO userDTO = new UserDTO("password", "password", "shiva.betel@gmail.com", "shivakumar", "GM");
        getApi().register(userDTO);
        assertNull(getApi().findByEmailId_Enabled(userDTO.getEmailId()).orElseGet(() -> null));
    }

    protected IUserService getApi(){
        return userService;
    }

    @Override
    protected User createNewEntity(IDto dto){
        UserDTO userDTO = (UserDTO)dto;
        User user = new User();
        user.setEmailId(userDTO.getEmailId());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return user;
    }

    @Override
    protected User createNewEntity() {
        UserDTO userDTO = new UserDTO("password", "password", "shiva.betel@gmail.com", "shivakumar", "GM");
        return createNewEntity(userDTO);
    }
}
