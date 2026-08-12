package pl.edu.agh.mwo.hibernate.filealbummanager.result.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.status.photo.AddPhotoStatus;

public class AddPhotoResult {

    private final AddPhotoStatus status;
    private final String photoName;

    public AddPhotoResult(AddPhotoStatus status, String photoName) {
        this.status = status;
        this.photoName = photoName;
    }

    public AddPhotoStatus getStatus() {
        return status;
    }

    public String getPhotoName() {
        return photoName;
    }
}