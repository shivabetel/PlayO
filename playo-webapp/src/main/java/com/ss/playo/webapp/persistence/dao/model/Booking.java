package com.ss.playo.webapp.persistence.dao.model;


import com.ss.playo.common.interfaces.IEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "COURT_BOOKING")
public class Booking implements IEntity {

    @Column(name = "BOOKING_ID")
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "BOOKING_NAME", nullable = false)
    private String name;

    @Column(name="BOOKING_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date bookingDate;

    @OneToOne(fetch = FetchType.EAGER)//EAGER is default
    @JoinColumn(name = "COURT_NO", referencedColumnName = "COURT_NAME", nullable = false)
    private Court courtNo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "booking", fetch = FetchType.LAZY)
    private Collection<BookingDetails> bookingDetails = new ArrayList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Collection<BookingDetails> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(Collection<BookingDetails> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public Court getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Court courtNo) {
        this.courtNo = courtNo;
    }
}
