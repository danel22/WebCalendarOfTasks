package services;

import db.dao.UserDAO;
import db.dao.UserDAOImpl;
import pojo.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrationServiceImpl implements RegistrationService {
    private static UserDAO userDAO=new UserDAOImpl();

    @Override
    public Boolean regUser(String login, String password) {
        if (login==null || password == null){
            return false;
        }
        return userDAO.createUser(new User(login, PasswordEncoder.encode(password)));
    }
}
