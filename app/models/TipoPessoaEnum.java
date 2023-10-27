package models;

import java.util.Arrays;
import java.util.List;

/**
 * Created by walla on 26/03/2019.
 */
public enum TipoPessoaEnum {
    FISICA, JURIDICA;

    public static List<TipoPessoaEnum> todos(){
        return Arrays.asList(values());
    }
}
