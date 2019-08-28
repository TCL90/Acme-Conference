
package converters;

import org.springframework.core.convert.converter.Converter;

import domain.Category;

public class CategoryToStringConverter implements Converter<Category, String> {

	@Override
	public String convert(final Category c) {
		String result;

		if (c == null)
			result = null;
		else
			result = String.valueOf(c.getId());
		return result;
	}

}
