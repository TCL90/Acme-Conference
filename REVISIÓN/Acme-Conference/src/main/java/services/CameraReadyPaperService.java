
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CameraReadyPaperRepository;
import domain.CameraReadyPaper;

@Service
@Transactional
public class CameraReadyPaperService {

	@Autowired
	private CameraReadyPaperRepository	cameraReadyPaperRepository;

	@Autowired
	private AdministratorService		administratorService;


	public Collection<CameraReadyPaper> findAllByConferenceId(final int conferenceId) {
		Assert.isTrue(this.administratorService.checkAdmin());
		return this.cameraReadyPaperRepository.findAllByConferenceId(conferenceId);
	}

	public Collection<CameraReadyPaper> findAllByConferenceIdAdmin(final int conferenceId) {
		Assert.isTrue(this.administratorService.checkAdminActor());
		return this.cameraReadyPaperRepository.findAllByConferenceId(conferenceId);
	}
	
	public CameraReadyPaper save(final CameraReadyPaper camera) {
		final CameraReadyPaper res = this.cameraReadyPaperRepository.save(camera);
		this.cameraReadyPaperRepository.flush();
		return res;
	}

	public void flush() {
		this.cameraReadyPaperRepository.flush();
	}

	public CameraReadyPaper findOne(final int paperId) {
		Assert.isTrue(this.administratorService.checkAdmin());
		return this.cameraReadyPaperRepository.findOne(paperId);

	}

	public CameraReadyPaper findOneAdmin(final int paperId) {
		Assert.isTrue(this.administratorService.checkAdminActor());
		return this.cameraReadyPaperRepository.findOne(paperId);

	}
	
	public Collection<CameraReadyPaper> findAll() {
		return this.cameraReadyPaperRepository.findAll();
	}

	public Collection<CameraReadyPaper> findByAuthorId(final int authorId) {
		return this.cameraReadyPaperRepository.findByAuthorId(authorId);
	}
}
