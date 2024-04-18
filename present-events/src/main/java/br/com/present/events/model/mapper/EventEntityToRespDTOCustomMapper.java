package br.com.present.events.model.mapper;

import br.com.present.commons.model.EventEntity;
import br.com.present.commons.model.mapper.BaseCustomMapper;
import br.com.present.commons.util.PresentModelConverterUtils;
import br.com.present.events.model.EventResponseDTO;

public class EventEntityToRespDTOCustomMapper extends BaseCustomMapper<EventEntity, EventResponseDTO> {

    public void addMappingTypeMap(){
        if(isNotMappedTypeMap(EventEntity.class, EventResponseDTO.class)){
            PresentModelConverterUtils.typeMap(EventEntity.class, EventResponseDTO.class)
                    .addMappings(mapper ->  mapper.map(src ->
                            src.getUserEntity().getId(), EventResponseDTO::setUserId));
        }
    }
}