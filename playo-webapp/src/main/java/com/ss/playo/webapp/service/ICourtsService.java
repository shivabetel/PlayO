package com.ss.playo.webapp.service;

import com.ss.playo.common.service.IService;
import com.ss.playo.webapp.dtos.CourtDTO;
import com.ss.playo.webapp.persistence.dao.model.Court;

import java.util.Date;
import java.util.List;

public interface ICourtsService extends IService<Court, Long> {

    public List<CourtDTO> queryCourtsByDateBySlot(Date bookingDate, String slot);

}
