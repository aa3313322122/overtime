package com.puban.framework.core.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DoubleFormatter implements Formatter<Double>
{

	@Override
	public String print(Double object, Locale locale)
	{
		return String.valueOf(object);
	}

	@Override
	public Double parse(String source, Locale locale) throws ParseException
	{
		return Double.parseDouble(source);
	}

}
