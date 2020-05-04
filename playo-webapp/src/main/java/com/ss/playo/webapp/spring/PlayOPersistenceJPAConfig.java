package com.ss.playo.webapp.spring;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.ss.playo.webapp.persistence"})
@PropertySource("classpath:mysql-properties-${persistenceTarget:dev}.properties")
@EnableJpaRepositories(basePackages = {"com.ss.playo.webapp.persistence.dao"})
@EntityScan(basePackages = {"com.ss.playo.webapp.persistence.dao.model"})
public class PlayOPersistenceJPAConfig {

    public PlayOPersistenceJPAConfig() {
        super();
    }

}
