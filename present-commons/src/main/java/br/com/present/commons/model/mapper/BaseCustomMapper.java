package br.com.present.commons.model.mapper;


import br.com.present.commons.util.PresentModelConverterUtils;

public abstract class BaseCustomMapper<S, D> {

    protected boolean isNotMappedTypeMap(Class<S> sourceType, Class<D> destinationType){
        return PresentModelConverterUtils.getTypeMap(sourceType, destinationType) == null;
    }

    public abstract void addMappingTypeMap();
}
