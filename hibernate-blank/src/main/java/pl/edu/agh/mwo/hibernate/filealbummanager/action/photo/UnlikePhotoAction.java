package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.PhotoLikeStatus;

import java.io.BufferedReader;
import java.io.IOException;

public class UnlikePhotoAction {

    private final PhotoManagerService photoManager;

    public UnlikePhotoAction(
            PhotoManagerService photoManager
    ) {
        this.photoManager = photoManager;
    }

    public void execute(
            BufferedReader br,
            User userLogged
    ) throws IOException {

        System.out.println(
                Messages.REMOVE_PHOTO_LIKE_NAME
        );

        String photoName =
                br.readLine();

        System.out.println(
                Messages.ALBUM_NAME
        );

        String albumName =
                br.readLine();

        PhotoLikeStatus unlikeResult =
                PhotoLikeStatus.fromInt(
                        photoManager.getProcessingStatusForPhotoLike(
                                userLogged,
                                albumName,
                                photoName
                        )
                );

        if (
                unlikeResult ==
                        PhotoLikeStatus.NEVER_LIKED
        ) {

            System.out.println(
                    Messages.NEVER_LIKED_PHOTO
            );

        } else if (
                unlikeResult ==
                        PhotoLikeStatus.ALREADY_LIKED
        ) {

            photoManager.deletePhotoLike(
                    userLogged,
                    albumName,
                    photoName
            );

            System.out.println(
                    Messages.PHOTO_LIKE_REMOVED
            );

        } else if (
                unlikeResult ==
                        PhotoLikeStatus.PHOTO_NOT_IN_ALBUM
        ) {

            System.out.println(
                    Messages.PHOTO_NOT_IN_ALBUM
            );

        } else if (
                unlikeResult ==
                        PhotoLikeStatus.ALBUM_DOES_NOT_EXIST
        ) {

            System.out.println(
                    Messages.ALBUM_DOES_NOT_EXIST
            );

        } else if (
                unlikeResult ==
                        PhotoLikeStatus.NOT_FRIEND_PHOTO_OWNER
        ) {

            System.out.println(
                    Messages.NOT_FRIEND_PHOTO_OWNER_NO_LIKE
            );
        }
    }
}