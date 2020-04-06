package com.ss.playo.webapp.service.impl;

import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.common.service.AbstractService;
import com.ss.playo.webapp.persistence.dao.IVerificationJPADAO;
import com.ss.playo.webapp.persistence.dao.model.User;
import com.ss.playo.webapp.persistence.dao.model.VerificationToken;
import com.ss.playo.webapp.service.IVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl extends AbstractService<VerificationToken, Long> implements IVerificationTokenService {


    @Autowired
    IVerificationJPADAO dao;

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        this.save(verificationToken);
    }

    @Override
    public VerificationToken findByToken(String token) {
        return dao.findByToken(token);
    }

    @Override
    protected IJPADAO<VerificationToken, Long> getDAO() {
        return dao;
    }
}
