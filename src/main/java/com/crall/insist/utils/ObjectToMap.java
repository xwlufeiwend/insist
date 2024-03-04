package com.crall.insist.utils;

import com.crall.insist.entity.HeaderEntity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectToMap {
    public static Map<String, Object> objectToMap(Object param){
        Map<String, Object> map = new HashMap<>();
        Field[] fields = param.getClass().getDeclaredFields();
        try {
            for (Field field: fields){
                field.setAccessible(true);
                map.put(field.getName(), field.get(param));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        HeaderEntity headerEntity = new HeaderEntity();
        headerEntity.setAccept("accept");
        headerEntity.setCookie("cookie");
        headerEntity.setReferer("referer");
        headerEntity.setUseAgent("useAgent");
        Map<String, Object> map = objectToMap(headerEntity);
        System.out.println(map);
    }
}
