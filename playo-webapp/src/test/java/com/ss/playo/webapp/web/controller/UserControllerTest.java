package com.ss.playo.webapp.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.playo.webapp.service.IUserService;
import com.ss.playo.webapp.web.dtos.UserDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:persistence-test.properties")
public class UserControllerTest{

    @Autowired
    MockMvc mockMvc;

    @Autowired
    IUserService userService;


    private ObjectMapper mapper = new ObjectMapper();


    @Test
    @Disabled
    void register() throws Exception {

        UserDTO userDTO = new UserDTO("password", "password", "shiva.betel@gmail.com", "shivakumar", "GM");

      mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                             .content(toJson(userDTO))
                             .contentType(MediaType.APPLICATION_JSON)
                             .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Disabled
    void validateUser() {
    }

    <T> T fromJsonResult(MvcResult result,
                         Class<T> tClass) throws Exception {
        return this.mapper.readValue(
                result.getResponse().getContentAsString(),
                tClass);
    }
    private byte[] toJson(Object object) throws Exception {
        return this.mapper
                .writeValueAsString(object).getBytes();
    }


}