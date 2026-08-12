package pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.status.photolike.AddPhotoLikeStatus;

public class AddPhotoLikeResult {

    private final AddPhotoLikeStatus status;
    private final String userName;
    private final String albumName;
    private final String photoName;

    public AddPhotoLikeResult(AddPhotoLikeStatus status, String userName, String albumName, String photoName) {
        this.status = status;
        this.userName = userName;
        this.albumName = albumName;
        this.photoName = photoName;
    }

    public AddPhotoLikeStatus getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getPhotoName() {
        return photoName;
    }
}