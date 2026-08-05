package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoLikeStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhotoRepository {

    private final Session session;
    private final AlbumRepository albumRepository;
    private final FriendService friendService;

    public PhotoRepository(Session session, AlbumRepository albumRepository, FriendService friendService) {
        this.session = session;
        this.albumRepository = albumRepository;
        this.friendService = friendService;
    }

    public Photo getPhotoFromDatabase(String photoName, int albumId) {
        Query<Photo> query = session.createQuery("FROM Photo p " + "WHERE p.name = :name " + "AND p.albumId = :albumId", Photo.class);
        query.setParameter("name", photoName);
        query.setParameter("albumId", albumId);
        return query.uniqueResult();
    }

    public List<Photo> getPhotosFromDatabase(int albumId) {
        Query<Photo> query = session.createQuery("FROM Photo p WHERE p.albumId = :albumId", Photo.class);
        query.setParameter("albumId", albumId);
        return query.list();
    }

    public boolean isPhotoBelongToUser(User user, String albumName, String photoName) {
        if (user == null)
            return false;

        Album album = albumRepository.getAlbumFromDatabase(albumName, user.getId());
        if (album == null)
            return false;


        return getPhotoFromDatabase(photoName, album.getId()) != null;
    }

    public PhotoAddResult getProcessingStatusWhileAddingPhoto(User user, String albumName, String photoName) {
        if (user == null)
            return PhotoAddResult.INVALID_USER_OR_ALBUM;

        Album album = albumRepository.getAlbumFromDatabase(albumName, user.getId());
        if (album == null)
            return PhotoAddResult.INVALID_USER_OR_ALBUM;

        Photo photo = getPhotoFromDatabase(photoName, album.getId());
        if (photo == null)
            return PhotoAddResult.CAN_BE_ADDED;

        return PhotoAddResult.ALREADY_EXISTS;
    }

    public void addPhoto(String photoName, String albumName, User user) {
        if (user == null)
            return;

        Album album = albumRepository.getAlbumFromDatabase(albumName, user.getId());

        if (album == null)
            return;

        Photo photo = new Photo();
        photo.setName(photoName);
        photo.setAlbumId(album.getId());
        photo.setDate(LocalDate.now().toString());

        Transaction transaction = session.beginTransaction();

        try {
            session.save(photo);
            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive())
                transaction.rollback();

            throw e;
        }
    }

    public void deletePhoto(String photoName, String albumName, User user) {
        if (user == null)
            return;

        Album album = albumRepository.getAlbumFromDatabase(albumName, user.getId());
        if (album == null)
            return;

        Photo photo = getPhotoFromDatabase(photoName, album.getId());
        if (photo == null)
            return;

        Transaction transaction = session.beginTransaction();

        try {
            deleteRelationBetweenPhotoAndUser(photo);
            album.removePhoto(photo);
            session.save(album);
            session.delete(photo);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Photo> getPhotosForUserAlbum(User user, String albumName) {
        if (user == null)
            return new ArrayList<>();

        Album album = albumRepository.getAlbumFromDatabase(albumName, user.getId());
        if (album == null)
            return new ArrayList<>();

        return getPhotosFromDatabase(album.getId());
    }

    public PhotoLikeStatus getProcessingStatusForPhotoLike(User user, String albumName, String photoName) {
        if (user == null)
            return PhotoLikeStatus.ALBUM_DOES_NOT_EXIST;

        Album album = albumRepository.getAlbumFromDatabase(albumName);
        if (album == null)
            return PhotoLikeStatus.ALBUM_DOES_NOT_EXIST;

        User owner = getUserById(album.getUserId());
        if (owner == null)
            return PhotoLikeStatus.ALBUM_DOES_NOT_EXIST;

        boolean allowed = user.equals(owner) || friendService.areWeFriends(user, owner.getName());

        if (!allowed)
            return PhotoLikeStatus.NOT_FRIEND_PHOTO_OWNER;

        Photo photo = getPhotoFromDatabase(photoName, album.getId());
        if (photo == null)
            return PhotoLikeStatus.PHOTO_NOT_IN_ALBUM;

        if (photo.getUsers().contains(user))
            return PhotoLikeStatus.ALREADY_LIKED;

        return PhotoLikeStatus.NEVER_LIKED;
    }

    private User getUserById(int userId) {
        Query<User> query = session.createQuery("FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", userId);
        return query.uniqueResult();
    }

    public void addPhotoLike(User user, String albumName, String photoName) {
        if (user == null)
            return;

        Album album = albumRepository.getAlbumFromDatabase(albumName);
        if (album == null)
            return;

        Photo photo = getPhotoFromDatabase(photoName, album.getId());
        if (photo == null)
            return;

        if (photo.getUsers().contains(user))
            return;

        Transaction transaction = session.beginTransaction();

        try {
            photo.addUser(user);
            session.save(photo);
            session.save(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();

            throw e;
        }
    }

    public void deletePhotoLike(User user, String albumName, String photoName) {
        if (user == null)
            return;

        Album album = albumRepository.getAlbumFromDatabase(albumName);
        if (album == null)
            return;

        Photo photo = getPhotoFromDatabase(photoName, album.getId());
        if (photo == null)
            return;

        if (!photo.getUsers().contains(user))
            return;

        Transaction transaction = session.beginTransaction();

        try {
            photo.removeUser(user);
            user.removePhoto(photo);
            session.save(photo);
            session.save(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();

            throw e;
        }
    }

    public int countedPhotoLikes(Photo photo) {
        if (photo == null)
            return 0;

        return photo.getUsers().size();
    }

    private void deleteRelationBetweenPhotoAndUser(Photo photo) {
        if (photo == null)
            return;

        List<User> users = new ArrayList<>(photo.getUsers());

        for (User user : users) {
            photo.removeUser(user);
            user.removePhoto(photo);
            session.save(user);
        }

        session.save(photo);
    }
}