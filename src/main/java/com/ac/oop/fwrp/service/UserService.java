package com.ac.oop.fwrp.service;

import com.ac.oop.fwrp.dao.UserDao;
import com.ac.oop.fwrp.model.User;

import java.sql.SQLException;

public class UserService {
  private UserDao userDao = new UserDao();

  public User registerUser(User user) throws SQLException {
    return userDao.createUser(user);
  }

  public User loginUser(String email, String password) throws SQLException {
    return userDao.getUserByEmailAndPassword(email, password);
  }
}
