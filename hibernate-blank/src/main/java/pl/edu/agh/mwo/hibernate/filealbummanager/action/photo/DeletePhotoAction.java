package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

import java.io.IOException;

public class DeletePhotoAction {

    private final AlbumService albumService;
    private final PhotoService photoService;


    public DeletePhotoAction(AlbumService albumService, PhotoService photoService) {
        this.photoService = photoService;
        this.albumService = albumService;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null) return MenuResult.CONTINUE;

        System.out.println(PhotoMessages.REMOVE_PHOTO_NAME);

        String photoName = reader.readLine();
        if (photoName == null || photoName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        System.out.println(AlbumMessages.ALBUM_NAME);

        String albumName = reader.readLine();
        if (albumName == null || albumName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        Album album = albumService.getAlbum(albumName, userLogged.getId());
        if (album == null) {
            System.out.println(String.format(PhotoMessages.PHOTO_DELETE_FORBIDDEN, userLogged.getName()));
            return MenuResult.CONTINUE;
        }

        Photo photo = photoService.getPhoto(photoName, album.getId());
        if (photo == null) {
            System.out.println(String.format(PhotoMessages.PHOTO_DELETE_FORBIDDEN, userLogged.getName()));
            return MenuResult.CONTINUE;
        }

        boolean deleted = photoService.deletePhoto(photo);
        if (deleted) {
            System.out.println(PhotoMessages.PHOTO_DELETED);
        } else {
            System.out.println(PhotoMessages.PHOTO_DELETE_FORBIDDEN);
        }
        return MenuResult.CONTINUE;
    }
}