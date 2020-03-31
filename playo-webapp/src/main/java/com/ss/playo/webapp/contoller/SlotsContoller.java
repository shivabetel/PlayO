package com.ss.playo.webapp.contoller;

import com.ss.playo.common.controller.AbstractController;
import com.ss.playo.common.interfaces.IEntity;
import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.common.service.IService;
import com.ss.playo.webapp.dtos.SlotDTO;
import com.ss.playo.webapp.persistence.dao.model.Slot;
import com.ss.playo.webapp.service.ISlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/slot")
public class SlotsContoller extends AbstractController<SlotDTO> {


    @Autowired
    private ISlotService service;

    @Autowired
    IEntityDTOMapper<Slot, SlotDTO> slotEntityDTOMapper;

    @Override
    protected IService getService() {
        return service;
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void save(@RequestBody SlotDTO dto) throws Exception{
          this.saveInternal(dto);
    }


    @GetMapping("/{bookingDate}/{courtNo}")
    public List<SlotDTO> query(@PathVariable(name = "bookingDate") String bookingDate, @PathVariable(name = "courtNo") String courtNo) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDate);
        List<Slot> slotList = service.querySlotsByDateByCourt(date, courtNo);
       return slotList.stream().map(slotEntityDTOMapper::fromEntity).collect(Collectors.toList());

    }

    @GetMapping("/dto/{bookingDate}/{courtNo}")
    public List<SlotDTO> queryDTO(@PathVariable(name = "bookingDate") String bookingDate, @PathVariable(name = "courtNo") String courtNo) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDate);
        return service.querySlotsByDateByCourtDTO(date, courtNo);

    }

    @GetMapping
    public List<SlotDTO> retrieveAllSlots(){
        List<Slot> slotList = service.findAll();
        return slotList.stream().map(slotEntityDTOMapper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public IEntityDTOMapper<Slot, SlotDTO> getEntityDTOMapper() {
        return slotEntityDTOMapper;
    }
}
