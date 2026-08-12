package pl.edu.agh.mwo.hibernate.filealbummanager.handler.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.DeleteAlbumResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;

public class DeleteAlbumHandler {

    public void handle(DeleteAlbumResult result) {
        if (result == null)
            return;

        switch (result.getStatus()) {
            case LOGGED_USER_NOT_FOUND:
                System.out.println(AlbumMessages.LOGGED_USER_NOT_FOUND);
                break;

            case ALBUM_DATA_NOT_FOUND:
                System.out.println(AlbumMessages.ALBUM_DATA_NOT_FOUND);
                break;

            case ALBUM_NOT_FOUND:
                System.out.println(String.format(AlbumMessages.ALBUM_NOT_FOUND, result.getAlbumName()));
                break;

            case ALBUM_NOT_OWNED_BY_USER:
                System.out.println(String.format(AlbumMessages.ALBUM_NOT_OWNED_BY_USER, result.getAlbumName()));
                break;

            case ALBUM_DELETED:
                System.out.println(String.format(AlbumMessages.ALBUM_DELETED, result.getAlbumName()));
                break;
        }
    }
}