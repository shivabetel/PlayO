package com.ss.playo.webapp.service.impl;

import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.common.service.AbstractService;
import com.ss.playo.webapp.persistence.dao.IRoleJPADAO;
import com.ss.playo.webapp.persistence.dao.model.Role;
import com.ss.playo.webapp.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl extends AbstractService<Role, Long> implements IRoleService {

    private IRoleJPADAO roleJPADAO;

    public RoleServiceImpl(IRoleJPADAO roleJPADAO) {

        this.roleJPADAO = roleJPADAO;
    }

    @Override
    protected IRoleJPADAO getDAO() {
        return roleJPADAO;
    }

    @Override
    public Optional<Role> findByName(String name) {
        return getDAO().findByName(name);
    }
}
