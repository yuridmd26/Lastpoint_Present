package br.com.present.events.model.mapper;

import br.com.present.commons.model.UserEventEntity;
import br.com.present.commons.model.mapper.BaseCustomMapper;
import br.com.present.commons.util.PresentModelConverterUtils;
import br.com.present.events.model.UserEventResponseDTO;

public class UserEventEntityToRespDTOCustomMapper extends BaseCustomMapper<UserEventEntity, UserEventResponseDTO> {

    public void addMappingTypeMap(){
        if(isNotMappedTypeMap(UserEventEntity.class, UserEventResponseDTO.class)){
            PresentModelConverterUtils.typeMap(UserEventEntity.class, UserEventResponseDTO.class)
                    .addMappings(mapper -> {
                        mapper.map(src -> src.getUserEntity().getId(), UserEventResponseDTO::setUserId);
                        mapper.map(src -> src.getEventEntity().getId(), UserEventResponseDTO::setEventId);
                    });
        }
    }
}