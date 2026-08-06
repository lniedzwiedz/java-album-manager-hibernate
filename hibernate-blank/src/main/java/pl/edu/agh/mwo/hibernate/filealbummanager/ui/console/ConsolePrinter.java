package pl.edu.agh.mwo.hibernate.filealbummanager.ui.console;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

import java.util.List;

public class ConsolePrinter {

    public void printApplicationTitle() {
        System.out.println();
        System.out.println(ApplicationMessages.ALBUM_MANAGER_TITLE);
    }

    public void printMenu() {
        System.out.println();
        System.out.println(ApplicationMessages.MENU_HEADER);
        System.out.println(ApplicationMessages.MENU_OPTIONS);
    }

    public void printUsers(List<User> users) {
        System.out.println(AccountMessages.USERS_HEADER);
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void printAlbums(List<Album> albums) {
        System.out.println(AlbumMessages.ALBUMS_HEADER);
        for (Album album : albums) {
            System.out.println(album);
        }
    }

    public void printUserAlbums(User user, List<Album> albums) {
        System.out.println(String.format(AlbumMessages.ALBUMS_OWNER_HEADER, user.getName()));
        for (Album album : albums) {
            System.out.println(album);
        }
    }

    public void printFriends(List<User> friends) {
        System.out.println(FriendMessages.FRIENDS_HEADER);
        if (friends.isEmpty()) {
            System.out.println(FriendMessages.NO_FRIENDS);
            return;
        }

        for (User friend : friends) {
            System.out.println(friend);
        }
    }

    public void printPhotos(List<Photo> photos) {
        for (Photo photo : photos) {
            System.out.println(photo);
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}