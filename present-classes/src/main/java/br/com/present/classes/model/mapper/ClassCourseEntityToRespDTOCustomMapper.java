package br.com.present.classes.model.mapper;

import br.com.present.classes.model.ClassCourseResponseDTO;
import br.com.present.commons.model.ClassCourseEntity;
import br.com.present.commons.model.mapper.BaseCustomMapper;
import br.com.present.commons.util.PresentModelConverterUtils;

public class ClassCourseEntityToRespDTOCustomMapper extends BaseCustomMapper<ClassCourseEntity, ClassCourseResponseDTO> {

    public void addMappingTypeMap(){
        if(isNotMappedTypeMap(ClassCourseEntity.class, ClassCourseResponseDTO.class)){
            PresentModelConverterUtils.typeMap(ClassCourseEntity.class, ClassCourseResponseDTO.class)
                    .addMappings(mapper -> {
                        mapper.map(src -> src.getClassEntity().getId(), ClassCourseResponseDTO::setClassId);
                        mapper.map(src -> src.getCourseEntity().getId(), ClassCourseResponseDTO::setCourseId);
                    });
        }
    }
}