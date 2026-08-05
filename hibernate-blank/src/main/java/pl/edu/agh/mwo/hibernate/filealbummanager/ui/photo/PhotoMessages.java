package pl.edu.agh.mwo.hibernate.filealbummanager.ui.photo;

public final class PhotoMessages {

    private PhotoMessages() {
    }

    public static final String ENTER_ALBUM_PHOTO =
            "Enter the name of album the photo belongs to:";

    public static final String ENTER_ALBUM_ADD_PHOTO =
            "Enter the name of album where you want to add photo:";

    public static final String ADD_PHOTO_NAME =
            "ADD enter photo name: ";

    public static final String PHOTO_ADDED =
            "Photo added successfully.";

    public static final String PHOTO_EXISTS =
            "Photo already exists in album.";

    public static final String PHOTO_ADD_FORBIDDEN =
            "Album does not exist or does not belong to user %s. Photo cannot be added.";

    public static final String REMOVE_PHOTO_NAME =
            "REMOVE photo name:";

    public static final String PHOTO_DELETED =
            "Photo deleted successfully.";

    public static final String PHOTO_DELETE_FORBIDDEN =
            "Photo/ album does not exist or does not belong to user %s. Photo cannot be deleted.";

    public static final String ADD_LIKE_PHOTO_NAME =
            "ADD like photo name: ";

    public static final String ALBUM_NAME_LIKE =
            "album name for photo to like: ";

    public static final String PHOTO_LIKE_ADDED =
            "You like photo - added successfully.";

    public static final String ALREADY_LIKE_PHOTO =
            "%s already like photo.";

    public static final String PHOTO_NOT_IN_ALBUM =
            "Photo does not exist in album.";

    public static final String ALBUM_DOES_NOT_EXIST =
            "Album does not exist.";

    public static final String NOT_FRIEND_PHOTO_OWNER =
            "You are not a friend with a person who take a photo.";

    public static final String REMOVE_PHOTO_LIKE_NAME =
            "REMOVE photoLIKE, photo name:";

    public static final String NEVER_LIKED_PHOTO =
            "You never like photo.";

    public static final String PHOTO_LIKE_REMOVED =
            "You don't like photo - remove successfully.";

    public static final String NOT_FRIEND_PHOTO_OWNER_NO_LIKE =
            "You are not a friend with a person who take a photo.";

    public static final String PHOTO_LIKES =
            " , likes: %d";
}