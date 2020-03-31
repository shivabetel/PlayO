package com.ss.playo.webapp.persistence.dao.model;

import com.ss.playo.common.interfaces.IEntity;

import javax.persistence.*;

@Entity
@Table(name = "SLOTS_MASTER", uniqueConstraints = {@UniqueConstraint(name = "unique_slot", columnNames = {"slot"} )})
public class Slot implements IEntity {


    @Column(name="SLOT", nullable = false)
    @Id
    private Integer slot;

    @Column(name = "SLOT_DESCRIPTION", nullable = false)
    private String description;


    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
