package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoLikeService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoLikeMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

import java.io.IOException;
import java.util.List;

public class ShowPhotosAction {

    private final AlbumService albumService;
    private final PhotoService photoService;
    private final PhotoLikeService photoLikeService;

    public ShowPhotosAction(AlbumService albumService,
                            PhotoService photoService,
                            PhotoLikeService photoLikeService) {
        this.albumService = albumService;
        this.photoService = photoService;
        this.photoLikeService = photoLikeService;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null)
            return MenuResult.CONTINUE;

        System.out.println(PhotoMessages.ENTER_ALBUM_PHOTO);
        String albumName = reader.readLine();
        if (albumName == null || albumName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        if (albumService.albumExistsForUser(userLogged, albumName)) {
            System.out.println(AlbumMessages.ALBUM_OR_PHOTO_NOT_EXIST);
            return MenuResult.CONTINUE;
        }

        List<Photo> photos = photoService.getPhotos(userLogged, albumName);
        for (Photo photo : photos) {
            System.out.println(photo);
            System.out.println(String.format(
                    PhotoLikeMessages.PHOTO_LIKES,
                    photoLikeService.countPhotoLikes(photo)));
        }
        return MenuResult.CONTINUE;
    }
}