package br.com.present.classes.model.mapper;

import br.com.present.classes.model.ClassCourseRequestDTO;
import br.com.present.commons.model.ClassCourseEntity;
import br.com.present.commons.model.mapper.BaseCustomMapper;
import br.com.present.commons.util.PresentModelConverterUtils;

public class ClassCourseReqDTOToEntityCustomMapper extends BaseCustomMapper<ClassCourseRequestDTO, ClassCourseEntity> {

    public void addMappingTypeMap(){
        if(isNotMappedTypeMap(ClassCourseRequestDTO.class, ClassCourseEntity.class)){
            PresentModelConverterUtils.typeMap(ClassCourseRequestDTO.class, ClassCourseEntity.class)
                    .addMappings(mapper -> {
                        mapper.map(ClassCourseRequestDTO::getClassId, (dest, value) -> dest.getClassEntity().setId((Long) value));
                        mapper.map(ClassCourseRequestDTO::getCourseId, (dest, value) -> dest.getCourseEntity().setId((Long) value));
                    });
        }
    }
}