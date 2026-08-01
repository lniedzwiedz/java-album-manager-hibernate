package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.FriendRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

import java.util.List;

public class FriendManagerService {

    private final FriendRepository friendRepository;

    public FriendManagerService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public boolean areWeFriends(User user, String friendName) {
        User friend = friendRepository.getUserByName(friendName);
        if (friend == null) {
            System.out.println(String.format(Messages.FRIEND_NOT_EXIST_DATABASE, friendName));
            return false;
        }
        return friendRepository.areFriends(user, friend);
    }

    public void addFriend(User user, String friendName) {
        User friend = friendRepository.getUserByName(friendName);
        if (friend == null) {
            System.out.println(String.format(Messages.FRIEND_NOT_EXIST, friendName));
            return;
        }
        if (friendRepository.areFriends(user, friend)) {
            return;
        }
        friendRepository.addFriend(user, friend);
    }

    public void deleteFriend(User user, String friendName) {
        User friend = friendRepository.getUserByName(friendName);
        if (friend == null) {
            System.out.println(String.format(Messages.FRIEND_NOT_EXIST, friendName));
            return;
        }
        if (!friendRepository.areFriends(user, friend))
            return;
        friendRepository.deleteFriend(user, friend);
    }

    public void printMyFriends(User user) {
        List<User> friends = friendRepository.getFriends(user);
        System.out.println(Messages.FRIENDS_HEADER);
        if (friends.isEmpty()) {
            System.out.println(Messages.NO_FRIENDS);
            return;
        }
        for (User friend : friends) {
            System.out.println(friend);
        }
    }
}