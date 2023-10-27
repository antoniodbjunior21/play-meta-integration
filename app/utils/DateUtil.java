package utils;

import org.joda.time.*;
import play.i18n.Messages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

    public static Date adicionarDiaData(Date dataFinal, Integer dias) {
        Calendar dataFimCalendarMes = Calendar.getInstance();
        dataFimCalendarMes.setTime(dataFinal);
        dataFimCalendarMes.add(Calendar.DAY_OF_MONTH, dias);
        return dataFimCalendarMes.getTime();
    }

    public static Integer ultimoDiaMes(Date data) {
        Calendar dataProximoMes = Calendar.getInstance();
        dataProximoMes.setTime(data);
        return dataProximoMes.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static boolean ehUltimoDiaMes(Date data) {
        Calendar dataFimVigencia = Calendar.getInstance();
        dataFimVigencia.setTime(data);
        return dataFimVigencia.getActualMaximum(Calendar.DAY_OF_MONTH) == dataFimVigencia.get(Calendar.DAY_OF_MONTH);
    }

    public static boolean ehPrimeiroDiaMes(Date data) {
        Calendar dataInicioVigencia = Calendar.getInstance();
        dataInicioVigencia.setTime(data);
        return dataInicioVigencia.get(Calendar.DAY_OF_MONTH) == 1;
    }

    public static String formataData(Date data) {
        if (data == null)
            return null;

        String date = null;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        date = formatter.format(data);

        return date;
    }

    public static String formataDataPostgres(Date data) {
        if (data == null)
            return null;

        String date = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        date = formatter.format(data);

        return date;
    }

    public static String formataTimestamp(Date data) {
        if (data == null)
            return null;

        String date = null;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = formatter.format(data);

        return date;
    }

    public static String formataHora(Date time, Boolean comSegundos) {
        if (time == null)
            return null;

        String date = null;
        DateFormat formatter = new SimpleDateFormat(comSegundos ? "HH:mm:ss" : "HH:mm");
        date = formatter.format(time);

        return date;
    }

    public static Date converteData(String data) {
        if (data == null || data.equals(""))
            return null;

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (Date) formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date converteDataFormatoAmericano(String data) {
        if (data == null || data.equals(""))
            return null;

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = (Date) formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date converteDataFormatoAmericanoT(String data) {
        if (data == null || data.equals(""))
            return null;

        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            date = (Date) dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date converteTimestamp(String data) {
        if (data == null || data.equals(""))
            return null;

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            date = (Date) formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date converteTimestampFormatoAmericano(String data) {
        if (data == null || data.equals(""))
            return null;

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = (Date) formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date converteHora(String time, Boolean comSegundos) {
        if (time == null || time.equals(""))
            return null;

        time += (comSegundos && time.length() == 5) ? ":00" : "";

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat(comSegundos ? "HH:mm:ss" : "HH:mm");
            date = (Date) formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date obterHoraZerada() {
        Calendar data = Calendar.getInstance();
        data.set(Calendar.HOUR, 0);
        data.set(Calendar.HOUR_OF_DAY, 0);
        data.set(Calendar.MINUTE, 0);
        data.set(Calendar.SECOND, 0);
        data.set(Calendar.MILLISECOND, 0);
        return data.getTime();
    }

    public static void zerarHorario(Calendar data) {
        data.set(Calendar.HOUR, 0);
        data.set(Calendar.HOUR_OF_DAY, 0);
        data.set(Calendar.MINUTE, 0);
        data.set(Calendar.SECOND, 0);
        data.set(Calendar.MILLISECOND, 0);
    }

    public static Date zerarData(Date data) {
        Calendar dataCalendar = Calendar.getInstance();
        dataCalendar.setTime(data);
        zerarData(dataCalendar);
        return dataCalendar.getTime();
    }

    public static Date zerarData(Calendar dataCalendar) {
        dataCalendar.set(Calendar.DATE, 0);
        dataCalendar.set(Calendar.MONTH, 0);
        dataCalendar.set(Calendar.YEAR, 0);
        return dataCalendar.getTime();
    }

    public static String formatarDataAmigavel(Date date) {
        Calendar data = Calendar.getInstance();
        data.setTime(date);
        zerarHorario(data);

        Calendar hoje = Calendar.getInstance();
        zerarHorario(hoje);

        if (hoje.equals(data)) {
            return "Hoje";
        } else {
            hoje.add(Calendar.DATE, -1);

            if (hoje.equals(data)) {
                return "Ontem";
            }
        }

        return formataData(date);
    }

    public static long calcularDiferencaDias(Date dataInicio, Date dataFim) {
        Calendar dataInicioCalendar = Calendar.getInstance();
        dataInicioCalendar.setTime(dataInicio);
        zerarHorario(dataInicioCalendar);
        Calendar dataFinalCalendar = Calendar.getInstance();
        dataFinalCalendar.setTime(dataFim);
        zerarHorario(dataFinalCalendar);
        long diferenca = dataFinalCalendar.getTimeInMillis() - dataInicioCalendar.getTimeInMillis();
        int tempoDia = 86400000;
        return diferenca / (long) tempoDia;
    }

    public static long calcularDiferencaMesesPor(Date dataInicio, Date dataFim) {
        LocalDate dataInicioMes = new LocalDate(dataInicio);
        LocalDate dataFimMes = new LocalDate(dataFim);
        Months months = Months.monthsBetween(dataInicioMes, dataFimMes);
        Long quantidadeMeses = Long.valueOf((long) months.getMonths());
        return quantidadeMeses.longValue();
    }

    public static long calcularDiferencaAnos(Date dataInicio, Date dataFim) {
        LocalDate dataInicioMes = new LocalDate(dataInicio);
        LocalDate dataFimMes = new LocalDate(dataFim);
        Years years = Years.yearsBetween(dataInicioMes, dataFimMes);
        return (long) years.getYears();
    }

    public static Date getUltimaDataMes(Integer mes, Integer ano) {
        Calendar data = Calendar.getInstance();
        data.set(Calendar.MONTH, mes);
        data.set(Calendar.YEAR, ano);

        int ultimoDiaMes = data.getActualMaximum(Calendar.DAY_OF_MONTH);

        data.set(Calendar.DAY_OF_MONTH, ultimoDiaMes);

        return data.getTime();
    }

    public static String formatarDataComDiaEMes(Date data) {
        Locale BRAZIL = new Locale("pt", "BR");
        SimpleDateFormat format = new SimpleDateFormat("d MMM", BRAZIL);
        return format.format(data);
    }

    public static String formatarDataComDiaEMesEAno(Date data) {
        Locale BRAZIL = new Locale("pt", "BR");
        SimpleDateFormat format = new SimpleDateFormat("dd MMM YYYY", BRAZIL);
        return format.format(data);
    }

    public static String formatarDataComMesEAno(Date data) {
        Locale BRAZIL = new Locale("pt", "BR");
        SimpleDateFormat format = new SimpleDateFormat("MMMM 'de' YYYY", BRAZIL);
        return format.format(data);
    }

    public static String formatarDataComMesEAnoAbreviados(Date data) {
        Locale BRAZIL = new Locale("pt", "BR");
        SimpleDateFormat format = new SimpleDateFormat("MMM'/'YYYY", BRAZIL);
        String dataStr = format.format(data);
        return dataStr.substring(0, 1).toUpperCase() + dataStr.substring(1);
    }

    public static Integer anoAtual() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static Date ultimoDiaMesAtual() {
        Calendar data = Calendar.getInstance();
        int ultimoDiaMes = data.getActualMaximum(Calendar.DAY_OF_MONTH);
        data.set(Calendar.DAY_OF_MONTH, ultimoDiaMes);
        return data.getTime();
    }

    public static Date primeiroDiaMesAtual() {
        Calendar data = Calendar.getInstance();
        data.set(Calendar.DAY_OF_MONTH, 1);
        return data.getTime();
    }

    public static String getDataExtenso(Date data){
        DateFormat df = new SimpleDateFormat("d MMMM yyyy");
        Locale BRAZIL = new Locale("pt","BR");
        df = DateFormat.getDateInstance(DateFormat.FULL, BRAZIL);
        return df.format(data);
    }

    public static Date dataAtualHorarioBrasilia(){
        Date localTime = new Date();
        //Fomatadores
        DateFormat dataConverter = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat horaConverter = new SimpleDateFormat("HH:mm:ss");
        //Setando as timezones
        dataConverter.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        horaConverter.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));

        Calendar dataCalendar = Calendar.getInstance();
        dataCalendar.setTime(DateUtil.converteData(dataConverter.format(localTime)));
        DateUtil.zerarHorario(dataCalendar);

        Calendar horarioCalendar = Calendar.getInstance();
        horarioCalendar.setTime(DateUtil.converteHora(horaConverter.format(localTime), false));

        Calendar dataAtual = Calendar.getInstance();
        dataAtual.setTime(dataCalendar.getTime());
        dataAtual.set(Calendar.HOUR_OF_DAY, horarioCalendar.get(Calendar.HOUR_OF_DAY));
        dataAtual.set(Calendar.MINUTE, horarioCalendar.get(Calendar.MINUTE));
        dataAtual.set(Calendar.SECOND, horarioCalendar.get(Calendar.SECOND));

        return dataAtual.getTime();
    }

    public static Integer calcularIntervaloHoras(Date dateInicio, Date dataFim){
        Hours tempo = Hours.hoursBetween(new DateTime(dateInicio), new DateTime(dataFim));
        return tempo.getHours();
    }

    public static Integer calcularIntervaloMinutes(Date dateInicio, Date dataFim){
        Minutes tempo = Minutes.minutesBetween(new DateTime(dateInicio), new DateTime(dataFim));
        return tempo.getMinutes();
    }

    public static Date converteTimestampComMilissegundos(String data) {
        if (data == null || data.equals(""))
            return null;

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            date = (Date) formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date dateTime(Date date, Date time) {

        Calendar aDate = Calendar.getInstance();
        aDate.setTime(date);

        Calendar aTime = Calendar.getInstance();
        aTime.setTime(time);

        Calendar aDateTime = Calendar.getInstance();
        aDateTime.set(Calendar.DAY_OF_MONTH, aDate.get(Calendar.DAY_OF_MONTH));
        aDateTime.set(Calendar.MONTH, aDate.get(Calendar.MONTH));
        aDateTime.set(Calendar.YEAR, aDate.get(Calendar.YEAR));
        aDateTime.set(Calendar.HOUR_OF_DAY, aTime.get(Calendar.HOUR_OF_DAY));
        aDateTime.set(Calendar.MINUTE, aTime.get(Calendar.MINUTE));
        aDateTime.set(Calendar.SECOND, aTime.get(Calendar.SECOND));
        aDateTime.set(Calendar.SECOND, aTime.get(Calendar.SECOND));

        return aDateTime.getTime();
    }

    public static String dataFormatacaoPorIntervalo(Date dateInicio, Date dateFim) {

        Interval interval =
                new Interval(dateInicio.getTime(), dateFim.getTime());

        // fix by Antonio Junior 08/04/2022
        // necessario especificar o period type para correta formatacao dos intervalos
        // anteriormente o interval era iniciado assim
        // Period period = interval.toPeriod();

        Integer segundos = interval.toPeriod(PeriodType.seconds()).getSeconds();
        Integer minutos = interval.toPeriod(PeriodType.minutes()).getMinutes();
        Integer horas = interval.toPeriod(PeriodType.hours()).getHours();
        Integer dias = interval.toPeriod(PeriodType.days()).getDays();
        Integer meses = interval.toPeriod(PeriodType.months()).getMonths();
        Integer anos = interval.toPeriod(PeriodType.years()).getYears();

        //anos
        if(anos > 0){
            if(anos == 1){
                return anos+" ano";
            }

            return DateUtil.formataData(dateInicio);
        }
        //mes
        else if(meses > 0){

            if(meses == 1){
                return meses+" "+ Messages.get("mes");
            }
            return meses+" meses";
        }
        //dias
        else if(dias > 0){

            if(dias == 1){
                return dias+" dia";
            }
            return dias+" dias";
        }
        //hora
        else if(horas > 0){

            if(horas == 1){
                return horas+" hora";
            }
            return horas+" horas";
        }else if(minutos > 0){
            if(minutos == 1){
                return minutos+" minuto";
            }
            return minutos+" minutos";
        }else {

            if(segundos == 0){
                segundos = 1;
            }

            if(segundos == 1){
                return segundos+" segundo";
            }
            return segundos+" segundos";
        }
    }

    public static String formatarTempoPorSegundos(Long segundos, String caractereVazio) {
        if (segundos != null) {
            java.time.LocalTime localTime = java.time.LocalTime.ofSecondOfDay(segundos);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return localTime.format(formatter);
        } else {
            return caractereVazio;
        }
    }
}