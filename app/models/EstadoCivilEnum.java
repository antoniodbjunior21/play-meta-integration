package models;

import java.util.Arrays;
import java.util.List;

public enum EstadoCivilEnum {

    SOLTEIRO, CASADO, SEPARADO, DIVORCIADO, VIUVO, CASADO_COM_COMUNHAO_BENS, CASADO_COMUNHAO_PARCIAL_BENS, CASADO_SEPARACAO_BENS, UNIAO_ESTAVEL;

    public static List<EstadoCivilEnum> todos(){
        return Arrays.asList(values());
    }

}
