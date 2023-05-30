package com.rick.bookStore.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    //<O,D> tipos genéricos (Origem e Destino)
    // recebe origem e destino, faz o mapeamento e retorna como destino.
    public static <O,D> D parseObject(O origin, Class<D> destination){
        return  mapper.map(origin, destination);
    }

    public static <O,D> List <D> parseListObject(List<O> origin, Class<D> destination){
        List<D> destinationObjects = new ArrayList<D>();
        for (O obj : origin){
            destinationObjects.add(mapper.map(obj, destination));
        }
        return destinationObjects;
    }


}
