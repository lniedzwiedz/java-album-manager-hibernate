package pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike;

public class PhotoLikeDeleteResult {

    private final PhotoLikeDeleteStatus status;
    private final String friendName;
    private final String albumName;
    private final String photoName;

    public PhotoLikeDeleteResult(PhotoLikeDeleteStatus status, String friendName, String albumName, String photoName) {
        this.status = status;
        this.friendName = friendName;
        this.albumName = albumName;
        this.photoName = photoName;
    }

    public PhotoLikeDeleteStatus getStatus() {
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