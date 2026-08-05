package pl.edu.agh.mwo.hibernate.filealbummanager.ui.application;

public final class ApplicationMessages {

    private ApplicationMessages() {
    }

    public static final String ALBUM_MANAGER_TITLE =
            "***** ALBUM MANAGER *****";

    public static final String INVALID_INPUT_E3 =
            "[E3] input is not correct";

    public static final String MENU_HEADER =
            "WHAT DO YOU WANT TO DO? select option: ";

//    public static final String MENU_OPTIONS =
//            "1-addAlbum, 2-deleteAlbum,\n"
//                    + "3-showMyAlbums, 5-showUserAlbums, 7-showPhotoInMyAlbum,\n"
//                    + "9-addPhoto, 10-deletePhoto,\n"
//                    + "11-likePhoto, 12-noLikePhoto,\n"
//                    + "20-addFriend, 21-deleteFriend, 23-showMyFriends,\n"
//                    + "999-logout, 666-removeYourselfFromDatabase";

    public static final String MENU_OPTIONS =
            MenuOption.ADD_ALBUM.getValue() + "-addAlbum, "
                    + MenuOption.DELETE_ALBUM.getValue() + "-deleteAlbum,\n"
                    + MenuOption.SHOW_MY_ALBUMS.getValue() + "-showMyAlbums, "
                    + MenuOption.SHOW_USER_ALBUMS.getValue() + "-showUserAlbums, "
                    + MenuOption.SHOW_PHOTOS.getValue() + "-showPhotoInMyAlbum,\n"
                    + MenuOption.ADD_PHOTO.getValue() + "-addPhoto, "
                    + MenuOption.DELETE_PHOTO.getValue() + "-deletePhoto,\n"
                    + MenuOption.LIKE_PHOTO.getValue() + "-likePhoto, "
                    + MenuOption.UNLIKE_PHOTO.getValue() + "-noLikePhoto,\n"
                    + MenuOption.ADD_FRIEND.getValue() + "-addFriend, "
                    + MenuOption.DELETE_FRIEND.getValue() + "-deleteFriend, "
                    + MenuOption.SHOW_FRIENDS.getValue() + "-showMyFriends,\n"
                    + MenuOption.DELETE_ACCOUNT.getValue() + "-removeYourselfFromDatabase, "
                    + MenuOption.LOGOUT.getValue() + "-logout";
}