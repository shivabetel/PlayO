package com.ss.playo.webapp.web.dtos;

import com.ss.playo.common.interfaces.IDto;

import java.util.List;


public class BookingDTO implements IDto {

    private String name;
    private String bookingDate;


    private String courtNo;
    private List<String> slots;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(String courtNo) {
        this.courtNo = courtNo;
    }

    public List<String> getSlots() {
        return slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = slots;
    }


}
