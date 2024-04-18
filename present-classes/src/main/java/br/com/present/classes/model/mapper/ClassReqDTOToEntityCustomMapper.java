package br.com.present.classes.model.mapper;

import br.com.present.classes.model.ClassRequestDTO;
import br.com.present.commons.model.ClassEntity;
import br.com.present.commons.model.mapper.BaseCustomMapper;
import br.com.present.commons.util.PresentModelConverterUtils;

public class ClassReqDTOToEntityCustomMapper extends BaseCustomMapper<ClassRequestDTO, ClassEntity> {

    public void addMappingTypeMap(){
        if(isNotMappedTypeMap(ClassRequestDTO.class, ClassEntity.class)){
            PresentModelConverterUtils.typeMap(ClassRequestDTO.class, ClassEntity.class)
                    .addMappings(mapper -> {
                        mapper.map(ClassRequestDTO::getGroupId, (dest, value) -> dest.getGroupEntity().setId((Long) value));
                        mapper.map(ClassRequestDTO::getDisciplineId, (dest, value) -> dest.getDisciplineEntity().setId((Long) value));
                        mapper.map(ClassRequestDTO::getEventId, (dest, value) -> dest.getEventEntity().setId((Long) value));
                    });
        }
    }
}