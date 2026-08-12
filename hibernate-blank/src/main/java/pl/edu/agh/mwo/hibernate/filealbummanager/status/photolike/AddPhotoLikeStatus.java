package pl.edu.agh.mwo.hibernate.filealbummanager.status.photolike;

public enum AddPhotoLikeStatus {

    LOGGED_USER_NOT_FOUND,
    USER_NOT_FOUND,
    FRIEND_DATA_NOT_FOUND,
    NOT_FRIENDS,
    ALBUM_DATA_NOT_FOUND,
    ALBUM_NOT_FOUND,
    ALBUM_NOT_OWNED_BY_USER,
    PHOTO_DATA_NOT_FOUND,
    PHOTO_NOT_FOUND,
    PHOTO_NOT_IN_ALBUM,
    PHOTO_ALREADY_LIKED,
    PHOTO_LIKE_ADDED,
}
