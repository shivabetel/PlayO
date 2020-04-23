package com.ss.playo.webapp.service;

import com.ss.playo.common.service.IService;
import com.ss.playo.webapp.persistence.dao.model.Role;

import java.util.Optional;

public interface IRoleService extends IService<Role, Long> {

    Optional<Role> findByName(String name);
}
