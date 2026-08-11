package pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo;

public final class PhotoLikeMessages {

    private PhotoLikeMessages() {
    }

    public static final String LOGGED_USER_NOT_FOUND =
            "Logged user not found.";

    public static final String PHOTO_OWNER_USERNAME =
            "Enter the username of the photo owner: ";

    public static final String FRIEND_DATA_NOT_FOUND =
            "Friend data not found.";

    public static final String PHOTO_OWNER_NOT_FOUND =
            "Photo owner not found.";

    public static final String NOT_FRIEND_PHOTO_OWNER =
            "You are not friends with the photo's owner.";

    public static final String NOT_FRIEND_PHOTO_OWNER_NO_LIKE =
            "You cannot like this photo because you are not friends with its owner.";

    public static final String ALBUM_NAME_LIKE =
            "Enter the name of the album containing the photo you want to like: ";

    public static final String ALBUM_DATA_NOT_FOUND =
            "Album data not found.";

    public static final String ALBUM_NOT_FOUND =
            "Album not found.";

    public static final String ADD_LIKE_PHOTO_NAME =
            "Like a photo. Enter the photo name: ";

    public static final String REMOVE_PHOTO_LIKE_NAME =
            "Unlike a photo. Enter the photo name: ";

    public static final String PHOTO_DATA_NOT_FOUND =
            "Photo data not found.";

    public static final String PHOTO_NOT_FOUND =
            "Photo not found.";

    public static final String PHOTO_NOT_IN_ALBUM =
            "Photo not found in the album.";

    public static final String PHOTO_LIKE_ADDED =
            "You liked the photo!";

    public static final String PHOTO_LIKE_REMOVED =
            "You no longer like this photo.";

    public static final String ALREADY_LIKE_PHOTO =
            "%s has already liked the photo.";

    public static final String ALREADY_LIKED =
            "You have already liked this photo.";

    public static final String NEVER_LIKED_PHOTO =
            "You have never liked this photo.";

    public static final String PHOTO_LIKES =
            ", number of likes: %d";

    public static final String PHOTO_LIKE_ERROR =
            "An unexpected error occurred while liking the photo.";
}