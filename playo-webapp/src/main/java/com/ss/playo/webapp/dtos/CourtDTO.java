package com.ss.playo.webapp.dtos;

import com.ss.playo.common.interfaces.IDto;

public class CourtDTO implements IDto {

    private Long courtId;
    private String courtName;

    public Long getCourtId() {
        return courtId;
    }

    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }
}
