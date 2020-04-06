package com.ss.playo.webapp.service;

import com.ss.playo.common.service.IService;
import com.ss.playo.webapp.persistence.dao.model.User;
import com.ss.playo.webapp.persistence.dao.model.VerificationToken;

public interface IVerificationTokenService extends IService<VerificationToken, Long> {

    void createVerificationToken(User user, String token);
    VerificationToken findByToken(String token);
}
