package com.ss.playo.webapp.persistence.dao;

import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.webapp.persistence.dao.model.VerificationToken;
import org.springframework.data.jpa.repository.Query;

public interface IVerificationJPADAO extends IJPADAO<VerificationToken,Long> {
    @Query("select verificationToken from VerificationToken verificationToken join fetch verificationToken.user where verificationToken.token =:token")
    VerificationToken findByToken(String token);
}
