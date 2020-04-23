package com.ss.playo.webapp.persistence.dao;

import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.webapp.persistence.dao.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface IUserJPADAO extends IJPADAO<User, String> {

    Optional<User> findByEmailId(String emailId);


    @Query("from User user where user.emailId = :emailId and user.enabled = true")
    Optional<User> findByEmailId_Enabled(String emailId);
}
