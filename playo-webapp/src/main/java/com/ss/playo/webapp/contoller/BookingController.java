package com.ss.playo.webapp.contoller;


import com.ss.playo.common.controller.AbstractController;
import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.common.service.IService;
import com.ss.playo.common.web.exception.ResourceNotFoundException;
import com.ss.playo.webapp.dtos.BookingDTO;
import com.ss.playo.webapp.mappers.BookingEntityDTOMapper;
import com.ss.playo.webapp.persistence.dao.model.Booking;
import com.ss.playo.webapp.service.IBookingService;
import com.ss.playo.webapp.service.ICourtsService;
import com.ss.playo.webapp.service.ISlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/booking")
public class BookingController extends AbstractController<BookingDTO> {

    @Autowired
    IBookingService service;

    @Autowired
    ICourtsService courtsService;

    @Autowired
    ISlotService slotService;

    @Autowired
    BookingEntityDTOMapper bookingEntityDTOMapper;

    @Override
    protected IService getService() {
        return service;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void save(@RequestBody BookingDTO dto) throws Exception {
        if (dto != null) {
            service.saveAll(bookingEntityDTOMapper.fromBookingDto(dto, courtsService, slotService));
        }

    }

    @GetMapping
    public List<BookingDTO> findAll(){
        List<Booking> bookingList =  this.service.findAll();
        return bookingList.stream().map(bookingEntityDTOMapper::fromEntityToDTO).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public BookingDTO findOne(@PathVariable(name = "id") String id){
        return this.service.findById(new Long(id)).map(bookingEntityDTOMapper::fromEntityToDTO).orElseThrow(() -> new ResourceNotFoundException("Booking details not found"));

    }

    @Override
    public IEntityDTOMapper<Booking, BookingDTO> getEntityDTOMapper() {
        return bookingEntityDTOMapper;
    }
}
