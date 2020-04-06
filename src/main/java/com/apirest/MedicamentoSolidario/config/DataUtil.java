package com.apirest.MedicamentoSolidario.config;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;

public class DataUtil {
	
//	public void isDateValid(Date date) {
//		DateFormat df = new SimpleDateFormat ("yyyy/MM/dd");
//		String strData =df.format(date);
//		df.setLenient (false); // aqui o pulo do gato
//		try {
//		    df.parse (strData);
//		    // data válida
//		} catch (ParseException ex) {
//			throw new ResourceNotFoundException(" Esta data é invalida : "+"' " +date+" '");
//		}
//	}
	public void isDateValid(String dataString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false); // aqui o pulo do gato
		try {
			sdf.parse(dataString);
			// data válida
		} catch (ParseException ex) {
			throw new ResourceNotFoundException(
					"Data é invalida : " + "'" + dataString + "'" + " Formato valido 'yyyy-MM-dd'");
		}
	}
	public LocalDate converterData(String dataStr) {
		LocalDate data;
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		data = LocalDate.parse(dataStr, formato); 
		return data ;
	}

}
