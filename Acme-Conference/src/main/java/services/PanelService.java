
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PanelRepository;
import domain.Panel;

@Service
@Transactional
public class PanelService {

	@Autowired
	private PanelRepository	panelRepository;


	public Panel findPanelByActivityId(final int id) {
		return this.panelRepository.findPanelByActivityId(id);
	}
}
