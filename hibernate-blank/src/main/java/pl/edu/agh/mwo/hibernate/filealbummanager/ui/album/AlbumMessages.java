package pl.edu.agh.mwo.hibernate.filealbummanager.ui.album;

public final class AlbumMessages {

    private AlbumMessages() {
    }

    public static final String ADD_ALBUM_NAME =
            "ADD album name: ";

    public static final String ALBUM_ADDED =
            "Album added successfully.";

    public static final String ALBUM_EXISTS =
            "Album already exists.";

    public static final String REMOVE_ALBUM_NAME =
            "REMOVE album name: ";

    public static final String ALBUM_REMOVED =
            "Album removed successfully. All album data were deleted.";

    public static final String ALBUM_DELETE_FORBIDDEN =
            "Album does not exist or does not belong to the user %s. Album cannot be deleted.";

    public static final String ENTER_USERNAME_ALBUMS =
            "Enter the username of the user whose albums you want to see:";

    public static final String ALBUM_NOT_FOUND =
            "Album not found.";

    public static final String ALBUM_NAME =
            "album name:";

    public static final String ALBUM_NOT_EXIST =
            "Album %s does not exist";

    public static final String ALBUMS_HEADER =
            "### Albums";

    public static final String ALBUMS_OWNER_HEADER =
            "### Albums, owner: %s";

    public static final String ALBUM_OR_PHOTO_NOT_EXIST =
            "Album or photo does not exist.";
}