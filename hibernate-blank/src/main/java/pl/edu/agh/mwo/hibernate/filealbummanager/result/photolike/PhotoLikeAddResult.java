package pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike;

public class PhotoLikeAddResult {

    private final PhotoLikeAddStatus status;
    private final String userName;
    private final String albumName;
    private final String photoName;

    public PhotoLikeAddResult(PhotoLikeAddStatus status, String userName, String albumName, String photoName) {
        this.status = status;
        this.userName = userName;
        this.albumName = albumName;
        this.photoName = photoName;
    }

    public PhotoLikeAddStatus getStatus() {
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