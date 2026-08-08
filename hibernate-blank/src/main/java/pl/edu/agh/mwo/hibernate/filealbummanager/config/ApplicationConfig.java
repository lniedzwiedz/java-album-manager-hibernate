package pl.edu.agh.mwo.hibernate.filealbummanager.config;

import org.hibernate.SessionFactory;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.ActionFactory;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.MenuActionHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.CreateAccountAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.LoginAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.application.ApplicationRunner;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.AlbumRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.FriendRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.UserRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleMenu;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsolePrinter;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ApplicationConfig {

    private final SessionFactory sessionFactory;

    public ApplicationConfig(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ApplicationRunner createApplication() {

        ConsolePrinter consolePrinter =
                new ConsolePrinter();

        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(System.in)
                );

        UserRepository userRepository =
                new UserRepository(sessionFactory);

        AlbumRepository albumRepository =
                new AlbumRepository(sessionFactory);

        FriendRepository friendRepository =
                new FriendRepository(sessionFactory);

        UserService userService =
                new UserService(userRepository);

        FriendService friendService =
                new FriendService(friendRepository);

        AlbumService albumService =
                new AlbumService(albumRepository);

        PhotoRepository photoRepository =
                new PhotoRepository(sessionFactory);

        PhotoService photoService =
                new PhotoService(photoRepository);

        ActionFactory actionFactory =
                new ActionFactory(
                        albumService,
                        photoService,
                        friendService,
                        userService,
                        consolePrinter
                );

        MenuActionHandler menuActionHandler =
                new MenuActionHandler(actionFactory);

        CreateAccountAction createAccountAction =
                new CreateAccountAction(userService);

        LoginAction loginAction =
                new LoginAction(
                        userService,
                        createAccountAction
                );

        ConsoleReader consoleReader =
                new ConsoleReader(bufferedReader);

        ConsoleMenu consoleMenu =
                new ConsoleMenu(
                        consolePrinter,
                        consoleReader
                );

        return new ApplicationRunner(
                menuActionHandler,
                loginAction,
                consolePrinter,
                consoleReader,
                consoleMenu
        );
    }
}