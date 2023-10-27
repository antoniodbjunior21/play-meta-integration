package utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Formatter;
import java.util.Locale;

public class MoedaUtil {

	public static boolean isNegative(BigDecimal value)
    {
		if (value == null) {
			return false;
		}
		return    value.signum() == -1;
    }
	
	public static boolean naoEhNuloNemZerado(BigDecimal value)
    {
		return    value != null && BigDecimal.ZERO.compareTo(value) != 0;
    }

    public static int decimalToCents(BigDecimal decimal) {
        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal bigDecimalInCents = decimal.movePointRight(2);
        int cents = bigDecimalInCents.intValueExact();
        return cents;
    }

	public static BigDecimal converterBigDecimal(String valor) throws ParseException{
		if (valor == null) {
			return BigDecimal.ZERO;
		}

		Locale brasil = new Locale( "pt", "BR" );
	    DecimalFormat numberFormat = new DecimalFormat( "#######0,00", new DecimalFormatSymbols( brasil ) );
		numberFormat.setParseBigDecimal( true );
	    numberFormat.setMinimumFractionDigits(2);
	    return (BigDecimal) numberFormat.parse(valor);
	}

	public static String formatarBigDecimal(BigDecimal valor,Integer precisao){
		if (valor == null) {
			return "";
		}

		Locale brasil = new Locale( "pt", "BR" );
		DecimalFormat numberFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols( brasil ) );
		numberFormat.setParseBigDecimal( true );
		numberFormat.setDecimalSeparatorAlwaysShown(true);
		numberFormat.setMinimumFractionDigits( precisao );
		return numberFormat.format(valor);
	}

	public static String formatarKm(Integer km){
		Formatter formatter = new Formatter();
		return formatter.format("%,d", km).toString();
	}

	public static String formatarBigDecimalComMoeda(BigDecimal valor,Integer precisao){
		if (valor == null) {
			return "";
		}
		Locale brasil = new Locale( "pt", "BR" );
		DecimalFormat numberFormat = new DecimalFormat("¤ #,##0.00", new DecimalFormatSymbols( brasil ) );
		numberFormat.setParseBigDecimal( true );
		numberFormat.setDecimalSeparatorAlwaysShown( true );
		numberFormat.setMinimumFractionDigits( precisao );
		return numberFormat.format(valor);
	}

	public static String formatarBigDecimalComMoeda(BigDecimal valor,Integer precisao,BigDecimal valorPadrao){
		if (valor == null) {
			return formatarBigDecimalComMoeda(valorPadrao, precisao);
		} else {
			return formatarBigDecimalComMoeda(valor, precisao);
		}
	}

	public static boolean isNotNull(BigDecimal valor){
		return valor != null && BigDecimal.ZERO.compareTo(valor) != 0;
	}

}
