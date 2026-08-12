package pl.edu.agh.mwo.hibernate.filealbummanager.result.photo;

public class PhotoDeleteResult {

    private final PhotoDeleteStatus status;
    private final String photoName;

    public PhotoDeleteResult(PhotoDeleteStatus status, String photoName) {
        this.status = status;
        this.photoName = photoName;
    }

    public PhotoDeleteStatus getStatus() {
        return status;
    }

    public String getPhotoName() {
        return photoName;
    }
}