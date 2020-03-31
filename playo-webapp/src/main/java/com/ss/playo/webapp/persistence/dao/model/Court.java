package com.ss.playo.webapp.persistence.dao.model;

import com.ss.playo.common.interfaces.IEntity;

import javax.persistence.*;

@Entity
@Table(name = "COURTS_MASTER", uniqueConstraints = {@UniqueConstraint(name = "unique_courtno", columnNames = {"COURT_NAME"})})
public class Court implements IEntity {

    @Column(name = "COURT_ID")
    @Id
    @GeneratedValue
    private Long courtId;
    @Column(name="COURT_NAME", nullable = false)
    private String name;

    public Long getCourtId() {
        return courtId;
    }

    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
