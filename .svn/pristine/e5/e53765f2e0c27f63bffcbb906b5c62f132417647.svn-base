package com.puban.framework.core.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class IntegerFormatter implements Formatter<Integer>
{

	@Override
	public String print(Integer object, Locale locale)
	{
		return String.valueOf(object);
	}

	@Override
	public Integer parse(String source, Locale locale) throws ParseException
	{
		return Integer.parseInt(source);
	}

}
