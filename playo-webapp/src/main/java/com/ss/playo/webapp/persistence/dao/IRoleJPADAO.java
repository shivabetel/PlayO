package com.ss.playo.webapp.persistence.dao;

import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.webapp.persistence.dao.model.Role;

import java.util.Optional;

public interface IRoleJPADAO extends IJPADAO<Role, Long> {

    Optional<Role> findByName(String roleName);

}
