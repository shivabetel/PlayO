package com.ss.playo.webapp.persistence.dao;


import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.webapp.persistence.dao.model.Booking;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookingJPADAO extends IJPADAO<Booking, Long> {
}
