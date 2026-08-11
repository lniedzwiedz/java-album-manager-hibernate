package pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend;

public final class FriendMessages {

    private FriendMessages() {
    }

    public static final String USER_NOT_FOUND =
            "User not found.";

    public static final String LOGGED_USER_NOT_FOUND =
            "Logged user not found.";

    public static final String FRIEND_DATA_NOT_FOUND =
            "Friend data not found.";

    public static final String FRIEND_NOT_FOUND =
            "Friend %s not found.";

    public static final String ADD_FRIEND_USERNAME =
            "Enter username to add as a friend: ";

    public static final String ALREADY_FRIEND =
            "You are already friends with %s.";

    public static final String NOW_FRIEND =
            "You are now friends with %s.";

    public static final String FRIEND_ADD_ERROR =
            "An unexpected error occurred while adding the friend.";

    public static final String DELETE_FRIEND_USERNAME =
            "Enter the username of the friend you want to remove: ";

    public static final String NOT_FRIEND =
            "You are not friends with %s.";

    public static final String FRIEND_DELETED =
            "Friend has been removed.";

    public static final String FRIEND_DELETE_ERROR =
            "An unexpected error occurred while deleting the friend.";

    public static final String FRIENDS_HEADER =
            "### Friends";

    public static final String NO_FRIENDS =
            "You don't have any friends yet.";
}