package br.com.present.attendances.model.mapper;

import br.com.present.attendances.model.AttendanceResponseDTO;
import br.com.present.commons.model.AttendanceEntity;
import br.com.present.commons.model.mapper.BaseCustomMapper;
import br.com.present.commons.util.PresentModelConverterUtils;

public class AttendEntityToRespDTOCustomMapper extends BaseCustomMapper<AttendanceEntity, AttendanceResponseDTO> {

    public void addMappingTypeMap(){
        if(isNotMappedTypeMap(AttendanceEntity.class, AttendanceResponseDTO.class)){
            PresentModelConverterUtils.typeMap(AttendanceEntity.class, AttendanceResponseDTO.class)
                    .addMappings(mapper -> {
                        mapper.map(src -> src.getUserEntity().getId(), AttendanceResponseDTO::setUserId);
                        mapper.map(src -> src.getEventEntity().getId(), AttendanceResponseDTO::setEventId);
                    });
        }
    }
}