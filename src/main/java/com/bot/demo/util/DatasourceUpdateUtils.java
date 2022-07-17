package com.bot.demo.util;

import com.bot.demo.vo.Todo;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class DatasourceUpdateUtils {
    static public Update update(Object entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Update update = new Update();
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field f : fields) {
            f.setAccessible(true);
            String fieldName = f.getName();
            String methodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            Object o = clazz.getMethod(methodName).invoke(entity);
            if(!ObjectUtils.isEmpty(o)) {
                update.set(fieldName, o);
            }
        }


        return update;
    }

    static public <A extends Annotation> Update update(Object entity, Class<A> annotationClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Update update = new Update();
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field f : fields) {
            f.setAccessible(true);
            String fieldName = f.getName();
            String methodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            Object o = clazz.getMethod(methodName).invoke(entity);
            if(!ObjectUtils.isEmpty(o) && clazz.getDeclaredAnnotation(annotationClass) == null) {
                update.set(fieldName, o);
            }
        }
        return update;
    }
}
