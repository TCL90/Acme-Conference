
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SponsorRepository;
import domain.Sponsor;

@Component
@Transactional
public class StringToSponsorConverter implements Converter<String, Sponsor> {

	@Autowired
	private SponsorRepository	ar;


	@Override
	public Sponsor convert(final String text) {
		Sponsor result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.ar.findOne(id);
				if (result == null)
					result = this.ar.findOne(id);

			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
