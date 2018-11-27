package ClassesAuxiliares;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ManipulandoData {
	public static int pegaMes(){
		Date data = new Date();
		GregorianCalendar dataCal = new GregorianCalendar();
		dataCal.setTime(data);
		int mes = dataCal.get(Calendar.MONTH)+1;
		int ano = dataCal.get(Calendar.YEAR);
		return mes;
	}
	public static int pegaAno(){
		Date data = new Date();
		GregorianCalendar dataCal = new GregorianCalendar();
		dataCal.setTime(data);
		int ano = dataCal.get(Calendar.YEAR);
		return ano;
	}
	public static String retornaMes(int mes) {
		if(mes == 1)
			return "Janeiro";
		if(mes == 2)
			return "Fevereiro";
		if(mes == 3)
			return "Março";
		if(mes == 4)
			return "Abril";
		if(mes == 5)
			return "Maio";
		if(mes == 6)
			return "Junho";
		if(mes == 7)
			return "Julho";
		if(mes == 8)
			return "Agosto";
		if(mes == 9)
			return "Setembro";
		if(mes == 10)
			return "Outubro";
		if(mes == 11)
			return "Novembro";
		
			return "Dezembro";
	}
	public static java.sql.Date convDataBanco(Date dataSistema) {
	    
	    java.util.Date dataUtil = dataSistema;
	    java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
	    
	    return dataSql;
	}
	public static Date dataAtual(){
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		return calendar.getTime();
	}
}
