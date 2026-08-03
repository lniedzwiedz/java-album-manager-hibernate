package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;

public class DeletePhotoAction {

    private final AlbumManagerService albumManager;
    private final PhotoManagerService photoManager;

    public DeletePhotoAction(
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
                Messages.REMOVE_PHOTO_NAME
        );

        String photoName =
                br.readLine();

        System.out.println(
                Messages.ALBUM_NAME
        );

        String albumName =
                br.readLine();

        boolean albumBelongs =
                albumManager.isAlbumBelongToUser(
                        userLogged,
                        albumName
                );

        boolean photoBelongs =
                photoManager.isPictureBelongToUser(
                        userLogged,
                        albumName,
                        photoName
                );

        if (
                albumBelongs &&
                        photoBelongs
        ) {

            photoManager.deletePhoto(
                    photoName,
                    albumName,
                    userLogged
            );

            System.out.println(
                    Messages.PICTURE_DELETED
            );

        } else {

            System.out.println(
                    String.format(
                            Messages.PICTURE_DELETE_FORBIDDEN,
                            userLogged.getName()
                    )
            );
        }
    }
}