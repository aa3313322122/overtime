package com.puban.framework.core.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class LongFormatter implements Formatter<Long>
{

	@Override
	public String print(Long object, Locale locale)
	{
		return String.valueOf(object);
	}

	@Override
	public Long parse(String source, Locale locale) throws ParseException
	{
		return Long.parseLong(source);
	}

}
