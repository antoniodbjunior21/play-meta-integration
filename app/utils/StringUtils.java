package utils;

import javax.swing.text.MaskFormatter;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.StringTokenizer;

public class StringUtils {

    public static boolean isEmpthOrNull(String text) {
        return text == null || text.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
    }

    public static boolean isNotEmpthOrNull(String text) {
        return text != null && !text.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
    }

    public static String standardize(String value) {

        if(isEmpthOrNull(value)){
            return "";
        }

        // UpperCase
        value = value.toUpperCase().trim();

        value = value.replaceAll("\\?", "");
        value = value.replaceAll("\\:", "");
        value = value.replaceAll("\\#", "");

        value = value.replaceAll("[-+=*&amp;%$#@!_]", "");
        value = value.replaceAll("['\"]", "");
        value = value.replaceAll("[<>()\\{\\}]", "");
        value = value.replaceAll("['\\\\.,()|/]", "");

        // Agudo
        value = value.replaceAll("Á", "A");
        value = value.replaceAll("É", "E");
        value = value.replaceAll("Í", "I");
        value = value.replaceAll("Ó", "O");
        value = value.replaceAll("Ú", "U");

        // Circunflexo
        value = value.replaceAll("Â", "A");
        value = value.replaceAll("Ê", "E");
        value = value.replaceAll("Î", "I");
        value = value.replaceAll("Ô", "O");
        value = value.replaceAll("Û", "U");

        // Til
        value = value.replaceAll("Ã", "A");
        value = value.replaceAll("Õ", "O");

        // Crase
        value = value.replaceAll("À", "A");
        value = value.replaceAll("È", "E");
        value = value.replaceAll("Ì", "I");
        value = value.replaceAll("Ò", "O");
        value = value.replaceAll("Ù", "U");

        // Cedilha
        value = value.replaceAll("Ç", "C");

        // Trema
        value = value.replaceAll("Ü", "U");

        // Troca espaço por traço
        value = value.replaceAll(" ", "-");

        value = value.replaceAll("--", "-");

        //removendo caracteres
        value = value.replaceAll("²", "");
        value = value.replaceAll("°", "");
        value = value.replaceAll("ª", "");
        value = value.replaceAll("º", "");

        return value.toLowerCase();
    }

    public static String toCamelCase(String strData) {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(strData, " ", true);
        while (st.hasMoreTokens()) {
            String strWord = st.nextToken();
            sb.append(Character.toUpperCase(strWord.charAt(0)));
            sb.append(strWord.substring(1).toLowerCase());
        }
        return sb.toString();
    }

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static String removerCaracteresEspeciais(String str) {

        if(isEmpthOrNull(str)){
            return "";
        }

        return str.replaceAll("[^a-zZ-Z1-9 ]", "");
    }

    public static String removerEspacos(String str) {
        return str.replaceAll("\\s", "");
    }

    public static String recuperarExtensaoArquivo(String arquivoHD) {
        if(isEmpthOrNull(arquivoHD)){
            return "";
        }
        return "." + arquivoHD.split("\\.")[1];
    }

    public static String trim(String str) {
        String trim = str.trim();
        trim = trim.replaceAll("\\s+$", "");
        trim = trim.replaceAll("^\\s+", "");
        return trim;
    }

    public static String cortarString(String texto, Integer lenght) {
        return texto != null && texto.length() < lenght.intValue() ? texto : texto.substring(0, lenght.intValue());
    }

    public static String obterPrimeiraLetra(String texto) {
        if (StringUtils.isNotEmpthOrNull(texto)) {
            return texto.substring(0, 1);
        }

        return "";
    }

    public static String removerCaracteres(String str) {
        if (str != null) {
            return str.replaceAll("[^0123456789]", "");
        } else {
            return "";
        }
    }

    public static String formatarString(String texto, String mascara) {
        try {
            MaskFormatter mf = new MaskFormatter(mascara);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(texto);
        } catch (ParseException e) {
            return null;
        }

    }

}
