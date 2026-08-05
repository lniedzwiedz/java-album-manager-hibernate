package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.photo.PhotoLikeMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.photo.PhotoMessages;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ShowPhotosAction {

    private final AlbumService albumService;
    private final PhotoService photoService;

    public ShowPhotosAction(AlbumService albumService, PhotoService photoService) {
        this.albumService = albumService;
        this.photoService = photoService;
    }

    public void execute(BufferedReader br, User userLogged) throws IOException {
        if (userLogged == null)
            return;

        System.out.println(PhotoMessages.ENTER_ALBUM_PHOTO);

        String albumName = br.readLine();
        if (!albumService.isAlbumBelongToUser(userLogged, albumName)) {
            System.out.println(AlbumMessages.ALBUM_NOT_FOUND);
            return;
        }

        List<Photo> photos = photoService.getPhotosForUserAlbum(userLogged, albumName);
        for (Photo photo : photos) {
            System.out.println(photo);
            System.out.println(String.format(PhotoLikeMessages.PHOTO_LIKES, photoService.countedPhotoLikes(photo)));
        }
    }
}