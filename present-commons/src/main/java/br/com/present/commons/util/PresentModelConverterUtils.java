package br.com.present.commons.util;

import java.lang.reflect.Type;

import br.com.present.commons.model.mapper.BaseCustomMapper;
import br.com.present.commons.util.bean.ModelConverterSingleton;
import lombok.experimental.UtilityClass;
import org.modelmapper.TypeMap;

@UtilityClass
public final class PresentModelConverterUtils {

    private static final ModelConverterSingleton modelConverter = ModelConverterSingleton.getInstance();

    public static <S, D> D map(final S entity, Class<D> outClass) {
        return modelConverter.map(entity, outClass);
    }

    public static <S, D> D map(final S entity, Class<D> outClass, BaseCustomMapper<?, ?> mapper) {
        if(mapper != null){
            addMapper(mapper);
        }
        return modelConverter.map(entity, outClass);
    }

    public static <D> D map(Object entity, Type destinationType) {
		return modelConverter.map(entity, destinationType);
	}

    public static <D> D map(Object entity, Type destinationType, BaseCustomMapper<?, ?> mapper) {
        if(mapper != null){
            addMapper(mapper);
        }
        return modelConverter.map(entity, destinationType);
    }

    public static void map(Object source, Object destination) {
    	modelConverter.map(source, destination);
    }

    public static void map(Object source, Object destination, BaseCustomMapper<?, ?> mapper) {
        if(mapper != null){
            addMapper(mapper);
        }
        modelConverter.map(source, destination);
    }

    public static <S, D> TypeMap<S, D> getTypeMap(Class<S> sourceType, Class<D> destinationType){
        return modelConverter.getTypeMap(sourceType, destinationType);
    }
    public static <S, D> TypeMap<S, D> typeMap(Class<S> sourceType, Class<D> destinationType){
        return modelConverter.typeMap(sourceType, destinationType);
    }

    private static void addMapper(BaseCustomMapper<?, ?> mapper) {
        mapper.addMappingTypeMap();
    }
}