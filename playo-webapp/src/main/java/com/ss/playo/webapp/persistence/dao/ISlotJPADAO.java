package com.ss.playo.webapp.persistence.dao;

import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.webapp.dtos.SlotDTO;
import com.ss.playo.webapp.persistence.dao.model.Slot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ISlotJPADAO extends IJPADAO<Slot, Integer> {


    @Query("select slotE from Slot slotE where slotE.slot not in (select bookingDetails.bookedSlot.slot from Booking booking inner join booking.bookingDetails bookingDetails where booking.bookingDate = :bookingDate and booking.courtNo.name = :courtNo)")
    public List<Slot> querySlotsByDateByCourt(Date bookingDate, String courtNo);

    @Query("select new com.ss.playo.webapp.dtos.SlotDTO(slotE.slot, slotE.description) from Slot slotE where slotE.slot not in (select bookingDetails.bookedSlot.slot from Booking booking inner join booking.bookingDetails bookingDetails where booking.bookingDate = :bookingDate and booking.courtNo.name = :courtNo) ")
    public List<SlotDTO> querySlotsByDateByCourtDTO(Date bookingDate, String courtNo);
}
