package pl.edu.agh.mwo.hibernate.filealbummanager.result.album;

public class AlbumDeleteResult {

    private final AlbumDeleteStatus status;
    private final String albumName;

    public AlbumDeleteResult(AlbumDeleteStatus status, String albumName) {
        this.status = status;
        this.albumName = albumName;
    }

    public AlbumDeleteStatus getStatus() {
        return status;
    }

    public String getAlbumName() {
        return albumName;
    }
}