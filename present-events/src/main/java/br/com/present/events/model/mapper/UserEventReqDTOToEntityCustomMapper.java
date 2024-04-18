package br.com.present.events.model.mapper;

import br.com.present.commons.model.UserEventEntity;
import br.com.present.commons.model.mapper.BaseCustomMapper;
import br.com.present.commons.util.PresentModelConverterUtils;
import br.com.present.events.model.UserEventRequestDTO;

public class UserEventReqDTOToEntityCustomMapper extends BaseCustomMapper<UserEventRequestDTO, UserEventEntity> {

    public void addMappingTypeMap(){
        if(isNotMappedTypeMap(UserEventRequestDTO.class, UserEventEntity.class)){
            PresentModelConverterUtils.typeMap(UserEventRequestDTO.class, UserEventEntity.class)
                    .addMappings(mapper -> {
                        mapper.map(UserEventRequestDTO::getUserId, (dest, value) -> dest.getUserEntity().setId((Long) value));
                        mapper.map(UserEventRequestDTO::getEventId, (dest, value) -> dest.getEventEntity().setId((Long) value));
                    });
        }
    }
}