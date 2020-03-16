package com.apirest.MedicamentoSolidario.config;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.apirest.MedicamentoSolidario.errors.ResourceNotFoundException;

public class VerificaData {
	
	public void isDateValid(Date date) {
		DateFormat df = new SimpleDateFormat ("yyyy/MM/dd");
		String strData =df.format(date);
		df.setLenient (false); // aqui o pulo do gato
		try {
		    df.parse (strData);
		    // data válida
		} catch (ParseException ex) {
			throw new ResourceNotFoundException(" Esta data é invalida : "+"' " +date+" '");
		}
	}

}
