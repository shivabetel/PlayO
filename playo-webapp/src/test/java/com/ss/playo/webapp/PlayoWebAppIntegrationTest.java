package com.ss.playo.webapp;


import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.webapp.persistence.dao.model.User;
import com.ss.playo.webapp.service.IUserService;
import com.ss.playo.webapp.web.dtos.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PlayoWebAppIntegrationTest {

    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @Autowired
    private IEntityDTOMapper<User, UserDTO> userDTOIEntityDTOMapper;



    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    void setUp() {
         this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Disabled
    public void testUserIdAvailablity() throws Exception{
        mockMvc.perform(get("/api/user/checkAvailability?emailId=shiva.betel@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.available").value(false));
    }

    @Test
    @Disabled
    public void testWhenNewUserWithUserIdAlreadyExists_throwsException(){
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setFirstName("shiva");
            userDTO.setLastName("GM");
            userDTO.setEmailId("shiva1.betel@gmail.com");
            userDTO.setPassword("password");
            userDTO.setConfirmPassword("password");
//        userService.register(userDTO);

            when(userService.register(userDTO)).thenReturn(userDTOIEntityDTOMapper.fromDTOToEntity(userDTO));
            mockMvc.perform(post("/api/user", userDTO))
                    .andDo(print())
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
            fail();

        }

    }
}
