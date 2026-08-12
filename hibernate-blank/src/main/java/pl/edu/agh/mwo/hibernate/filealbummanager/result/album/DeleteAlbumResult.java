package pl.edu.agh.mwo.hibernate.filealbummanager.result.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.status.album.DeleteAlbumStatus;

public class DeleteAlbumResult {

    private final DeleteAlbumStatus status;
    private final String albumName;

    public DeleteAlbumResult(DeleteAlbumStatus status, String albumName) {
        this.status = status;
        this.albumName = albumName;
    }

    public DeleteAlbumStatus getStatus() {
        return status;
    }

    public String getAlbumName() {
        return albumName;
    }
}