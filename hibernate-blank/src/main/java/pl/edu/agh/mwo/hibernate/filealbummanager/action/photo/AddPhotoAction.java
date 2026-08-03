package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;

public class AddPhotoAction {

    private final AlbumManagerService albumManager;
    private final PhotoManagerService photoManager;

    public AddPhotoAction(
            AlbumManagerService albumManager,
            PhotoManagerService photoManager
    ) {
        this.albumManager = albumManager;
        this.photoManager = photoManager;
    }

    public void execute(
            BufferedReader br,
            User userLogged
    ) throws IOException {

        System.out.println(
                Messages.ENTER_ALBUM_ADD_PHOTO
        );

        String albumName =
                br.readLine();

        if (
                !albumManager.isAlbumBelongToUser(
                        userLogged,
                        albumName
                )
        ) {

            System.out.println(
                    String.format(
                            Messages.PHOTO_ADD_FORBIDDEN,
                            userLogged.getName()
                    )
            );

            return;
        }

        System.out.println(
                Messages.ADD_PHOTO_NAME
        );

        String photoName =
                br.readLine();

        int photoResult =
                photoManager
                        .getProcessingStatusWhileAddingPhoto(
                                userLogged,
                                albumName,
                                photoName
                        );

        if (photoResult == 1) {

            photoManager.addPhoto(
                    photoName,
                    albumName,
                    userLogged
            );

            System.out.println(
                    Messages.PHOTO_ADDED
            );

        } else if (photoResult == 2) {

            System.out.println(
                    Messages.PHOTO_EXISTS
            );
        }
    }
}