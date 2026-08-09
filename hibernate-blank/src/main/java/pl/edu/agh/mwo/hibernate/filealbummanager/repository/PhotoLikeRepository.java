package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;

public class PhotoLikeRepository {

    private final SessionFactory sessionFactory;

    public PhotoLikeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean addPhotoLike(Photo photo, User user) {
        if (photo == null || user == null) return false;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            try {
                Photo managedPhoto = session.get(Photo.class, photo.getId());
                User managedUser = session.get(User.class, user.getId());

                if (managedPhoto == null || managedUser == null) {
                    transaction.rollback();
                    return false;
                }

                if (managedPhoto.getUsers().contains(managedUser)) {
                    transaction.rollback();
                    return false;
                }
                managedPhoto.addUser(managedUser);
                transaction.commit();
                return true;

            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    public boolean deletePhotoLike(User user, Photo photo) {
        if (photo == null || user == null)
            return false;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            try {
                Photo managedPhoto = session.get(Photo.class, photo.getId());
                User managedUser = session.get(User.class, user.getId());

                if (managedPhoto == null || managedUser == null) {
                    transaction.rollback();
                    return false;
                }

                if (!managedPhoto.getUsers().contains(managedUser)) {
                    transaction.rollback();
                    return false;
                }
                managedPhoto.removeUser(managedUser);
                managedUser.removePhoto(managedPhoto);
                transaction.commit();
                return true;

            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    public int countPhotoLikes(Photo photo) {
        if (photo == null)
            return 0;
        return photo.getUsers().size();
    }
}