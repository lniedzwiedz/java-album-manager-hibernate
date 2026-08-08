package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.FriendRepository;

import java.util.List;

public class FriendService {

    private final FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public User getUserByName(String userName) {
        return friendRepository.getUserByName(userName);
    }

    public User getUserById(int userId) {
        return friendRepository.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return friendRepository.getAllUsers();
    }

    public void addFriend(User user, User friend) {
        friendRepository.addFriend(user, friend);
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