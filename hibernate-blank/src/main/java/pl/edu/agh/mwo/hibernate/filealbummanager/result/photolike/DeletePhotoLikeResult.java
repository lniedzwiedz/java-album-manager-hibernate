package pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.status.photolike.DeletePhotoLikeStatus;

public class DeletePhotoLikeResult {

    private final DeletePhotoLikeStatus status;
    private final String friendName;
    private final String albumName;
    private final String photoName;

    public DeletePhotoLikeResult(DeletePhotoLikeStatus status, String friendName, String albumName, String photoName) {
        this.status = status;
        this.friendName = friendName;
        this.albumName = albumName;
        this.photoName = photoName;
    }

    public DeletePhotoLikeStatus getStatus() {
        return status;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getPhotoName() {
        return photoName;
    }
}