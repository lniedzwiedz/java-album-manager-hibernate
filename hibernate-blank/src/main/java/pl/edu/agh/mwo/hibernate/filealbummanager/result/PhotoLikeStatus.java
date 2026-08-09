package pl.edu.agh.mwo.hibernate.filealbummanager.result;

public enum PhotoLikeStatus {

    ALREADY_LIKED,
    PHOTO_NOT_IN_ALBUM,
    ALBUM_DOES_NOT_EXIST,
    NEVER_LIKED,
    NOT_FRIEND_PHOTO_OWNER,
    PHOTO_LIKE_ERROR;
}

