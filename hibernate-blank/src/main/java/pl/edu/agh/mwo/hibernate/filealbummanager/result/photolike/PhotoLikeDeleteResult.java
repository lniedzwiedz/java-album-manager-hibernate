package pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike;

public enum PhotoLikeDeleteResult {

    LOGGED_USER_NOT_FOUND,
    FRIEND_DATA_NOT_FOUND,
    PHOTO_OWNER_NOT_FOUND,
    NOT_FRIEND_PHOTO_OWNER,
    ALBUM_DATA_NOT_FOUND,
    ALBUM_NOT_FOUND,
    PHOTO_DATA_NOT_FOUND,
    PHOTO_NOT_FOUND,
    PHOTO_NOT_IN_ALBUM,
    NOT_LIKED,
    PHOTO_LIKE_DELETED,
    PHOTO_LIKE_ERROR,
}
