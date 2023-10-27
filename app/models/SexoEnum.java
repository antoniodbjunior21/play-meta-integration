package models;

import java.util.Arrays;
import java.util.List;

/**
 * Created by walla on 03/10/2019.
 */
public enum SexoEnum {
    MASCULINO, FEMININO;


    public static List<SexoEnum> todos(){
        return Arrays.asList(values());
    }
}
