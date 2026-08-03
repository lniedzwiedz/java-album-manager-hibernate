package pl.edu.agh.mwo.hibernate.filealbummanager;

import org.hibernate.Session;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.ActionFactory;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.MenuActionHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.application.ApplicationRunner;
import pl.edu.agh.mwo.hibernate.filealbummanager.config.HibernateUtil;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.AlbumRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.FriendRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.UserRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserManagerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        UserRepository userRepository =
                new UserRepository(session);

        AlbumRepository albumRepository =
                new AlbumRepository(session);

        FriendRepository friendRepository =
                new FriendRepository(session);

        UserManagerService userManager =
                new UserManagerService(
                        userRepository
                );

        FriendManagerService friendManager =
                new FriendManagerService(
                        friendRepository
                );

        AlbumManagerService albumManager =
                new AlbumManagerService(
                        session,
                        albumRepository
                );

        PhotoRepository photoRepository =
                new PhotoRepository(
                        session,
                        albumRepository,
                        friendManager
                );

        PhotoManagerService photoManager =
                new PhotoManagerService(
                        photoRepository
                );

        ActionFactory actionFactory =
                new ActionFactory(
                        albumManager,
                        photoManager,
                        friendManager,
                        userManager
                );

        MenuActionHandler menuActionHandler =
                new MenuActionHandler(
                        actionFactory
                );

        ApplicationRunner applicationRunner =
                new ApplicationRunner(
                        userManager,
                        menuActionHandler
                );

        BufferedReader br =
                new BufferedReader(
                        new InputStreamReader(System.in)
                );

        try {

            applicationRunner.run(br);

        } catch (IOException e) {

            throw new RuntimeException(e);

        } finally {

            try {

                br.close();

            } catch (IOException e) {

                e.printStackTrace();
            }

            session.close();

            HibernateUtil
                    .getSessionFactory()
                    .close();
        }
    }
}