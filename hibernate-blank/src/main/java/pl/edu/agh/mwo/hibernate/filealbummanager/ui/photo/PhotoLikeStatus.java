package pl.edu.agh.mwo.hibernate.filealbummanager.ui.photo;

public enum PhotoLikeStatus {

    ALREADY_LIKED(1),
    PHOTO_NOT_IN_ALBUM(2),
    ALBUM_DOES_NOT_EXIST(3),
    NEVER_LIKED(4),
    NOT_FRIEND_PHOTO_OWNER(5);

    private final int value;

    PhotoLikeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PhotoLikeStatus fromInt(int value) {
        for (PhotoLikeStatus status : values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }
}

