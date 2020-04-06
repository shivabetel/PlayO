package com.ss.playo.webapp.service.impl;

import com.ss.playo.common.service.AbstractService;
import com.ss.playo.webapp.persistence.dao.IBookingJPADAO;
import com.ss.playo.webapp.persistence.dao.ICourtJPADAO;
import com.ss.playo.webapp.persistence.dao.ISlotJPADAO;
import com.ss.playo.webapp.persistence.dao.model.Booking;
import com.ss.playo.webapp.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl extends AbstractService<Booking, Long> implements IBookingService {

    @Autowired
    IBookingJPADAO dao;

    @Autowired
    ICourtJPADAO courtJPADAO;

    @Autowired
    ISlotJPADAO slotJPADAO;



    @Override
    protected IBookingJPADAO getDAO() {
        return dao;
    }


    @Override
    public Iterable saveAll(Iterable entities) {
        return getDAO().saveAll(entities);
    }
}
