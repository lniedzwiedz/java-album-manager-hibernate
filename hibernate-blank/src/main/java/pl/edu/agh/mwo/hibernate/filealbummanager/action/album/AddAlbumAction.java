package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.AlbumAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

import java.io.IOException;

public class AddAlbumAction {

    private final AlbumService albumService;

    public AddAlbumAction(AlbumService albumService) {
        this.albumService = albumService;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        System.out.println(AlbumMessages.ADD_ALBUM_NAME);

        String albumName = reader.readLine();
        if (albumName == null || albumName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        AlbumAddResult result = albumService.getProcessingStatusWhileAddingAlbum(userLogged, albumName);
        if (userLogged != null) {

            switch (result) {

                case CAN_BE_ADDED -> {
                    albumService.createNewAlbum(userLogged, albumName);
                    System.out.println(AlbumMessages.ALBUM_ADDED);
                }

                case ALREADY_EXISTS -> System.out.println(AlbumMessages.ALBUM_EXISTS);

                case INVALID_USER -> System.out.println(AccountMessages.USER_NOT_FOUND);
            }
        }
        return MenuResult.CONTINUE;
    }
}