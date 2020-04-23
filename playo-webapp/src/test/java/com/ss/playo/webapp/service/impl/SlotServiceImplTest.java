package com.ss.playo.webapp.service.impl;

import com.ss.playo.common.interfaces.IDto;
import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.common.service.IService;
import com.ss.playo.webapp.persistence.dao.ISlotJPADAO;
import com.ss.playo.webapp.persistence.dao.model.Slot;
import com.ss.playo.webapp.web.dtos.SlotDTO;
import com.ss.test.common.service.AbstractServiceUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SlotServiceImplTest extends AbstractServiceUnitTest<Slot, Integer> {

    SlotServiceImpl instance;

    @MockBean
    ISlotJPADAO daoMock;

    @BeforeEach
    void setUp() {
        instance = new SlotServiceImpl(daoMock);
    }

    @Test
    void querySlotsByDateByCourt() {

        //given
        Date bookingDate = new Date();
        String courtNo = "4";
        List<Slot> slots = Arrays.asList();
        when(getDAO().querySlotsByDateByCourt(bookingDate, courtNo)).thenReturn(slots);

        List<Slot> returnedSlots =  instance.querySlotsByDateByCourt(bookingDate, courtNo);

        assertEquals(slots, returnedSlots);

    }

    @Test
    void querySlotsByDateByCourtDTO() {
        Date bookingDate = new Date();
        String courtNo = "4";
        List<SlotDTO> slots = Arrays.asList();
        when(getDAO().querySlotsByDateByCourtDTO(bookingDate, courtNo)).thenReturn(slots);
        List<SlotDTO> returnedList = instance.querySlotsByDateByCourtDTO(bookingDate, courtNo);
        assertEquals(slots, returnedList);
    }

    @Override
    public IService<Slot, Integer> getApi() {
        return instance;
    }

    @Override
    protected ISlotJPADAO getDAO() {
        return daoMock;
    }

    @Override
    protected Slot createNewEntity(IDto dto) {
        return null;
    }
}