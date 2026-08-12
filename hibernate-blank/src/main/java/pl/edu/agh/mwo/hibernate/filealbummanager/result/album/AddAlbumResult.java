package pl.edu.agh.mwo.hibernate.filealbummanager.result.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.status.album.AddAlbumStatus;

public class AddAlbumResult {

    private final AddAlbumStatus status;
    private final String albumName;

    public AddAlbumResult(AddAlbumStatus status, String albumName) {
        this.status = status;
        this.albumName = albumName;
    }

    public AddAlbumStatus getStatus() {
        return status;
    }

    public String getAlbumName() {
        return albumName;
    }
}