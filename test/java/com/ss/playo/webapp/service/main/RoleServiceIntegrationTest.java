package com.ss.playo.webapp.service.main;

import com.ss.playo.common.interfaces.IDto;
import com.ss.playo.common.service.IService;
import com.ss.playo.webapp.persistence.dao.model.Privilege;
import com.ss.playo.webapp.persistence.dao.model.Role;
import com.ss.playo.webapp.service.IRoleService;
import com.ss.test.common.service.AbstractServiceIntegrationUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class RoleServiceIntegrationTest extends AbstractServiceIntegrationUnitTest<Role, Long> {

    @Autowired
    IRoleService instance;


    @Test
    void findByName() {
      Role existingResource =   instance.save(createNewEntity());
       Role resourceName = instance.findByName(existingResource.getName()).orElseGet(() -> null);

       assertThat(existingResource, equalTo(resourceName));
    }


    @Override
    protected IService<Role, Long> getApi() {
        return instance;
    }

    @Override
    protected Role createNewEntity() {
        Role role = new Role("ROLE_USER", new HashSet<>(Arrays.asList(new Privilege("CAN_READ", "CAN READ"))));
        return role;
    }

    @Override
    protected Role createNewEntity(IDto dto) {
        return null;
    }
}