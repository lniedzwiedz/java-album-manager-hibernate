package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.FriendRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

import java.util.List;

public class FriendService {

    private final FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public boolean areWeFriends(User user, String friendName) {
        if (user == null || friendName == null || friendName.isBlank()) return false;

        User friend = friendRepository.getUserByName(friendName);
        if (friend == null) {
            System.out.println(String.format(FriendMessages.FRIEND_NOT_FOUND, friendName));
            return false;
        }

        return friendRepository.areFriends(user, friend);
    }

    public void addFriend(User user, String friendName) {
        if (user == null || friendName == null || friendName.isBlank()) return;

        User friend = friendRepository.getUserByName(friendName);
        if (friend == null) {
            System.out.println(String.format(FriendMessages.FRIEND_NOT_FOUND, friendName));
            return;
        }

        if (friendRepository.areFriends(user, friend))
            return;

        friendRepository.addFriend(user, friend);
    }

    public void deleteFriend(User user, String friendName) {
        if (user == null || friendName == null || friendName.isBlank()) return;

        User friend = friendRepository.getUserByName(friendName);

        if (friend == null) {
            System.out.println(String.format(FriendMessages.FRIEND_NOT_FOUND, friendName));
            return;
        }

        if (!friendRepository.areFriends(user, friend)) return;

        friendRepository.deleteFriend(user, friend);
    }

    public List<User> getFriends(User user) {
        if (user == null) return List.of();
        return friendRepository.getFriends(user);
    }

    private boolean isInvalidInput(User user, String friendName) {
        return user == null || friendName == null || friendName.isBlank();
    }
}