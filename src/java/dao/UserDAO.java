package dao;

import model.User;

public interface UserDAO {
    boolean insertUser(String username, String firstName, String lastName, String email, String password);
    User getUserByUsername(String username);
}
