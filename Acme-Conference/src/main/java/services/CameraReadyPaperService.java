
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CameraReadyPaperRepository;
import domain.CameraReadyPaper;

@Service
@Transactional
public class CameraReadyPaperService {

	@Autowired
	private CameraReadyPaperRepository	cameraReadyPaperRepository;


	public CameraReadyPaper save(final CameraReadyPaper camera) {
		final CameraReadyPaper res = this.cameraReadyPaperRepository.save(camera);
		this.cameraReadyPaperRepository.flush();
		return res;
	}

	public void flush() {
		this.cameraReadyPaperRepository.flush();
	}
}
