package com.ss.playo.webapp.service;

import com.ss.playo.common.service.IService;
import com.ss.playo.webapp.web.dtos.SlotDTO;
import com.ss.playo.webapp.persistence.dao.model.Slot;

import java.util.Date;
import java.util.List;

public interface ISlotService extends IService<Slot, Integer> {

    public List<Slot> querySlotsByDateByCourt(Date date, String courtNo);

    public List<SlotDTO> querySlotsByDateByCourtDTO(Date date, String courtNo);

}
