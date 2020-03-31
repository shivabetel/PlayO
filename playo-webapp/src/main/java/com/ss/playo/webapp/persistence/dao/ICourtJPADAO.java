package com.ss.playo.webapp.persistence.dao;

import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.webapp.persistence.dao.model.Court;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ICourtJPADAO extends IJPADAO<Court, Long> {

    @Query("select court from Court court where court.name not in (select booking.courtNo.name from Booking booking inner join booking.bookingDetails details where booking.bookingDate = :bookingDate and details.bookedSlot.slot = :slot)")
    public List<Court> queryCourtsByDateBySlot(Date bookingDate, Integer slot);
}
