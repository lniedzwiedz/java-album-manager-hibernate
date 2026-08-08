package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoLikeStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhotoRepository {

    private final SessionFactory sessionFactory;

    public PhotoRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Photo getPhotoFromDatabase(String photoName, int albumId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Photo> query = session.createQuery("FROM Photo p " + "WHERE p.name = :name " + "AND p.albumId = :albumId", Photo.class);
            query.setParameter("name", photoName);
            query.setParameter("albumId", albumId);
            return query.uniqueResult();
        }
    }

    public List<Photo> getPhotosFromDatabase(int albumId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Photo> query = session.createQuery("FROM Photo p WHERE p.albumId = :albumId", Photo.class);
            query.setParameter("albumId", albumId);
            return query.list();
        }
    }

    public boolean isPhotoBelongToUser(User user, String albumName, String photoName) {
        if (user == null) return false;

        try (Session session = sessionFactory.openSession()) {
            Query<Album> albumQuery = session.createQuery("FROM Album a " + "WHERE a.name = :name " + "AND a.userId = :userId", Album.class);

            albumQuery.setParameter("name", albumName);
            albumQuery.setParameter("userId", user.getId());

            Album album = albumQuery.uniqueResult();
            if (album == null) return false;

            Query<Photo> photoQuery = session.createQuery("FROM Photo p " + "WHERE p.name = :name " + "AND p.albumId = :albumId", Photo.class);

            photoQuery.setParameter("name", photoName);
            photoQuery.setParameter("albumId", album.getId());

            return photoQuery.uniqueResult() != null;
        }
    }

//    public PhotoAddResult checkPhotoCanBeAdded(User user, String albumName, String photoName) {
//        if (user == null) return PhotoAddResult.INVALID_USER_OR_ALBUM;
//
//        try (Session session = sessionFactory.openSession()) {
//            Query<Album> albumQuery = session.createQuery("FROM Album a " + "WHERE a.name = :name " + "AND a.userId = :userId", Album.class);
//
//            albumQuery.setParameter("name", albumName);
//            albumQuery.setParameter("userId", user.getId());
//
//            Album album = albumQuery.uniqueResult();
//            if (album == null) return PhotoAddResult.INVALID_USER_OR_ALBUM;
//
//            Query<Photo> photoQuery = session.createQuery("FROM Photo p " + "WHERE p.name = :name " + "AND p.albumId = :albumId", Photo.class);
//
//            photoQuery.setParameter("name", photoName);
//            photoQuery.setParameter("albumId", album.getId());
//
//            Photo photo = photoQuery.uniqueResult();
//            if (photo == null) return PhotoAddResult.CAN_BE_ADDED;
//
//            return PhotoAddResult.ALREADY_EXISTS;
//        }
//    }

    public void addPhoto(String photoName, String albumName, User user) {
//        if (user == null)
//            return;
//
//        try (Session session = sessionFactory.openSession()) {
//            Query<Album> query = session.createQuery("FROM Album a " + "WHERE a.name = :name " + "AND a.userId = :userId", Album.class);
//
//            query.setParameter("name", albumName);
//            query.setParameter("userId", user.getId());
//
//            Album album = query.uniqueResult();
//            if (album == null)
//                return;
//
//            Photo photo = new Photo();
//            photo.setName(photoName);
//            photo.setAlbumId(album.getId());
//            photo.setDate(LocalDate.now().toString());
//
//            Transaction transaction = session.beginTransaction();
//            try {
//                session.save(photo);
//                transaction.commit();
//
//            } catch (Exception e) {
//                if (transaction.isActive())
//                    transaction.rollback();
//                throw e;
//            }
//        }

        if (user == null) return;

        try (Session session = sessionFactory.openSession()) {
            Query<Album> query = session.createQuery("FROM Album a " + "WHERE a.name = :name " + "AND a.userId = :userId", Album.class);

            query.setParameter("name", albumName);
            query.setParameter("userId", user.getId());

            Album album = query.uniqueResult();
            if (album == null) return;

            Photo photo = new Photo();
            photo.setName(photoName);
            photo.setAlbumId(album.getId());
            photo.setDate(LocalDate.now().toString());

            Transaction transaction = session.beginTransaction();
            try {
                session.save(photo);
                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    public void deletePhoto(String photoName, String albumName, User user) {
        if (user == null) return;

        try (Session session = sessionFactory.openSession()) {
            Query<Album> albumQuery = session.createQuery("FROM Album a " + "WHERE a.name = :name " + "AND a.userId = :userId", Album.class);

            albumQuery.setParameter("name", albumName);
            albumQuery.setParameter("userId", user.getId());

            Album album = albumQuery.uniqueResult();
            if (album == null) return;

            Query<Photo> photoQuery = session.createQuery("FROM Photo p " + "WHERE p.name = :name " + "AND p.albumId = :albumId", Photo.class);

            photoQuery.setParameter("name", photoName);
            photoQuery.setParameter("albumId", album.getId());

            Photo photo = photoQuery.uniqueResult();
            if (photo == null) return;

            Transaction transaction = session.beginTransaction();
            try {
                deleteRelationBetweenPhotoAndUser(session, photo);

                album.removePhoto(photo);
                session.save(album);
                session.delete(photo);

                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    public List<Photo> getPhotosForUserAlbum(User user, String albumName) {
        if (user == null) return new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Query<Album> albumQuery = session.createQuery("FROM Album a " + "WHERE a.name = :name " + "AND a.userId = :userId", Album.class);

            albumQuery.setParameter("name", albumName);
            albumQuery.setParameter("userId", user.getId());

            Album album = albumQuery.uniqueResult();
            if (album == null) return new ArrayList<>();

            Query<Photo> photoQuery = session.createQuery("FROM Photo p WHERE p.albumId = :albumId", Photo.class);
            photoQuery.setParameter("albumId", album.getId());

            return photoQuery.list();
        }
    }

    public PhotoLikeStatus checkPhotoLikeStatus(User user, String albumName, String photoName) {
        if (user == null) return PhotoLikeStatus.ALBUM_DOES_NOT_EXIST;

        try (Session session = sessionFactory.openSession()) {
            Query<Album> albumQuery = session.createQuery("FROM Album a WHERE a.name = :name", Album.class);
            albumQuery.setParameter("name", albumName);

            Album album = albumQuery.uniqueResult();
            if (album == null) return PhotoLikeStatus.ALBUM_DOES_NOT_EXIST;

            User owner = session.get(User.class, album.getUserId());
            User managedUser = session.get(User.class, user.getId());
            if (owner == null || managedUser == null) return PhotoLikeStatus.ALBUM_DOES_NOT_EXIST;

            boolean areFriends = managedUser.equals(owner) || managedUser.getUsers().contains(owner) || owner.getUsers().contains(managedUser);

            if (!areFriends) return PhotoLikeStatus.NOT_FRIEND_PHOTO_OWNER;

            Query<Photo> photoQuery = session.createQuery("FROM Photo p " + "WHERE p.name = :name " + "AND p.albumId = :albumId", Photo.class);

            photoQuery.setParameter("name", photoName);
            photoQuery.setParameter("albumId", album.getId());

            Photo photo = photoQuery.uniqueResult();
            if (photo == null) return PhotoLikeStatus.PHOTO_NOT_IN_ALBUM;

            if (photo.getUsers().contains(managedUser)) return PhotoLikeStatus.ALREADY_LIKED;

            return PhotoLikeStatus.NEVER_LIKED;
        }
    }

    public void addPhotoLike(User user, String albumName, String photoName) {
        if (user == null) return;

        try (Session session = sessionFactory.openSession()) {
            Query<Album> albumQuery = session.createQuery("FROM Album a WHERE a.name = :name", Album.class);
            albumQuery.setParameter("name", albumName);

            Album album = albumQuery.uniqueResult();
            if (album == null) return;

            Query<Photo> photoQuery = session.createQuery("FROM Photo p " + "WHERE p.name = :name " + "AND p.albumId = :albumId", Photo.class);

            photoQuery.setParameter("name", photoName);
            photoQuery.setParameter("albumId", album.getId());

            Photo photo = photoQuery.uniqueResult();
            if (photo == null) return;

            User managedUser = session.get(User.class, user.getId());
            if (managedUser == null) return;

            Transaction transaction = session.beginTransaction();
            try {
                if (photo.getUsers().contains(managedUser)) {
                    transaction.rollback();
                    return;
                }
                photo.addUser(managedUser);

                session.save(photo);
                session.save(managedUser);

                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    public void deletePhotoLike(User user, String albumName, String photoName) {
        if (user == null) return;

        try (Session session = sessionFactory.openSession()) {

            Query<Album> albumQuery = session.createQuery("FROM Album a WHERE a.name = :name", Album.class);
            albumQuery.setParameter("name", albumName);

            Album album = albumQuery.uniqueResult();
            if (album == null) return;

            Query<Photo> photoQuery = session.createQuery("FROM Photo p " + "WHERE p.name = :name " + "AND p.albumId = :albumId", Photo.class);

            photoQuery.setParameter("name", photoName);
            photoQuery.setParameter("albumId", album.getId());

            Photo photo = photoQuery.uniqueResult();
            if (photo == null) return;

            User managedUser = session.get(User.class, user.getId());
            if (managedUser == null) return;

            Transaction transaction = session.beginTransaction();
            try {
                if (!photo.getUsers().contains(managedUser)) {
                    transaction.rollback();
                    return;
                }
                photo.removeUser(managedUser);
                managedUser.removePhoto(photo);

                session.save(photo);
                session.save(managedUser);

                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    public int countedPhotoLikes(Photo photo) {
        if (photo == null) return 0;
        return photo.getUsers().size();
    }

    private void deleteRelationBetweenPhotoAndUser(Session session, Photo photo) {
        if (photo == null) return;

        List<User> users = new ArrayList<>(photo.getUsers());
        for (User user : users) {
            photo.removeUser(user);
            user.removePhoto(photo);
            session.save(user);
        }
        session.save(photo);
    }
}