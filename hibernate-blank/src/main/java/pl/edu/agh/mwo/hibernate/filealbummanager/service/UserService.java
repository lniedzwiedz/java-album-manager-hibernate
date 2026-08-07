package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserFromDatabase(String userName) {
        return userRepository.getUserFromDatabase(userName);
    }

    public User getUserFromDatabase(int userId) {
        return userRepository.getUserFromDatabase(userId);
    }

    public List<User> getUsersFromDatabase() {
        return userRepository.getUsersFromDatabase();
    }

    public boolean isUserExistsInDatabase(String userName) {
        return userRepository.isUserExists(userName);
    }

    public void addUser(String userName) {
        userRepository.addUser(userName);
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

}