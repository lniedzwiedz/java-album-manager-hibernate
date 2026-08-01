package pl.edu.agh.mwo.hibernate.filealbummanager;

import org.hibernate.Session;
import pl.edu.agh.mwo.hibernate.filealbummanager.config.HibernateUtil;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.AlbumRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.FriendRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.UserRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        UserRepository userRepository = new UserRepository(session);
        AlbumRepository albumRepository = new AlbumRepository(session);
        FriendRepository friendRepository = new FriendRepository(session);
        UserManagerService userManager = new UserManagerService(userRepository);
        FriendManagerService friendManager = new FriendManagerService(friendRepository);
        AlbumManagerService albumManager = new AlbumManagerService(session, albumRepository);
        PhotoRepository photoRepository = new PhotoRepository(session, albumRepository, friendManager);
        PhotoManagerService photoManager = new PhotoManagerService(photoRepository);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        User userLogged = null;
        try {
            do {
                System.out.println();
                System.out.println(Messages.ALBUM_MANAGER_TITLE);
                boolean isUserAccountExis = false;
                boolean isLogin;

                // LOGIN / CREATE ACCOUNT
                do {
                    System.out.println(Messages.SELECT_LOGIN_OR_CREATE);
                    String decision0 = br.readLine();

                    // LOGIN
                    if (decision0.equals("1")) {
                        do {
                            isLogin = false;
                            System.out.println(Messages.LOGIN_USERNAME);
                            String userName = br.readLine();
                            userLogged = userManager.getUserFromDatabase(userName);
                            if (userLogged != null) {
                                System.out.println(String.format(Messages.WELCOME, userLogged.getName()));
                                isUserAccountExis = true;
                                isLogin = true;
                            } else {
                                System.out.println(Messages.USER_NOT_FOUND_RETRY);
                                System.out.println(Messages.SELECT_CREATE_RETRY);
                                String decision1 = br.readLine();
                                if (decision1.equals("1")) {
                                    userManager.addUser(userName);
                                    userLogged = userManager.getUserFromDatabase(userName);
                                    isUserAccountExis = true;
                                    isLogin = true;
                                    System.out.println(String.format(Messages.WELCOME_ACCOUNT_CREATED, userLogged.getName()));
                                } else if (decision1.equals("2")) {
                                    isUserAccountExis = false;
                                } else {
                                    System.out.println(Messages.INVALID_INPUT_E2);
                                }
                            }
                        } while (!isLogin);
                    }

                    // CREATE ACCOUNT
                    else if (decision0.equals("2")) {
                        do {
                            isLogin = false;
                            System.out.println(Messages.CREATE_ACCOUNT_USERNAME);
                            String userName = br.readLine();
                            userLogged = userManager.getUserFromDatabase(userName);
                            if (userLogged != null) {
                                System.out.println(String.format(Messages.WELCOME, userLogged.getName()));
                                System.out.println(Messages.ACCOUNT_EXISTS_AUTO_LOGIN);
                                isUserAccountExis = true;
                                isLogin = true;
                            } else {
                                userManager.addUser(userName);
                                userLogged = userManager.getUserFromDatabase(userName);
                                isUserAccountExis = true;
                                isLogin = true;
                                System.out.println(String.format(Messages.WELCOME_ACCOUNT_CREATED_EXCLAMATION, userLogged.getName()));
                            }
                        } while (!isLogin);
                    } else {
                        System.out.println(Messages.INVALID_INPUT_E3);
                    }
                } while (!isUserAccountExis);

                // MAIN MENU
                do {
                    System.out.println();
                    System.out.println(Messages.MENU_HEADER);
                    System.out.println(Messages.MENU_OPTIONS);
                    input = br.readLine();

                    // 1 - ADD ALBUM
                    if (input.equals("1")) {
                        System.out.println(Messages.ADD_ALBUM_NAME);
                        boolean isAlbumExist;
                        do {
                            isAlbumExist = false;
                            String albumName = br.readLine();
                            int result = albumManager.getProcessingStatusWhileAddingAlbum(userLogged, albumName);
                            if (result == 1) {
                                albumManager.createNewAlbum(userLogged, albumName);
                                System.out.println(Messages.ALBUM_ADDED);
                                isAlbumExist = true;
                            } else if (result == 2) {
                                System.out.println(Messages.ALBUM_EXISTS);
                                System.out.println(Messages.RETRY_ALBUM);
                                String decision2 = br.readLine();
                                if (decision2.equals("1")) {
                                    isAlbumExist = false;
                                } else if (decision2.equals("2")) {
                                    isAlbumExist = true;
                                } else {
                                    System.out.println(Messages.INVALID_INPUT_E33);
                                    isAlbumExist = true;
                                }
                            }
                        } while (!isAlbumExist);
                    }

                    // 2 - DELETE ALBUM
                    else if (input.equals("2")) {
                        System.out.println(Messages.REMOVE_ALBUM_NAME);
                        String albumName = br.readLine();
                        if (albumManager.isAlbumBelongToUser(userLogged, albumName)) {
                            albumManager.deleteAlbum(userLogged, albumName);
                            System.out.println(Messages.ALBUM_REMOVED);
                        } else {
                            System.out.println(String.format(Messages.ALBUM_DELETE_FORBIDDEN, userLogged.getName()));
                        }
                    }

                    // 3 - SHOW MY ALBUMS
                    else if (input.equals("3")) {
                        albumManager.printUserAlbums(userLogged);
                    }

                    // 5 - SHOW USER ALBUMS
                    else if (input.equals("5")) {
                        System.out.println(Messages.ENTER_USERNAME_ALBUMS);
                        String userName = br.readLine();
                        User user = userManager.getUserFromDatabase(userName);
                        if (user != null) {
                            albumManager.printUserAlbums(user);
                        } else {
                            System.out.println(Messages.USER_NOT_FOUND);
                        }
                    }

                    // 7 - SHOW PHOTOS
                    else if (input.equals("7")) {
                        System.out.println(Messages.ENTER_ALBUM_PHOTO);
                        String albumName = br.readLine();
                        if (albumManager.isAlbumBelongToUser(userLogged, albumName)) {
                            photoManager.printPhoto(userLogged, albumName);
                        } else {
                            System.out.println(Messages.ALBUM_NOT_FOUND);
                        }
                    }

                    // 9 - ADD PHOTO
                    else if (input.equals("9")) {
                        System.out.println(Messages.ENTER_ALBUM_ADD_PHOTO);
                        String albumName = br.readLine();
                        if (albumManager.isAlbumBelongToUser(userLogged, albumName)) {
                            System.out.println(Messages.ADD_PHOTO_NAME);
                            String photoName = br.readLine();
                            boolean isPictureExist;
                            do {
                                isPictureExist = false;
                                int result = photoManager.getProcessingStatusWhileAddingPhoto(userLogged, albumName, photoName);
                                if (result == 1) {
                                    photoManager.addPhoto(photoName, albumName, userLogged);
                                    System.out.println(Messages.PHOTO_ADDED);
                                    isPictureExist = true;
                                } else if (result == 2) {
                                    System.out.println(Messages.PHOTO_EXISTS);
                                    System.out.println(Messages.RETRY_PHOTO);
                                    String decision2 = br.readLine();
                                    if (decision2.equals("1")) {
                                        isPictureExist = false;
                                    } else if (decision2.equals("2")) {
                                        isPictureExist = true;
                                    } else {
                                        System.out.println(Messages.INVALID_INPUT_E7);
                                        isPictureExist = true;
                                    }
                                }
                            } while (!isPictureExist);
                        } else {
                            System.out.println(String.format(Messages.PHOTO_ADD_FORBIDDEN, userLogged.getName()));
                        }
                    }

                    // 10 - DELETE PHOTO
                    else if (input.equals("10")) {
                        System.out.println(Messages.REMOVE_PHOTO_NAME);
                        String photoName = br.readLine();
                        System.out.println(Messages.ALBUM_NAME);
                        String albumName = br.readLine();
                        if (albumManager.isAlbumBelongToUser(userLogged, albumName) && photoManager.isPictureBelongToUser(userLogged, albumName, photoName)) {
                            photoManager.deletePhoto(photoName, albumName, userLogged);
                            System.out.println(Messages.PICTURE_DELETED);
                        } else {
                            System.out.println(String.format(Messages.PICTURE_DELETE_FORBIDDEN, userLogged.getName()));
                        }
                    }

                    // 11 - LIKE PHOTO
                    else if (input.equals("11")) {
                        System.out.println(Messages.ADD_LIKE_PHOTO_NAME);
                        String photoName = br.readLine();
                        System.out.println(Messages.ALBUM_NAME_LIKE);
                        String albumName = br.readLine();
                        int result = photoManager.getProcessingStatusForPhotoLike(userLogged, albumName, photoName);
                        if (result == 4) {
                            photoManager.addPhotoLike(userLogged, albumName, photoName);
                            System.out.println(Messages.PHOTO_LIKE_ADDED);
                        } else if (result == 1) {
                            System.out.println(String.format(Messages.ALREADY_LIKE_PHOTO, userLogged.getName()));
                        } else if (result == 2) {
                            System.out.println(Messages.PHOTO_NOT_IN_ALBUM);
                        } else if (result == 3) {
                            System.out.println(Messages.ALBUM_DOES_NOT_EXIST);
                        } else if (result == 5) {
                            System.out.println(Messages.NOT_FRIEND_PHOTO_OWNER);
                        }
                    }

                    // 12 - UNLIKE PHOTO
                    else if (input.equals("12")) {
                        System.out.println(Messages.REMOVE_PHOTO_LIKE_NAME);
                        String photoName = br.readLine();
                        System.out.println(Messages.ALBUM_NAME);
                        String albumName = br.readLine();
                        int result = photoManager.getProcessingStatusForPhotoLike(userLogged, albumName, photoName);
                        if (result == 4) {
                            System.out.println(Messages.NEVER_LIKED_PHOTO);
                        } else if (result == 1) {
                            photoManager.deletePhotoLike(userLogged, albumName, photoName);
                            System.out.println(Messages.PHOTO_LIKE_REMOVED);
                        } else if (result == 2) {
                            System.out.println(Messages.PHOTO_NOT_IN_ALBUM);
                        } else if (result == 3) {
                            System.out.println(Messages.ALBUM_DOES_NOT_EXIST);
                        } else if (result == 5) {
                            System.out.println(Messages.NOT_FRIEND_PHOTO_OWNER_NO_LIKE);
                        }
                    }

                    // 20 - ADD FRIEND
                    else if (input.equals("20")) {
                        System.out.println(Messages.ADD_FRIEND_USERNAME);
                        String friendName = br.readLine();
                        boolean userExists = userManager.isUserExistsInDatabase(friendName);
                        if (userExists) {
                            if (friendManager.areWeFriends(userLogged, friendName)) {
                                System.out.println(String.format(Messages.ALREADY_FRIEND, friendName));
                            } else {
                                friendManager.addFriend(userLogged, friendName);
                                System.out.println(String.format(Messages.NOW_FRIEND, friendName));
                            }
                        } else {
                            System.out.println(String.format(Messages.USER_DOES_NOT_EXIST, friendName));
                        }
                    }

                    // 21 - DELETE FRIEND
                    else if (input.equals("21")) {
                        System.out.println(Messages.DELETE_FRIEND_USERNAME);
                        String friendName = br.readLine();
                        boolean userExists = userManager.isUserExistsInDatabase(friendName);
                        if (userExists) {
                            if (friendManager.areWeFriends(userLogged, friendName)) {
                                friendManager.deleteFriend(userLogged, friendName);
                            } else {
                                System.out.println(String.format(Messages.NOT_FRIEND, friendName));
                            }
                        } else {
                            System.out.println(String.format(Messages.USER_DOES_NOT_EXIST, friendName));
                        }
                    }

                    // 23 - SHOW FRIENDS
                    else if (input.equals("23")) {
                        friendManager.printMyFriends(userLogged);
                    }

                    // 666 - DELETE ACCOUNT
                    else if (input.equals("666")) {
                        System.out.println(Messages.CONFIRM_DELETE_ACCOUNT);
                        String goodbye = br.readLine();
                        if (goodbye.equals("1")) {
                            String userName = userLogged.getName();
                            userManager.deleteUser(userLogged);
                            System.out.println(String.format(Messages.GOODBYE, userName));
                            userLogged = null;
                            input = "exit2";
                        } else if (goodbye.equals("2")) {
                            System.out.println(Messages.WISE_CHOICE);
                        }
                    }

                    // 999 - LOGOUT
                    else if (input.equals("999")) {
                        System.out.println(Messages.CONFIRM_LOGOUT);
                        String goodbye = br.readLine();
                        if (goodbye.equals("1")) {
                            System.out.println(String.format(Messages.GOODBYE, userLogged.getName()));
                            userLogged = null;
                            input = "exit2";
                        } else if (goodbye.equals("2")) {
                            System.out.println();
                        }
                    }
                } while (!input.equals("exit2"));
            } while (!input.equals("exit"));
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