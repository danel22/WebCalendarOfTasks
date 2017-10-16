package services;

import db.dao.UserDAO;
import db.dao.UserDAOImpl;

public class AuthorizationServiceImpl implements AuthorizationService {
    private static UserDAO userDAO = new UserDAOImpl();

    @Override
    public Boolean auth(String login, String password) {
        if (login==null || password==null){
            return false;
        }
        if (userDAO.getUserByLoginAndPassword(login, PasswordEncoder.encode(password))!=null) {
            return true;
        }
        return false;
    }
}
