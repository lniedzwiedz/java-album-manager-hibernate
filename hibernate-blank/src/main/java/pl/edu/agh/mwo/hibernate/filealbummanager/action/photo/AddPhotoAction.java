package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photo.AddPhotoHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.PhotoAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoLikeMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

import java.io.IOException;

public class AddPhotoAction {

    private final AlbumService albumService;
    private final PhotoService photoService;
    private final AddPhotoHandler addPhotoHandler;

    public AddPhotoAction(AlbumService albumService,
                          PhotoService photoService,
                          AddPhotoHandler addPhotoHandler) {
        this.albumService = albumService;
        this.photoService = photoService;
        this.addPhotoHandler = addPhotoHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null || userLogged.getId() <= 0) {
            System.out.println(PhotoMessages.LOGGED_USER_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        System.out.println(PhotoMessages.ENTER_ALBUM_ADD_PHOTO);
        String albumName = reader.readLine();

        if (albumName == null || albumName.isBlank()) {
            System.out.println(PhotoMessages.ALBUM_DATA_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        Album album = albumService.getAlbum(albumName, userLogged.getId());
        if (album == null || album.getId() <= 0) {
            System.out.println(PhotoMessages.ALBUM_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        System.out.println(PhotoMessages.ADD_PHOTO_NAME);
        String photoName = reader.readLine();

        if (photoName == null || photoName.isBlank()) {
            System.out.println(PhotoMessages.ALBUM_DATA_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        PhotoAddResult result = photoService.addPhoto(userLogged, album, photoName);
        return addPhotoHandler.handle(result, userLogged);
    }
}