package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.FriendRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendAddStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendDeleteStatus;

import java.util.List;

public class FriendService {

    private final FriendRepository friendRepository;
    private final UserService userService;

    public FriendService(
            FriendRepository friendRepository,
            UserService userService) {

        this.friendRepository = friendRepository;
        this.userService = userService;
    }

    public FriendAddStatus addFriend(User userLogged, String friendName) {
        if (userLogged == null || userLogged.getId() <= 0)
            return FriendAddStatus.LOGGED_USER_NOT_FOUND;

        if (friendName == null || friendName.isBlank())
            return FriendAddStatus.FRIEND_DATA_NOT_FOUND;

        User friend = userService.getUser(friendName);
        if (friend == null || friend.getId() <= 0)
            return FriendAddStatus.FRIEND_NOT_FOUND;

        if (userLogged.getId() == friend.getId())
            return FriendAddStatus.ALREADY_FRIEND;

        if (areFriends(userLogged, friend))
            return FriendAddStatus.ALREADY_FRIEND;

        friendRepository.addFriend(userLogged, friend);
        return FriendAddStatus.NOW_FRIEND;
    }

    public FriendDeleteStatus deleteFriend(User user, String friendName) {
        if (user == null || user.getId() <= 0)
            return FriendDeleteStatus.LOGGED_USER_NOT_FOUND;

        if (friendName == null || friendName.isBlank())
            return FriendDeleteStatus.FRIEND_NOT_FOUND;

        User friend = userService.getUser(friendName);
        if (friend == null || friend.getId() <= 0)
            return FriendDeleteStatus.FRIEND_NOT_FOUND;

        if (!areFriends(user, friend))
            return FriendDeleteStatus.NOT_FRIEND;

        friendRepository.deleteFriend(user, friend);
        return FriendDeleteStatus.FRIEND_DELETED;
    }

    public boolean areFriends(User user, User friend) {
        return friendRepository.areFriends(user, friend);
    }

    public List<User> getFriends(User user) {
        return friendRepository.getFriends(user);
    }
}