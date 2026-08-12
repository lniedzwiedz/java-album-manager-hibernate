package pl.edu.agh.mwo.hibernate.filealbummanager.result.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.status.photo.DeletePhotoStatus;

public class DeletePhotoResult {

    private final DeletePhotoStatus status;
    private final String photoName;

    public DeletePhotoResult(DeletePhotoStatus status, String photoName) {
        this.status = status;
        this.photoName = photoName;
    }

    public DeletePhotoStatus getStatus() {
        return status;
    }

    public String getPhotoName() {
        return photoName;
    }
}