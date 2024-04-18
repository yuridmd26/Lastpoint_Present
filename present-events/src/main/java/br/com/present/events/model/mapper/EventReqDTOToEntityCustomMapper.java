package br.com.present.events.model.mapper;

import br.com.present.commons.model.EventEntity;
import br.com.present.commons.model.mapper.BaseCustomMapper;
import br.com.present.commons.util.PresentModelConverterUtils;
import br.com.present.events.model.EventRequestDTO;
import org.modelmapper.Converter;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class EventReqDTOToEntityCustomMapper extends BaseCustomMapper<EventRequestDTO, EventEntity> {

    private final Converter<ZonedDateTime, OffsetDateTime> zonedToOffset =
            context -> context.getSource().toOffsetDateTime();

    public void addMappingTypeMap(){
        if(isNotMappedTypeMap(EventRequestDTO.class, EventEntity.class)){
            PresentModelConverterUtils.typeMap(EventRequestDTO.class, EventEntity.class)
                    .addMapping(EventRequestDTO::getUserId, (dest, value) -> dest.getUserEntity().setId((Long) value))
                    .addMappings(mapper -> {
                        mapper.using(zonedToOffset).map(EventRequestDTO::getStartDateTime, EventEntity::setStartDateTime);
                        mapper.using(zonedToOffset).map(EventRequestDTO::getEndDateTime, EventEntity::setEndDateTime);
                    });
        }
    }
}