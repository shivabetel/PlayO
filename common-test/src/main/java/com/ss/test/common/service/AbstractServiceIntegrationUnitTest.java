package com.ss.test.common.service;

import com.ss.playo.common.interfaces.IDto;
import com.ss.playo.common.interfaces.IEntity;
import com.ss.playo.common.service.IService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:persistence-test.properties")
@Transactional
public abstract class AbstractServiceIntegrationUnitTest<T extends IEntity, E extends Serializable> {

    @Test
    /**/
    public final void givenResourceExists_whenResourceIsRetrievedById_thenResourceIsFound() {
        // Given
        final T existingResource = getApi().save(createNewEntity());

        // When
       final T resourceName =  getApi().findById((E)existingResource.getId()).orElseGet(() -> null);

       //then
        assertNotNull(resourceName);
    }

    @Test
    public final void givenResourceExists_whenResourceIsRetrievedByName_thenFoundResourceIsCorrect(){
        final T existingResource = getApi().save(createNewEntity());

        // When
        final T resourceName =  getApi().findById((E)existingResource.getId()).orElseGet(() -> null);

        assertThat(existingResource, equalTo(resourceName));

    }

    protected abstract IService<T, E> getApi();
    protected abstract T createNewEntity();
    protected abstract T createNewEntity(IDto dto);


}
