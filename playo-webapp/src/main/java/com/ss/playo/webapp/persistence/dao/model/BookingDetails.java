package com.ss.playo.webapp.persistence.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "COURT_BOOKING_DETAILS")
public class BookingDetails {

    @Column(name = "BOOKING_DETAILS_ID")
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "BOOKING_SLOT", nullable = false)
    private Slot bookedSlot;


    @ManyToOne
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Slot getBookedSlot() {
        return bookedSlot;
    }

    public void setBookedSlot(Slot bookedSlot) {
        this.bookedSlot = bookedSlot;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
