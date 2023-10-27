package models;

import java.util.Arrays;
import java.util.List;

/**
 * Created by walla on 05/01/2018.
 */
public enum FinalidadeContatoEnum {
    VENDAS, LOCACAO, AMBOS;

    public static List<FinalidadeContatoEnum> todos(){
        return Arrays.asList(values());
    }
}
