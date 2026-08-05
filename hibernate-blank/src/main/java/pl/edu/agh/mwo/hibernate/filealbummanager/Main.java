package pl.edu.agh.mwo.hibernate.filealbummanager;

import org.hibernate.Session;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.ActionFactory;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.MenuActionHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.CreateAccountAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.LoginAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.application.ApplicationRunner;
import pl.edu.agh.mwo.hibernate.filealbummanager.config.HibernateUtil;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.AlbumRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.FriendRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.UserRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        UserRepository userRepository = new UserRepository(session);
        AlbumRepository albumRepository = new AlbumRepository(session);
        FriendRepository friendRepository = new FriendRepository(session);
        UserService userService = new UserService(userRepository);
        FriendService friendService = new FriendService(friendRepository);
        AlbumService albumService = new AlbumService(session, albumRepository);
        PhotoRepository photoRepository = new PhotoRepository(session, albumRepository, friendService);
        PhotoService photoService = new PhotoService(photoRepository);
        ActionFactory actionFactory = new ActionFactory(albumService, photoService, friendService, userService);
        MenuActionHandler menuActionHandler = new MenuActionHandler(actionFactory);
        CreateAccountAction createAccountAction = new CreateAccountAction(userService);
        LoginAction loginAction = new LoginAction(userService, createAccountAction);
        ApplicationRunner applicationRunner = new ApplicationRunner(menuActionHandler, loginAction);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
            HibernateUtil.getSessionFactory().close();
        }
    }
}