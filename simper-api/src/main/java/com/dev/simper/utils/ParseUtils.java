package com.dev.simper.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.modelmapper.ModelMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParseUtils {

    private static ModelMapper modelMapper = new ModelMapper();

    /**
     * Method responsible for converting/mapping an object to a specific class
     * @param sourceObject
     * @param destinationClass
     * @param <T>
     * @param <V>
     * @return V mapped object
     */
    public static <T, V> V parse(T sourceObject, Class<V> destinationClass) {
        V v = null;
        try {
            v = modelMapper.map(sourceObject, destinationClass);
        } catch (Exception e) {
            log.error("Unable to convert the object {" + ToStringBuilder.reflectionToString(sourceObject) + "} to the class {" + destinationClass.getSimpleName() + "}.");
            throw new RuntimeException(e);
        }
        return v;
    }

    /***
     * Method responsible for converting/mapping a list of objects to a specific class
     * @param <T>
     * @param <V>
     * @param sourceList
     * @param destinationClass
     * @return List<V> list of mapped object
     */
    public static <T, V> List<V> parse(List<T> sourceList, Class<V> destinationClass) {
        List<V> vList = new ArrayList<V>();
        if (sourceList == null || sourceList.isEmpty()) {
            return vList;
        }
        for (T t : sourceList) {
            V v = parse(t, destinationClass);
            if (v != null) {
                vList.add(v);
            }
        }
        return vList;
    }
}
