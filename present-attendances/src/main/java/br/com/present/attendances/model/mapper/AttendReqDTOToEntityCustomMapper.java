package br.com.present.attendances.model.mapper;

import br.com.present.attendances.model.AttendanceRequestDTO;
import br.com.present.commons.model.AttendanceEntity;
import br.com.present.commons.model.mapper.BaseCustomMapper;
import br.com.present.commons.util.PresentModelConverterUtils;

public class AttendReqDTOToEntityCustomMapper extends BaseCustomMapper<AttendanceRequestDTO, AttendanceEntity> {

    public void addMappingTypeMap(){
        if(isNotMappedTypeMap(AttendanceRequestDTO.class, AttendanceEntity.class)){
            PresentModelConverterUtils.typeMap(AttendanceRequestDTO.class, AttendanceEntity.class)
                    .addMappings(mapper -> {
                        mapper.map(AttendanceRequestDTO::getUserId, (dest, value) -> dest.getUserEntity().setId((Long) value));
                        mapper.map(AttendanceRequestDTO::getEventId, (dest, value) -> dest.getEventEntity().setId((Long) value));
                    });
        }
    }
}