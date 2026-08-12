package pl.edu.agh.mwo.hibernate.filealbummanager.config;

import org.hibernate.SessionFactory;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.ActionFactory;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.CreateAccountAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.LoginAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.handler.MenuActionHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.handler.account.AuthenticationMenuActionHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.handler.account.CreateAccountHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.handler.account.LoginHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.application.ApplicationRunner;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.AlbumRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.FriendRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoLikeRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.UserRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoLikeService;
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

        ConsoleReader consoleReader =
                new ConsoleReader(
                        bufferedReader
                );

        ConsoleMenu consoleMenu =
                new ConsoleMenu(
                        consoleReader,
                        consolePrinter
                );

        UserRepository userRepository =
                new UserRepository(
                        sessionFactory
                );

        AlbumRepository albumRepository =
                new AlbumRepository(
                        sessionFactory
                );

        FriendRepository friendRepository =
                new FriendRepository(
                        sessionFactory
                );

        PhotoRepository photoRepository =
                new PhotoRepository(
                        sessionFactory
                );

        PhotoLikeRepository photoLikeRepository =
                new PhotoLikeRepository(
                        sessionFactory
                );

        UserService userService =
                new UserService(
                        userRepository
                );

        FriendService friendService =
                new FriendService(
                        friendRepository,
                        userService
                );

        AlbumService albumService =
                new AlbumService(
                        albumRepository
                );

        PhotoService photoService =
                new PhotoService(
                        albumRepository,
                        photoRepository
                );

        PhotoLikeService photoLikeService =
                new PhotoLikeService(
                        userService,
                        albumService,
                        photoService,
                        photoLikeRepository
                );

        CreateAccountHandler createAccountHandler =
                new CreateAccountHandler();

        LoginHandler loginHandler =
                new LoginHandler();

        CreateAccountAction createAccountAction =
                new CreateAccountAction(
                        userService,
                        createAccountHandler
                );

        LoginAction loginAction =
                new LoginAction(
                        userService,
                        loginHandler
                );

        AuthenticationMenuActionHandler authenticationMenuActionHandler =
                new AuthenticationMenuActionHandler(
                        loginAction,
                        createAccountAction
                );

        ActionFactory actionFactory =
                new ActionFactory(
                        userService,
                        friendService,
                        albumService,
                        photoService,
                        photoLikeService,
                        consolePrinter
                );

        MenuActionHandler menuActionHandler =
                new MenuActionHandler(
                        actionFactory
                );

        return new ApplicationRunner(
                authenticationMenuActionHandler,
                menuActionHandler,
                userService,
                consoleReader,
                consolePrinter,
                consoleMenu
        );
    }
}