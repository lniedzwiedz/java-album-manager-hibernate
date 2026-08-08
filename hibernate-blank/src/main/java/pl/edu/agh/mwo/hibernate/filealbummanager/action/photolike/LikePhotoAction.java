package pl.edu.agh.mwo.hibernate.filealbummanager.action.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
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
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoLikeStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

import java.io.IOException;

public class LikePhotoAction {

    private final AlbumService albumService;
    private final PhotoService photoService;
    private final PhotoLikeService photoLikeService;

    public LikePhotoAction(AlbumService albumService, PhotoService photoService, PhotoLikeService photoLikeService) {
        this.albumService = albumService;
        this.photoService = photoService;
        this.photoLikeService = photoLikeService;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null) return MenuResult.CONTINUE;

        System.out.println(PhotoLikeMessages.ADD_LIKE_PHOTO_NAME);

        String photoName = reader.readLine();
        if (photoName == null || photoName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        System.out.println(PhotoLikeMessages.ALBUM_NAME_LIKE);

        String albumName = reader.readLine();
        if (albumName == null || albumName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        PhotoLikeStatus likeResult = photoLikeService.checkPhotoLikeStatus(userLogged, albumName, photoName);
        if (likeResult == null) {
            System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
            return MenuResult.CONTINUE;
        }

        switch (likeResult) {

            case NEVER_LIKED:
//                photoService.addPhotoLike(userLogged, albumName, photoName);
//                System.out.println(PhotoLikeMessages.PHOTO_LIKE_ADDED);
//                break;
            {

                Album album = albumService.getAlbum(albumName, userLogged.getId());

                if (album == null) {
                    System.out.println(AlbumMessages.ALBUM_DOES_NOT_EXIST);
                    return MenuResult.CONTINUE;
                }

                Photo photo = photoService.getPhotoFromDatabase(photoName, album.getId());

                if (photo == null) {
                    System.out.println(PhotoMessages.PHOTO_NOT_IN_ALBUM);
                    return MenuResult.CONTINUE;
                }

                boolean added = photoLikeService.addPhotoLike(photo, userLogged);

                if (added) {
                    System.out.println(PhotoLikeMessages.PHOTO_LIKE_ADDED);
                } else {
                    System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
                }

                break;
            }

            case ALREADY_LIKED:
                System.out.println(String.format(PhotoLikeMessages.ALREADY_LIKE_PHOTO, userLogged.getName()));
                break;

            case PHOTO_NOT_IN_ALBUM:
                System.out.println(PhotoMessages.PHOTO_NOT_IN_ALBUM);
                break;

            case ALBUM_DOES_NOT_EXIST:
                System.out.println(AlbumMessages.ALBUM_DOES_NOT_EXIST);
                break;

            case NOT_FRIEND_PHOTO_OWNER:
                System.out.println(PhotoLikeMessages.NOT_FRIEND_PHOTO_OWNER);
                break;

            default:
                System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
                break;
        }
        return MenuResult.CONTINUE;
    }
}