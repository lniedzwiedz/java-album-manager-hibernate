package pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo;

public final class PhotoMessages {

    private PhotoMessages() {
    }

    public static final String ENTER_ALBUM_PHOTO =
            "Enter the name of the album the photo belongs to: ";

    public static final String ENTER_ALBUM_ADD_PHOTO =
            "Enter album name to add the photo: ";

    public static final String ADD_PHOTO_NAME =
            "Add photo. Enter the photo name: ";

    public static final String PHOTO_ADDED =
            "Photo added successfully.";

    public static final String PHOTO_EXISTS =
            "A photo with this name already exists in the album.";

    public static final String PHOTO_ADD_FORBIDDEN =
            "The photo cannot be added because the album does not exist or does not belong to user.";

    public static final String REMOVE_PHOTO_NAME =
            "Remove photo. Enter the photo name: ";

    public static final String PHOTO_DELETED =
            "Photo deleted successfully.";

    public static final String PHOTO_DELETE_FORBIDDEN =
            "Album or photo does not exist or does not belong to user %s. Photo cannot be deleted.";

    public static final String PHOTO_NOT_IN_ALBUM =
            "Photo not found in the specified album.";

    public static final String PHOTO_ADD_ERROR =
            "An unexpected error occurred while adding the photo.";

    public static final String PHOTO_NOT_FOUND =
            "Photo not found in the specified album.";

    public static final String ALBUM_NOT_FOUND =
            "Album not found.";
}