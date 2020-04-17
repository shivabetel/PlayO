package com.ss.playo.webapp.web.config;


import com.ss.playo.webapp.persistence.dao.ILookupJPADAO;
import com.ss.playo.webapp.persistence.dao.model.Lookup;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LookupConfigurationProperties implements InitializingBean {

    private Map<String, String> lookup;
    private ILookupJPADAO lookupJPADAO;

    public LookupConfigurationProperties(ILookupJPADAO lookupJPADAO) {
        this.lookupJPADAO = lookupJPADAO;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        lookup = this.getLookupData().get().stream().collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue()));
    }

    private Optional<List<Lookup>> getLookupData() {
        return Optional.ofNullable(lookupJPADAO.findAll());
    }


    public String getLookupValue(String lookupKey) {
        return lookup != null ? lookup.get(lookupKey) : null;
    }
}
