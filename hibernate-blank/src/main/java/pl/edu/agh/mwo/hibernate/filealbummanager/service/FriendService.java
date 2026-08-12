package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.FriendRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.friend.AddFriendStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.friend.DeleteFriendStatus;

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

    public AddFriendStatus addFriend(User userLogged, String friendName) {
        if (userLogged == null || userLogged.getId() <= 0)
            return AddFriendStatus.LOGGED_USER_NOT_FOUND;

        if (friendName == null || friendName.isBlank())
            return AddFriendStatus.FRIEND_DATA_NOT_FOUND;

        User friend = userService.getUser(friendName);
        if (friend == null || friend.getId() <= 0)
            return AddFriendStatus.FRIEND_NOT_FOUND;

        if (userLogged.getId() == friend.getId())
            return AddFriendStatus.ALREADY_FRIEND;

        if (areFriends(userLogged, friend))
            return AddFriendStatus.ALREADY_FRIEND;

        friendRepository.addFriend(userLogged, friend);
        return AddFriendStatus.NOW_FRIEND;
    }

    public DeleteFriendStatus deleteFriend(User user, String friendName) {
        if (user == null || user.getId() <= 0)
            return DeleteFriendStatus.LOGGED_USER_NOT_FOUND;

        if (friendName == null || friendName.isBlank())
            return DeleteFriendStatus.FRIEND_NOT_FOUND;

        User friend = userService.getUser(friendName);
        if (friend == null || friend.getId() <= 0)
            return DeleteFriendStatus.FRIEND_NOT_FOUND;

        if (!areFriends(user, friend))
            return DeleteFriendStatus.NOT_FRIEND;

        friendRepository.deleteFriend(user, friend);
        return DeleteFriendStatus.FRIEND_DELETED;
    }

    public boolean areFriends(User user, User friend) {
        return friendRepository.areFriends(user, friend);
    }

    public List<User> getFriends(User user) {
        return friendRepository.getFriends(user);
    }
}