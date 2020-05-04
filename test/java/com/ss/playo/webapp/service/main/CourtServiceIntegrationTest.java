package com.ss.playo.webapp.service.main;

import com.ss.playo.common.interfaces.IDto;
import com.ss.playo.common.interfaces.IEntity;
import com.ss.playo.common.service.IService;
import com.ss.playo.webapp.persistence.dao.model.Booking;
import com.ss.playo.webapp.persistence.dao.model.BookingDetails;
import com.ss.playo.webapp.persistence.dao.model.Court;
import com.ss.playo.webapp.persistence.dao.model.Slot;
import com.ss.playo.webapp.service.IBookingService;
import com.ss.playo.webapp.service.ICourtsService;
import com.ss.playo.webapp.service.ISlotService;
import com.ss.playo.webapp.web.dtos.CourtDTO;
import com.ss.test.common.service.AbstractServiceIntegrationUnitTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


class CourtServiceIntegrationTest extends AbstractServiceIntegrationUnitTest {

    @Autowired
    ICourtsService courtsService;

    @Autowired
    IBookingService bookingService;

    @Autowired
    ISlotService slotService;

    @Test
    void whenSavePerformed() {
        //given

        saveCourts();

        List<Court> foundCourtList = courtsService.findAll();

        assertThat(foundCourtList, hasSize(8));
    }

    @Test
    void when_queryCourtsByDateBySlotPeformed(){
        //given
        Date bookingDate = new Date();
        Integer slotNo = 19;//7pm slot
        Long courtNo = new Long(5);
        saveCourts();
        saveSlots();
        bookCourt(bookingDate, slotNo, courtNo);

        //when
        List<CourtDTO> courtDtosList = courtsService.queryCourtsByDateBySlot(bookingDate, slotNo.toString());
        List<String> courtNames = courtDtosList.stream().map(courtDTO -> courtDTO.getCourtName()).collect(Collectors.toList());

        //then

        assertThat(courtNames, not(hasItem(courtNo.toString())));
    }


    private void bookCourt(Date bookingDate, Integer slot, Long courtNo){
        Booking booking = new Booking();
        booking.setBookingDate(bookingDate);
        booking.setCourtNo(courtsService.findByCourtName(courtNo.toString()).orElseGet(() -> null));
        booking.setName("shiva");

        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setBooking(booking);
        bookingDetails.setBookedSlot(slotService.findById(19).orElseGet(() -> null));

        booking.setBookingDetails(Arrays.asList(bookingDetails));

        bookingService.save(booking);
    }


    private void saveCourts(){
        List<Court> courtList = new ArrayList();
        for(int i=1;i<=8;i++){
            Court court = new Court();
            court.setName(String.valueOf(i));
            courtList.add(court);
        }
        getApi().saveAll(courtList);

    }

    private void saveSlots(){
        List<Slot> slotList = Arrays.asList(
                new Slot(5, "5am slot"),
                new Slot(6, "6am slot"),
                new Slot(7, "7am slot"),
                new Slot(8, "8am slot"),
                new Slot(9, "9am slot"),
                new Slot(10, "10am slot"),
                new Slot(11, "11am slot"),
                new Slot(12, "12pm slot"),
                new Slot(13, "1pm slot"),
                new Slot(14, "2pm slot"),
                new Slot(15, "3pm slot"),
                new Slot(16, "4pm slot"),
                new Slot(17, "5pm slot"),
                new Slot(18, "6pm slot"),
                new Slot(19, "7pm slot"),
                new Slot(20, "8pm slot"),
                new Slot(21, "9pm slot"),
                new Slot(22, "10pm slot")

        );
        slotService.saveAll(slotList);
    }

    @Override
    protected ICourtsService getApi() {
        return courtsService;
    }

    @Override
    protected Court createNewEntity() {
        Court court = new Court();
        court.setName("1");
        return court;
    }

    @Override
    protected IEntity createNewEntity(IDto dto) {
        return null;
    }
}