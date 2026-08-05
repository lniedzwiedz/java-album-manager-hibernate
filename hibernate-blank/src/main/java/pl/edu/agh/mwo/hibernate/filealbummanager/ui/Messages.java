package pl.edu.agh.mwo.hibernate.filealbummanager.ui;

public final class Messages {

    private Messages() {

    }

    // LOGIN / USER
    public static final String LOGIN_USERNAME = "log in. user name: ";
    public static final String CREATE_ACCOUNT_USERNAME = "Create account. user name: ";
    public static final String SELECT_LOGIN_OR_CREATE = "select option: 1 - log in, 2 - create account";
    public static final String SELECT_CREATE_RETRY = "select option: 1 - create account, 2 - try again";
    public static final String WELCOME = "Welcome %s";
    public static final String WELCOME_ACCOUNT_CREATED = "Welcome %s. User account created.";
    public static final String WELCOME_ACCOUNT_CREATED_EXCLAMATION = "Welcome %s! User account created.";
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String USER_NOT_FOUND_RETRY = "user not found. Do you want to try login again or create account?";
    public static final String USER_DOES_NOT_EXIST = "User %s does not exist.";
    public static final String ACCOUNT_EXISTS_AUTO_LOGIN = "Account exists. You have been automatically logged in.";
    public static final String USERS_HEADER = "### Users";

    // ALBUM
    public static final String ADD_ALBUM_NAME = "ADD album name: ";
    public static final String ALBUM_ADDED = "Album added successfully.";
    public static final String ALBUM_EXISTS = "Album already exists.";
    public static final String REMOVE_ALBUM_NAME = "REMOVE album name: ";
    public static final String ALBUM_REMOVED = "Album removed successfully. All album data were deleted.";
    public static final String ALBUM_DELETE_FORBIDDEN = "Album does not exist or does not belong to the user %s. Album cannot be deleted.";
    public static final String ENTER_USERNAME_ALBUMS = "Enter the username of the user whose albums you want to see:";
    public static final String ALBUM_NOT_FOUND = "Album not found.";
    public static final String ALBUM_NAME = "album name:";
    public static final String ALBUM_NOT_EXIST = "Album %s does not exist";
    public static final String ALBUM_NOT_EXIST_BRACKET = "[Album %s does not exist]";
    public static final String ALBUM_OR_PHOTO_NOT_EXIST = "Album does not exist or photo does not exist";
    public static final String ALBUMS_HEADER = "### Albums";
    public static final String ALBUMS_OWNER_HEADER = "### Albums, owner: %s";

    // PHOTO
    public static final String ENTER_ALBUM_PHOTO = "Enter the name of album the photo belongs to:";
    public static final String ENTER_ALBUM_ADD_PHOTO = "Enter the name of album where you want to add photo:";
    public static final String ADD_PHOTO_NAME = "ADD enter photo name: ";
    public static final String PHOTO_ADDED = "Photo added successfully.";
    public static final String PHOTO_EXISTS = "Photo already exists in album.";
    public static final String PHOTO_ADD_FORBIDDEN = "Album does not exist or does not belong to user %s. Photo cannot be added.";
    public static final String REMOVE_PHOTO_NAME = "REMOVE photo name:";
    public static final String PICTURE_DELETED = "Picture deleted successfully.";
    public static final String PICTURE_DELETE_FORBIDDEN = "Picture/ album does not exist or does not belong to user %s. Picture cannot be deleted.";
    public static final String PHOTO_LIKES = " , likes: %d";

    // PHOTO LIKE
    public static final String ADD_LIKE_PHOTO_NAME = "ADD like photo name: ";
    public static final String ALBUM_NAME_LIKE = "album name for photo to like: ";
    public static final String PHOTO_LIKE_ADDED = "You like photo - added successfully.";
    public static final String ALREADY_LIKE_PHOTO = "%s already like photo.";
    public static final String PHOTO_NOT_IN_ALBUM = "Photo does not exist in album.";
    public static final String ALBUM_DOES_NOT_EXIST = "Album does not exist.";
    public static final String NOT_FRIEND_PHOTO_OWNER = "You are not a friend with a person who take a photo.";
    public static final String REMOVE_PHOTO_LIKE_NAME = "REMOVE photoLIKE, photo name:";
    public static final String NEVER_LIKED_PHOTO = "You never like photo.";
    public static final String PHOTO_LIKE_REMOVED = "You don't like photo - remove successfully.";
    public static final String NOT_FRIEND_PHOTO_OWNER_NO_LIKE = "You are not a friend with a person who take a photo. You don't like photo.";

    // FRIEND
    public static final String ADD_FRIEND_USERNAME = "ADD FRIEND user name: ";
    public static final String ALREADY_FRIEND = "You are already friend of %s.";
    public static final String NOW_FRIEND = "You are now friend of %s.";
    public static final String DELETE_FRIEND_USERNAME = "DELETE FRIEND, user name: ";
    public static final String NOT_FRIEND = "You are not friend of %s.";
    public static final String FRIEND_NOT_EXIST_DATABASE = "[Friend %s does not exist in database]";
    public static final String FRIEND_NOT_EXIST = "[Friend %s does not exist]";
    public static final String FRIENDS_HEADER = "### Friends";
    public static final String NO_FRIENDS = "No friends.";

    // ACCOUNT
    public static final String CONFIRM_DELETE_ACCOUNT = "Are you sure you want to remove yourself from database? 1-yes, 2-no";
    public static final String GOODBYE = "Goodbye %s!";
    public static final String WISE_CHOICE = "hmmm - wise choose (?)";
    public static final String CONFIRM_LOGOUT = "Are you sure you want to logout? 1-yes, 2-no";

    // APPLICATION
    public static final String ALBUM_MANAGER_TITLE = "***** ALBUM MANAGERvO1 *****";
    public static final String INVALID_INPUT_E3 = "[E3] input is not correct";
    public static final String MENU_HEADER = "WHAT DO YOU WANT TO DO? select option: ";
    public static final String MENU_OPTIONS = "1-addAlbum, 2-deleteAlbum,\n"
            + "3-showMyAlbums, 5-showUserAlbums, 7-showPhotoInMyAlbum,\n"
            + "9-addPhoto, 10-deletePhoto,\n"
            + "11-likePhoto, 12-noLikePhoto,\n"
            + "20-addFriend, 21-deleteFriend, 23-showMyFriends,\n"
            + "999-logout, 666-removeYourselfFromDatabase";
}