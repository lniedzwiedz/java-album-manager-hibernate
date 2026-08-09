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

    public FriendAddResult checkFriendAddStatus(User user, String friendName) {
        if (user == null)
            return FriendAddResult.USER_NOT_FOUND;

        User friend = userService.getUser(friendName);
        if (friend == null)
            return FriendAddResult.USER_NOT_FOUND;

        if (areFriends(user, friend))
            return FriendAddResult.ALREADY_FRIEND;

        return FriendAddResult.NOW_FRIEND;
    }

    public void addFriend(User user, String friendName) {
        User friend = userService.getUser(friendName);
        if (friend == null)
            return;

        friendRepository.addFriend(user, friend);
    }

    public void addFriend(User user, User friend) {
        friendRepository.addFriend(user, friend);
    }

    public FriendDeleteResult checkFriendDeleteStatus(User user, String friendName) {
        if (user == null)
            return FriendDeleteResult.USER_NOT_FOUND;

        User friend = userService.getUser(friendName);
        if (friend == null)
            return FriendDeleteResult.USER_NOT_FOUND;

        if (!areFriends(user, friend))
            return FriendDeleteResult.NOT_FRIEND;

        return FriendDeleteResult.FRIEND_REMOVED;
    }

    public void deleteFriend(User user, String friendName) {
        User friend = userService.getUser(friendName);
        if (friend == null)
            return;

        friendRepository.deleteFriend(user, friend);
    }

    public void deleteFriend(User user, User friend) {
        friendRepository.deleteFriend(user, friend);
    }

    public boolean areFriends(User user, User friend) {
        return friendRepository.areFriends(user, friend);
    }

    public List<User> getFriends(User user) {
        return friendRepository.getFriends(user);
    }
}