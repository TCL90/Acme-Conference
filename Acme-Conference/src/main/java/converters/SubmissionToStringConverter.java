
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Submission;

@Component
@Transactional
public class SubmissionToStringConverter implements Converter<Submission, String> {

	@Override
	public String convert(final Submission m) {
		String result;

		if (m == null)
			result = null;
		else
			result = String.valueOf(m.getId());
		return result;
	}

}
