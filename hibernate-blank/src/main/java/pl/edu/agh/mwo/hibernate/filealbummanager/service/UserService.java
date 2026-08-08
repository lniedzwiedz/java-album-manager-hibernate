package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String userName) {
        return userRepository.getUser(userName);
    }

    public User getUser(int userId) {
        return userRepository.getUser(userId);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public boolean exists(String userName) {
        return userRepository.exists(userName);
    }

//    public void createUser(String userName) {
//        userRepository.save(userName);
//    }

    public void createUser(String userName) {
        User user = new User();
        user.setName(userName);
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}