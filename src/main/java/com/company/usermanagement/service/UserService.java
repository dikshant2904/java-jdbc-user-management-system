package com.company.usermanagement.service;

import com.company.usermanagement.model.User;
import com.company.usermanagement.userdao.UserDAO;
import java.util.List;
public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    // ✅ Add User
    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    // ✅ Get All Users
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // ✅ Get User By Id
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

   

    // ✅ Delete User
    public boolean deleteById(int id) {
        return userDAO.deleteById(id);
    }
}