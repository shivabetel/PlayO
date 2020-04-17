package com.ss.playo.webapp.service.impl;

import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.common.service.AbstractService;
import com.ss.playo.webapp.persistence.dao.ILookupJPADAO;
import com.ss.playo.webapp.persistence.dao.model.Lookup;
import com.ss.playo.webapp.service.ILookupService;
import com.ss.playo.webapp.web.dtos.LookupDTO;

public class LookupServiceImpl extends AbstractService<Lookup, Long> implements ILookupService {

    private ILookupJPADAO lookupJPADAO;


    public LookupServiceImpl(ILookupJPADAO lookupJPADAO) {
        this.lookupJPADAO = lookupJPADAO;
    }

    @Override
    protected IJPADAO<Lookup, Long> getDAO() {
        return lookupJPADAO;
    }
}
