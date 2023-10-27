package utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by AgiumSoft on 16/12/2015.
 */
public class ReflectionsUtil {

    public static Set<Field> findFields(Class<?> classe, Class<? extends Annotation> anotacao) {
        Set<Field> set = new HashSet<>();
        Class<?> c = classe;
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(anotacao)) {
                    set.add(field);
                }
            }
            c = c.getSuperclass();
        }
        return set;
    }

    public static Field getField(String field, Class<?> type) throws NoSuchFieldException {
        try{
            Field fieldClass = type.getDeclaredField(field);
            fieldClass.setAccessible(true);
            return fieldClass;
        }catch (NoSuchFieldException e){
            return type.getSuperclass().getDeclaredField(field);
        }
    }

    public static Object getValue(String field, Class<?> type, Object object) throws NoSuchFieldException, IllegalAccessException {
        Field fieldClass = null;
        try{
            fieldClass = type.getDeclaredField(field);
            fieldClass.setAccessible(true);
            return fieldClass.get(object);
        }catch (NoSuchFieldException | IllegalAccessException e){
            fieldClass = type.getSuperclass().getDeclaredField(field);
            fieldClass.setAccessible(true);
            return fieldClass.get(object);
        }
    }

}
