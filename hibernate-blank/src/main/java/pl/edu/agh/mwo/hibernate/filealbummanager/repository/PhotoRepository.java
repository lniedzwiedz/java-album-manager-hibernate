package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhotoRepository {

    private final Session session;
    private final AlbumRepository albumRepository;
    private final FriendManagerService friendManager;

    public PhotoRepository(Session session, AlbumRepository albumRepository, FriendManagerService friendManager) {
        this.session = session;
        this.albumRepository = albumRepository;
        this.friendManager = friendManager;
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

    public boolean isPictureBelongToUser(User user, String albumName, String photoName) {
        if (user == null)
            return false;
        Album album = albumRepository.getAlbumFromDatabase(albumName, user.getId());
        if (album == null)
            return false;
        return getPhotoFromDatabase(photoName, album.getId()) != null;
    }

    public int getProcessingStatusWhileAddingPhoto(User user, String albumName, String photoName) {
        if (user == null)
            return 3;
        Album album = albumRepository.getAlbumFromDatabase(albumName, user.getId());
        if (album == null)
            return 3;
        Photo photo = getPhotoFromDatabase(photoName, album.getId());
        if (photo == null)
            return 1;
        return 2;
    }

    public void addPhoto(String photoName, String albumName, User user) {
        if (user == null)
            return;
        Album album = albumRepository.getAlbumFromDatabase(albumName, user.getId());
        if (album == null) {
            System.out.println(String.format(Messages.ALBUM_NOT_EXIST_BRACKET, albumName));
            return;
        }
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
        if (album == null) {
            System.out.println(Messages.ALBUM_OR_PHOTO_NOT_EXIST);
            return;
        }
        Photo photo = getPhotoFromDatabase(photoName, album.getId());
        if (photo == null) {
            System.out.println(Messages.ALBUM_OR_PHOTO_NOT_EXIST);
            return;
        }
        Transaction transaction = session.beginTransaction();
        try {
            deleteRelationBetweenPhotoAndUser(photo);
            album.removePhoto(photo);
            session.save(album);
            session.delete(photo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();
            throw e;
        }
    }

    public void printPhoto(User user, String albumName) {
        if (user == null)
            return;
        Album album = albumRepository.getAlbumFromDatabase(albumName, user.getId());
        if (album == null) {
            System.out.println(Messages.ALBUM_NOT_FOUND);
            return;
        }
        List<Photo> photos = getPhotosFromDatabase(album.getId());
        for (Photo photo : photos) {
            System.out.println(photo);
            System.out.println(String.format(Messages.PHOTO_LIKES, countedPhotoLikes(photo)));
        }
    }

    public int getProcessingStatusForPhotoLike(User user, String albumName, String photoName) {
        if (user == null)
            return 3;
        Album album = albumRepository.getAlbumFromDatabase(albumName);
        if (album == null)
            return 3;
        User owner = getUserById(album.getUserId());
        if (owner == null)
            return 3;
        boolean allowed = user.equals(owner) || friendManager.areWeFriends(user, owner.getName());
        if (!allowed)
            return 5;
        Photo photo = getPhotoFromDatabase(photoName, album.getId());
        if (photo == null)
            return 2;
        if (photo.getUsers().contains(user))
            return 1;
        return 4;
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
        List<User> allUsers = session.createQuery("FROM User", User.class).list();
        for (User user : allUsers) {
            if (user.getPhotos().contains(photo)) {
                user.removePhoto(photo);
                photo.removeUser(user);
                session.save(user);
            }
        }
        session.save(photo);
    }
}