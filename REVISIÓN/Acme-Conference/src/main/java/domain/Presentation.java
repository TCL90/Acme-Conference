
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Presentation extends Activity {

	private CameraReadyPaper	cameraReadyPaper;


	@Valid
	@ManyToOne(optional = false)
	public CameraReadyPaper getCameraReadyPaper() {
		return this.cameraReadyPaper;
	}

	public void setCameraReadyPaper(final CameraReadyPaper cameraReadyPaper) {
		this.cameraReadyPaper = cameraReadyPaper;
	}

}
