package com.ss.playo.webapp.service.impl;

import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.common.service.AbstractService;
import com.ss.playo.webapp.web.dtos.CourtDTO;
import com.ss.playo.webapp.persistence.dao.ICourtJPADAO;
import com.ss.playo.webapp.persistence.dao.model.Court;
import com.ss.playo.webapp.service.ICourtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CourtServiceImpl extends AbstractService<Court, Long> implements ICourtsService {


    @Autowired
    private ICourtJPADAO dao;

    @Autowired
    IEntityDTOMapper<Court, CourtDTO> courtEntityDTOMapper;

    @Override
    protected ICourtJPADAO getDAO() {
        return dao;
    }

    @Override
    public List<CourtDTO> queryCourtsByDateBySlot(Date bookingDate, String slot) {
        List<Court> courtList = getDAO().queryCourtsByDateBySlot(bookingDate,Integer.parseInt(slot));
       return   courtList.stream().map(courtEntityDTOMapper::fromEntityToDTO).collect(Collectors.toList());

    }

    @Override
    public Optional<Court> findByCourtName(String name) {
        return getDAO().findByName(name);
    }
}
