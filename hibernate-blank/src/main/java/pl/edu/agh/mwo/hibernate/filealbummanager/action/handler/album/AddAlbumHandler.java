package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;

public class AddAlbumHandler {

    public void handle(AlbumAddResult result) {
        if (result == null)
            return;

        switch (result.getStatus()) {
            case ALBUM_ADDED:
                System.out.println(String.format(AlbumMessages.ALBUM_ADDED, result.getAlbumName()));
                break;

            case ALBUM_ALREADY_EXISTS:
                System.out.println(String.format(AlbumMessages.ALBUM_ALREADY_EXISTS, result.getAlbumName()));
                break;

            case ALBUM_DATA_NOT_FOUND:
                System.out.println(AlbumMessages.ALBUM_DATA_NOT_FOUND);
                break;

            case LOGGED_USER_NOT_FOUND:
                System.out.println(AlbumMessages.LOGGED_USER_NOT_FOUND);
                break;
        }
    }
}