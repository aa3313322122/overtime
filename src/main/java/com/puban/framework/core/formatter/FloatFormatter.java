package com.puban.framework.core.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class FloatFormatter implements Formatter<Float>
{

	@Override
	public String print(Float object, Locale locale)
	{
		return String.valueOf(object);
	}

	@Override
	public Float parse(String source, Locale locale) throws ParseException
	{
		return Float.parseFloat(source);
	}

}
