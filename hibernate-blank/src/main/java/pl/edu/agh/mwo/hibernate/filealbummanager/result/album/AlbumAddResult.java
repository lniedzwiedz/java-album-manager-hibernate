package pl.edu.agh.mwo.hibernate.filealbummanager.result.album;

public class AlbumAddResult {

    private final AlbumAddStatus status;
    private final String albumName;

    public AlbumAddResult(AlbumAddStatus status, String albumName) {
        this.status = status;
        this.albumName = albumName;
    }

    public AlbumAddStatus getStatus() {
        return status;
    }

    public String getAlbumName() {
        return albumName;
    }
}