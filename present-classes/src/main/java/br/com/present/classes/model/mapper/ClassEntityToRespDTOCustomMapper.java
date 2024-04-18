package br.com.present.classes.model.mapper;

import br.com.present.classes.model.ClassResponseDTO;
import br.com.present.commons.model.ClassEntity;
import br.com.present.commons.model.mapper.BaseCustomMapper;
import br.com.present.commons.util.PresentModelConverterUtils;

public class ClassEntityToRespDTOCustomMapper extends BaseCustomMapper<ClassEntity, ClassResponseDTO> {

    public void addMappingTypeMap(){
        if(isNotMappedTypeMap(ClassEntity.class, ClassResponseDTO.class)){
            PresentModelConverterUtils.typeMap(ClassEntity.class, ClassResponseDTO.class)
                    .addMappings(mapper -> {
                        mapper.map(src -> src.getGroupEntity().getId(), ClassResponseDTO::setGroupId);
                        mapper.map(src -> src.getDisciplineEntity().getId(), ClassResponseDTO::setDisciplineId);
                        mapper.map(src -> src.getEventEntity().getId(), ClassResponseDTO::setEventId);
                    });
        }
    }
}