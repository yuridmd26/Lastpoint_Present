package br.com.present.commons.util.bean;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelConverterSingleton extends ModelMapper {
	
	private static ModelConverterSingleton instance;
	
	private ModelConverterSingleton() {}

    public static ModelConverterSingleton getInstance() {
        if (instance == null) {
        	instance = new ModelConverterSingleton();
            instance.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        }
        return instance;
    }
}