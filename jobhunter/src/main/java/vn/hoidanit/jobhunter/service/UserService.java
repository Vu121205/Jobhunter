package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleCreateUser(User user) {
        this.userRepository.save(user);
        return user;
    }

    public void handleDeleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public User fetchUserById(long id) {
        Optional<User> userOpt = this.userRepository.findById(id);
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        return null;
    }

    public List<User> fetchAllUsers() {
        return this.userRepository.findAll();
    }
    
    public User handleUpdateUser(User user) {
        User currUser = this.fetchUserById(user.getId());
        if (currUser != null) {
            currUser.setName(user.getName());
            currUser.setEmail(user.getEmail());
            currUser.setPassword(user.getPassword());
            this.userRepository.save(currUser);
            return currUser;
        }
        return null;
    }

    public User handleGetUserByUsername(String username) {
        return this.userRepository.findByEmail(username);
    }

}
