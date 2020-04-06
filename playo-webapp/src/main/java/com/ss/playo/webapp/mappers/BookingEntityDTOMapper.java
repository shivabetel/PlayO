package com.ss.playo.webapp.mappers;

import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.webapp.dtos.BookingDTO;
import com.ss.playo.webapp.persistence.dao.model.Booking;
import com.ss.playo.webapp.persistence.dao.model.BookingDetails;
import com.ss.playo.webapp.persistence.dao.model.Court;
import com.ss.playo.webapp.persistence.dao.model.Slot;
import com.ss.playo.webapp.service.ICourtsService;
import com.ss.playo.webapp.service.ISlotService;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookingEntityDTOMapper implements IEntityDTOMapper<Booking, BookingDTO> {

    @Override
    public BookingDTO fromEntityToDTO(Booking entity){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setName(entity.getName());
        bookingDTO.setBookingDate(entity.getBookingDate().toString());
        bookingDTO.setCourtNo(entity.getCourtNo().getName());
        bookingDTO.setSlots(entity.getBookingDetails().stream().map(bookingDetails -> bookingDetails.getBookedSlot().getSlot().toString()).collect(Collectors.toList()));
        return bookingDTO;
    }

    @Override
    public Booking fromDTOToEntity(BookingDTO dto) {
        return null;
    }


    public List<Booking> fromBookingDto(BookingDTO dto, ICourtsService courtsService, ISlotService slotService) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getBookingDate());
        List<Booking> entityList = new ArrayList<>();
        Booking bookingEntity = new Booking();
        bookingEntity.setName(dto.getName());
        bookingEntity.setBookingDate(date);
        Optional<Court> optionalCourt = courtsService.findById(new Long(dto.getCourtNo()));
        bookingEntity.setCourtNo(optionalCourt.orElse(null));
        bookingEntity.setBookingDetails( dto.getSlots().stream().map(s -> {
            BookingDetails bookingDetailsEntity = new BookingDetails();
            Optional<Slot> optionalSlot = slotService.findById(new Integer(s));
            bookingDetailsEntity.setBookedSlot(optionalSlot.orElse(null));
//            bookingDetailsEntity.setBookedSlot(entityManager.getReference(Slot.class, new Integer(dto.getSlot())));
            bookingDetailsEntity.setBooking(bookingEntity);
            return bookingDetailsEntity;
        }).collect(Collectors.toList()));
        entityList.add(bookingEntity);

        return entityList;
    }
}
