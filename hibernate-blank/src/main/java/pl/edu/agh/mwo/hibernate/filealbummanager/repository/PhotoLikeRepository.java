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

    public void addPhotoLike(User user, Photo photo) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                Photo managedPhoto = session.get(Photo.class, photo.getId());
                User managedUser = session.get(User.class, user.getId());

                if (managedPhoto != null && managedUser != null)
                    managedPhoto.addUser(managedUser);

                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }

    public void deletePhotoLike(User user, Photo photo) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                Photo managedPhoto = session.get(Photo.class, photo.getId());
                User managedUser = session.get(User.class, user.getId());

                if (managedPhoto != null && managedUser != null)
                    managedPhoto.removeUser(managedUser);

                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }


    //    TODO: Fix this method — it doesn't do what it describes xD
    public int countPhotoLikes(Photo photo) {
        if (photo == null || photo.getId() <= 0)
            return 0;

        return photo.getUsers().size();
    }
}