package com.ss.playo.webapp.mappers;

import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.webapp.dtos.CourtDTO;
import com.ss.playo.webapp.persistence.dao.model.Court;
import org.springframework.stereotype.Component;

@Component
public class CourtEntityDTOMapper implements IEntityDTOMapper<Court, CourtDTO> {

    @Override
    public CourtDTO fromEntityToDTO(Court entity) {
        CourtDTO courtDTO = new CourtDTO();
        courtDTO.setCourtId(entity.getCourtId());
        courtDTO.setCourtName(entity.getName());
        return courtDTO;
    }

    @Override
    public Court fromDTOToEntity(CourtDTO dto) {
        Court court = new Court();
        court.setName(dto.getCourtName());
        return court;
    }

}
