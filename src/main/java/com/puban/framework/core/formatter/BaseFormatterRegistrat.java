package com.puban.framework.core.formatter;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class BaseFormatterRegistrat implements FormatterRegistrar
{
	private String datePattern;
	
	public BaseFormatterRegistrat(String datePattern)
	{
		this.datePattern=datePattern;
	}
	
	@Override
	public void registerFormatters(FormatterRegistry registry)
	{
		registry.addFormatter(new DateFormatter(datePattern));
		registry.addFormatter(new IntegerFormatter());
		registry.addFormatter(new FloatFormatter());
		registry.addFormatter(new DoubleFormatter());
		registry.addFormatter(new LongFormatter());
		//register more formatters here
	} 

}
