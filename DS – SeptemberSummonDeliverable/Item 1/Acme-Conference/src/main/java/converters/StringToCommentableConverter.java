
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.CommentableRepository;
import repositories.ConferenceRepository;
import repositories.PanelRepository;
import repositories.PresentationRepository;
import repositories.ReportRepository;
import repositories.TutorialRepository;
import domain.Commentable;

@Component
@Transactional
public class StringToCommentableConverter implements Converter<String, Commentable> {

	@Autowired
	CommentableRepository	mr;

	@Autowired
	ConferenceRepository	cr;

	@Autowired
	PanelRepository			pr;

	@Autowired
	PresentationRepository	pr2;

	@Autowired
	TutorialRepository		tr;

	@Autowired
	ReportRepository		rr;


	@Override
	public Commentable convert(final String text) {
		Commentable result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.tr.findOne(id);
				if (result == null)
					result = this.cr.findOne(id);
				if (result == null)
					result = this.mr.findOne(id);
				if (result == null)
					result = this.pr.findOne(id);
				if (result == null)
					result = this.pr2.findOne(id);
				if (result == null)
					result = this.rr.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;

	}
}
