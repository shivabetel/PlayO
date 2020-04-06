package com.ss.playo.webapp.web.mappers;

import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.webapp.web.dtos.SlotDTO;
import com.ss.playo.webapp.persistence.dao.model.Slot;
import org.springframework.stereotype.Component;


@Component
public class SlotEntityDTOMapper implements IEntityDTOMapper<Slot, SlotDTO> {

    @Override
    public SlotDTO fromEntityToDTO(Slot entity) {
        SlotDTO slotDTO = new SlotDTO();
        slotDTO.setSlot(entity.getSlot());
        slotDTO.setDescription(entity.getDescription());
        return slotDTO;
    }

    @Override
    public Slot fromDTOToEntity(SlotDTO dto) {
        Slot slot = new Slot();
        slot.setSlot(dto.getSlot());
        slot.setDescription(dto.getDescription());
        return slot;
    }
}
