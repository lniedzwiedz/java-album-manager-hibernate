package pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo;

public final class PhotoLikeMessages {

    private PhotoLikeMessages() {
    }

    public static final String ADD_LIKE_PHOTO_NAME =
            "Like a photo. Enter the photo name: ";

    public static final String ALBUM_NAME_LIKE =
            "Enter the name of the album containing the photo you want to like: ";

    public static final String PHOTO_LIKE_ADDED =
            "You liked the photo!";

    public static final String ALREADY_LIKE_PHOTO =
            "%s has already liked the photo.";

    public static final String NOT_FRIEND_PHOTO_OWNER =
            "You are not friends with the photo's owner.";

    public static final String REMOVE_PHOTO_LIKE_NAME =
            "Unlike a photo. Enter the photo name: ";

    public static final String NEVER_LIKED_PHOTO =
            "You have never liked this photo.";

    public static final String PHOTO_LIKE_REMOVED =
            "You no longer like this photo.";

    public static final String NOT_FRIEND_PHOTO_OWNER_NO_LIKE =
            "You cannot like this photo because you are not friends with its owner.";

    public static final String PHOTO_LIKES =
            ", number of likes: %d";

    public static final String PHOTO_LIKE_ERROR =
            "An unexpected error occurred while liking the photo.";

    public static final String PHOTO_OWNER_USERNAME =
            "Enter the username of the photo owner: ";
}