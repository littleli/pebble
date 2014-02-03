package com.mitchellbosecke.pebble.extension.core;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.extension.LocaleAware;
import com.mitchellbosecke.pebble.extension.NamedArguments;

public class NumberFormatFilter implements Filter, LocaleAware, NamedArguments {

	private Locale locale;
	
	@Override
	public void setLocale(Locale locale){
		this.locale = locale;
	}
	
	@Override
	public List<String> getArgumentNames() {
		List<String> names = new ArrayList<>();
		names.add("format");
		return names;
	}

	@Override
	public Object apply(Object input, Map<String, Object> args) {
		if (input == null) {
			return null;
		}
		Number number = (Number) input;

		if (args.get("format") != null) {
			Format format = new DecimalFormat((String) args.get("format"));
			return format.format(number);
		} else {
			NumberFormat numberFormat = NumberFormat.getInstance(locale);
			return numberFormat.format(number);
		}
	}

}