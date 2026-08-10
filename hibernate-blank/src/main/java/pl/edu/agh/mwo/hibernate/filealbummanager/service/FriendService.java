package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.FriendRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendDeleteResult;

import java.util.List;

public class FriendService {

    private final FriendRepository friendRepository;
    private final UserService userService;

    public FriendService(FriendRepository friendRepository, UserService userService) {
        this.friendRepository = friendRepository;
        this.userService = userService;
    }

    public FriendAddResult addFriend(User user, String friendName) {
        if (user == null)
            return FriendAddResult.LOGGED_USER_NOT_FOUND;

        User friend = userService.getUser(friendName);
        if (friend == null)
            return FriendAddResult.FRIEND_NOT_FOUND;

        if (areFriends(user, friend))
            return FriendAddResult.ALREADY_FRIEND;

        friendRepository.addFriend(user, friend);
        return FriendAddResult.NOW_FRIEND;
    }

    public FriendDeleteResult deleteFriend(User user, String friendName) {
        if (user == null)
            return FriendDeleteResult.LOGGED_USER_NOT_FOUND;

        User friend = userService.getUser(friendName);
        if (friend == null)
            return FriendDeleteResult.FRIEND_NOT_FOUND;

        if (!areFriends(user, friend))
            return FriendDeleteResult.NOT_FRIEND;

        friendRepository.deleteFriend(user, friend);
        return FriendDeleteResult.FRIEND_REMOVED;
    }

    public boolean areFriends(User user, User friend) {
        return friendRepository.areFriends(user, friend);
    }

    public List<User> getFriends(User user) {
        return friendRepository.getFriends(user);
    }
}