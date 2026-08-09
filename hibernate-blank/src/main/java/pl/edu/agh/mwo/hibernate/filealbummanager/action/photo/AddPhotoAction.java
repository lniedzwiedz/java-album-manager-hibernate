package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photo.AddPhotoHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.PhotoAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

import java.io.IOException;
import java.time.LocalDate;

public class AddPhotoAction {

    private final AlbumService albumService;
    private final PhotoService photoService;
    private final AddPhotoHandler addPhotoHandler;

    public AddPhotoAction(AlbumService albumService, PhotoService photoService, AddPhotoHandler addPhotoHandler) {

        this.albumService = albumService;
        this.photoService = photoService;
        this.addPhotoHandler = addPhotoHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null)
            return MenuResult.CONTINUE;

        System.out.println(PhotoMessages.ENTER_ALBUM_ADD_PHOTO);
        String albumName = reader.readLine();

        if (albumName == null || albumName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        if (albumService.albumExistsForUser(userLogged, albumName)) {
            System.out.println(String.format(PhotoMessages.PHOTO_ADD_FORBIDDEN, userLogged.getName()));
            return MenuResult.CONTINUE;
        }

        System.out.println(PhotoMessages.ADD_PHOTO_NAME);
        String photoName = reader.readLine();

        PhotoAddResult result = photoService.checkPhotoCanBeAdded(userLogged, albumName, photoName);
        if (result == PhotoAddResult.CAN_BE_ADDED) {

            Album album = albumService.getAlbum(albumName, userLogged.getId());
            Photo photo = new Photo();
            photo.setName(photoName);
            photo.setAlbumId(album.getId());
            photo.setDate(LocalDate.now().toString());
            photoService.addPhoto(photo);
        }
        return addPhotoHandler.handle(result, userLogged);
    }
}