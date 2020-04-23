package com.ss.playo.webapp.service.impl;

import com.ss.playo.common.service.AbstractService;
import com.ss.playo.webapp.web.dtos.SlotDTO;
import com.ss.playo.webapp.persistence.dao.ISlotJPADAO;
import com.ss.playo.webapp.persistence.dao.model.Slot;
import com.ss.playo.webapp.service.ISlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SlotServiceImpl extends AbstractService<Slot, Integer> implements ISlotService {

    private ISlotJPADAO dao;

    @Override
    protected ISlotJPADAO getDAO() {
        return dao;
    }

    public SlotServiceImpl(ISlotJPADAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Slot> querySlotsByDateByCourt(Date date, String courtNo) {

        return getDAO().querySlotsByDateByCourt(date, courtNo);
    }

    @Override
    public List<SlotDTO> querySlotsByDateByCourtDTO(Date date, String courtNo) {
        return getDAO().querySlotsByDateByCourtDTO(date, courtNo);
    }

}
