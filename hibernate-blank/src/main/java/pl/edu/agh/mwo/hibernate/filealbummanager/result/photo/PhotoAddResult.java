package pl.edu.agh.mwo.hibernate.filealbummanager.result.photo;

public class PhotoAddResult {

    private final PhotoAddStatus status;
    private final String photoName;

    public PhotoAddResult(PhotoAddStatus status, String photoName) {
        this.status = status;
        this.photoName = photoName;
    }

    public PhotoAddStatus getStatus() {
        return status;
    }

    public String getPhotoName() {
        return photoName;
    }
}