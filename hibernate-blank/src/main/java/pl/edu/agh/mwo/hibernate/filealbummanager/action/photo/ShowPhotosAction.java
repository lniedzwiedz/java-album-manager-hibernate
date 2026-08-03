package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;

public class ShowPhotosAction {

    private final AlbumManagerService albumManager;
    private final PhotoManagerService photoManager;

    public ShowPhotosAction(
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
                Messages.ENTER_ALBUM_PHOTO
        );

        String albumName =
                br.readLine();

        if (
                albumManager.isAlbumBelongToUser(
                        userLogged,
                        albumName
                )
        ) {

            photoManager.printPhoto(
                    userLogged,
                    albumName
            );

        } else {

            System.out.println(
                    Messages.ALBUM_NOT_FOUND
            );
        }
    }
}