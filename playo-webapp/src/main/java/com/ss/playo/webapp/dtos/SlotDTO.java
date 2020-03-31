package com.ss.playo.webapp.dtos;

import com.ss.playo.common.interfaces.IDto;

public class SlotDTO implements IDto {

    private Integer slot;
    private String description;


    public SlotDTO() {
    }

    public SlotDTO(Integer slot, String description) {
        this.slot = slot;
        this.description = description;
    }

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
