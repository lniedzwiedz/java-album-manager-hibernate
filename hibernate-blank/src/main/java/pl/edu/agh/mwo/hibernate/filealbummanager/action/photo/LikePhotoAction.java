package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.PhotoLikeStatus;

import java.io.BufferedReader;
import java.io.IOException;

public class LikePhotoAction {

    private final PhotoManagerService photoManager;

    public LikePhotoAction(
            PhotoManagerService photoManager
    ) {
        this.photoManager = photoManager;
    }

    public void execute(
            BufferedReader br,
            User userLogged
    ) throws IOException {

        System.out.println(
                Messages.ADD_LIKE_PHOTO_NAME
        );

        String photoName =
                br.readLine();

        System.out.println(
                Messages.ALBUM_NAME_LIKE
        );

        String albumName =
                br.readLine();

        PhotoLikeStatus likeResult =
                PhotoLikeStatus.fromInt(
                        photoManager.getProcessingStatusForPhotoLike(
                                userLogged,
                                albumName,
                                photoName
                        )
                );

        if (
                likeResult ==
                        PhotoLikeStatus.NEVER_LIKED
        ) {

            photoManager.addPhotoLike(
                    userLogged,
                    albumName,
                    photoName
            );

            System.out.println(
                    Messages.PHOTO_LIKE_ADDED
            );

        } else if (
                likeResult ==
                        PhotoLikeStatus.ALREADY_LIKED
        ) {

            System.out.println(
                    String.format(
                            Messages.ALREADY_LIKE_PHOTO,
                            userLogged.getName()
                    )
            );

        } else if (
                likeResult ==
                        PhotoLikeStatus.PHOTO_NOT_IN_ALBUM
        ) {

            System.out.println(
                    Messages.PHOTO_NOT_IN_ALBUM
            );

        } else if (
                likeResult ==
                        PhotoLikeStatus.ALBUM_DOES_NOT_EXIST
        ) {

            System.out.println(
                    Messages.ALBUM_DOES_NOT_EXIST
            );

        } else if (
                likeResult ==
                        PhotoLikeStatus.NOT_FRIEND_PHOTO_OWNER
        ) {

            System.out.println(
                    Messages.NOT_FRIEND_PHOTO_OWNER
            );
        }
    }
}