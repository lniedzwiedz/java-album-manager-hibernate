package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.AlbumAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.album.AlbumMessages;

import java.io.BufferedReader;
import java.io.IOException;

public class AddAlbumAction {

    private final AlbumService albumService;

    public AddAlbumAction(AlbumService albumService) {
        this.albumService = albumService;
    }

    public void execute(BufferedReader br, User userLogged) throws IOException {
        System.out.println(AlbumMessages.ADD_ALBUM_NAME);

        String albumName = br.readLine();
        AlbumAddResult result = albumService.getProcessingStatusWhileAddingAlbum(userLogged, albumName);

        switch (result) {

            case CAN_BE_ADDED -> {
                albumService.createNewAlbum(userLogged, albumName);
                System.out.println(AlbumMessages.ALBUM_ADDED);
            }

            case ALREADY_EXISTS -> System.out.println(AlbumMessages.ALBUM_EXISTS);

            case INVALID_USER -> System.out.println(AccountMessages.USER_NOT_FOUND);
        }
    }
}