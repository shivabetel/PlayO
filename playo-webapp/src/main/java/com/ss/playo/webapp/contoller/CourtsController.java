package com.ss.playo.webapp.contoller;

import com.ss.playo.common.controller.AbstractController;
import com.ss.playo.common.interfaces.IEntity;
import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.common.service.IService;
import com.ss.playo.webapp.dtos.CourtDTO;
import com.ss.playo.webapp.persistence.dao.model.Court;
import com.ss.playo.webapp.service.ICourtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/courts")
public class CourtsController extends AbstractController<CourtDTO> {

    @Autowired
    ICourtsService courtsService;

    @Autowired
    IEntityDTOMapper<Court, CourtDTO> courtEntityDTOMapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void save(@RequestBody CourtDTO courtDTO) throws Exception{
        this.saveInternal(courtDTO);
    }

    @GetMapping("/{bookingDate}/{slot}")
    public List<CourtDTO> query(@PathVariable(name = "bookingDate") String bookingDate, @PathVariable(name = "slot") String slot) throws  ParseException{
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDate);
      return this.courtsService.queryCourtsByDateBySlot(date, slot);
    }

    @Override
    protected IService getService() {
        return courtsService;
    }

    @Override
    public IEntityDTOMapper<Court, CourtDTO> getEntityDTOMapper() {
        return courtEntityDTOMapper;
    }
}
