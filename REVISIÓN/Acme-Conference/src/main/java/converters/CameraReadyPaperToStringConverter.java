
package converters;

import org.springframework.core.convert.converter.Converter;

import domain.CameraReadyPaper;

public class CameraReadyPaperToStringConverter implements Converter<CameraReadyPaper, String> {

	@Override
	public String convert(final CameraReadyPaper c) {
		String result;

		if (c == null)
			result = null;
		else
			result = String.valueOf(c.getId());
		return result;
	}

}
