package pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album;

public final class AlbumMessages {

    private AlbumMessages() {
    }

    public static final String ENTER_USERNAME_ALBUMS =
            "Enter the username of the user whose albums you want to view: ";

    public static final String LOGGED_USER_NOT_FOUND =
            "Logged user not found.";

    public static final String ALBUM_DATA_NOT_FOUND =
            "Album data not found.";

    public static final String ALBUM_NOT_FOUND =
            "Album not found.";

    public static final String ALBUM_NOT_OWNED_BY_USER =
            "This album does not belong to this user.";

    public static final String ALBUM_OR_PHOTO_NOT_EXIST =
            "Album or photo not found.";

    public static final String ADD_ALBUM_NAME =
            "Enter the album name: ";

    public static final String ALBUM_ADDED =
            "Album has been added successfully.";

    public static final String ALBUM_ALREADY_EXISTS =
            "Album already exists.";

    public static final String ALBUM_ADD_ERROR =
            "An unexpected error occurred while adding the album.";

    public static final String DELETE_ALBUM_NAME =
            "Enter the name of the album you want to remove: ";

    public static final String ALBUM_DELETED =
            "Album removed successfully. All album data have been deleted.";

    public static final String ALBUM_DELETE_ERROR =
            "An unexpected error occurred while deleting the album.";

    public static final String ALBUMS_HEADER =
            "### Albums";

    public static final String ALBUMS_OWNER_HEADER =
            "### Albums, owner: %s";
}