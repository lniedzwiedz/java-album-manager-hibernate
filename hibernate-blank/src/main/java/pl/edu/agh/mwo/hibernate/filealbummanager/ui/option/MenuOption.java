package pl.edu.agh.mwo.hibernate.filealbummanager.ui.option;

public enum MenuOption {

    ADD_ALBUM(1),
    DELETE_ALBUM(2),
    SHOW_MY_ALBUMS(3),
    SHOW_USER_ALBUMS(5),
    SHOW_PHOTOS(7),
    ADD_PHOTO(9),
    DELETE_PHOTO(10),
    LIKE_PHOTO(11),
    UNLIKE_PHOTO(12),
    ADD_FRIEND(20),
    DELETE_FRIEND(21),
    SHOW_FRIENDS(23),
    DELETE_ACCOUNT(666),
    LOGOUT(999);

    private final int value;

    MenuOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MenuOption fromInt(int value) {
        for (MenuOption option : values()) {
            if (option.value == value)
                return option;
        }
        return null;
    }
}