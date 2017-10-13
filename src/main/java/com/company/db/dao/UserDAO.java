package com.company.db.dao;

import com.company.db.ConnectionManagerPostresSQL;
import com.company.db.IConnectionManager;
import com.company.pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**Класс взаимодействия с БД по Пользователям
 *
 */
public class UserDAO {
    public static class UserDAOException extends Exception {
    }

    private static IConnectionManager manager;
    static {
        manager = ConnectionManagerPostresSQL.getInstance();
    }

    /**Функция возвращает из БД всех Пользователей
     * @return
     * @throws UserDAOException
     */
    public static List<User> getAll() throws UserDAOException {
        List<User> userList = new ArrayList<User>();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.user");

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getDate("dateofregistration").toLocalDate(),
                        resultSet.getBoolean("isadmin"),
                        resultSet.getBoolean("islock"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
        return userList;
    }

    /**Функция возвращает из БД конкретного Пользователя
     * @param id -id Пользователя
     * @return User - Пользователь
     * @throws UserDAOException
     */
    public static User getById(int id) throws UserDAOException {
        User user;

        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "SELECT * FROM public.user where id=?");
            pstatement.setInt(1,id);
            ResultSet resultSet = pstatement.executeQuery();
            resultSet.next();

            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getDate("dateofregistration").toLocalDate(),
                    resultSet.getBoolean("isadmin"),
                    resultSet.getBoolean("islock"));

    } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
        return user;
    }

    /**Функция удаляет из БД Пользователя
     * @param id -id Пользователя
     * @throws UserDAOException
     */
    public static void deleteById(int id) throws UserDAOException {
        User user;

        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "DELETE FROM public.task " +
                            "where tasksListId IN (SELECT id FROM public.taskslist WHERE userid=?)");
            pstatement.setInt(1,id);
            pstatement.execute();
            PreparedStatement pstatement1 = manager.getConnection().prepareStatement(
                    "DELETE FROM public.taskslist " +
                            "WHERE userid=?");
            pstatement1.setInt(1,id);
            pstatement1.execute();
            PreparedStatement pstatement2 = manager.getConnection().prepareStatement(
                    "DELETE FROM public.user " +
                            "WHERE id=?");
            pstatement2.setInt(1,id);
            pstatement2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }

    /**Функция обновляет информацию в БД о конкретном Пользователе
     * @param user - Пользователь
     * @throws UserDAOException
     */
    public static void update(User user) throws UserDAOException {
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "UPDATE public.user " +
                            "SET (firstname,lastname,email,login,password,dateofregistration,isadmin,islock)=" +
                            "(?,?,?,?,?,?,?,?) WHERE id=?");
            pstatement.setString(1,user.getFirstName());
            pstatement.setString(2,user.getLastName());
            pstatement.setString(3,user.getEmail());
            pstatement.setString(4,user.getLogin());
            pstatement.setString(5,user.getPassword());
            pstatement.setDate(6,Date.valueOf(user.getDateOfRegistration()));
            pstatement.setBoolean(7,user.isAdmin());
            pstatement.setBoolean(8,user.isLock());
            pstatement.setInt(9,user.getId());
            pstatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }

    /** Функция обновляет информацию в БД о Пользователях, переданных в списке
     * @param users - список Пользователей
     * @throws UserDAOException
     */
    public static void updateAll(List<User> users) throws UserDAOException {
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "UPDATE public.user " +
                            "SET (firstname,lastname,email,login,password,dateofregistration,isadmin,islock)=" +
                            "(?,?,?,?,?,?,?,?) WHERE id=?");
            for(User user:users) {
                pstatement.setString(1, user.getFirstName());
                pstatement.setString(2, user.getLastName());
                pstatement.setString(3, user.getEmail());
                pstatement.setString(4, user.getLogin());
                pstatement.setString(5, user.getPassword());
                pstatement.setDate(6, Date.valueOf(user.getDateOfRegistration()));
                pstatement.setBoolean(7, user.isAdmin());
                pstatement.setBoolean(8, user.isLock());
                pstatement.setInt(9, user.getId());
                pstatement.addBatch();
            }
            pstatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }

    /**Функция добавляет в БД одного Пользователя
     * @param user - Пользователь
     * @throws UserDAOException
     */
    public static void insertOne(User user) throws UserDAOException {
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "INSERT INTO public.user " +
                            "(id,firstname,lastname,email,login,password,dateofregistration,isadmin,islock) " +
                            "VALUES (?,?,?,?,?,?,?,?,?)");
            pstatement.setInt(1, user.getId());
            pstatement.setString(2, user.getFirstName());
            pstatement.setString(3, user.getLastName());
            pstatement.setString(4, user.getEmail());
            pstatement.setString(5, user.getLogin());
            pstatement.setString(6, user.getPassword());
            pstatement.setDate(7, Date.valueOf(user.getDateOfRegistration()));
            pstatement.setBoolean(8, user.isAdmin());
            pstatement.setBoolean(9, user.isLock());
            pstatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }

    /**Функция добавляет информацию в БД о Пользователях, переданных в списке
     * @param users - список Пользователей
     * @throws UserDAOException
     */
    public static void insertAll(List<User> users) throws UserDAOException {
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "INSERT INTO public.user " +
                            "(id,firstname,lastname,email,login,password,dateofregistration,isadmin,islock) " +
                            "VALUES (?,?,?,?,?,?,?,?,?)");
            for(User user:users) {
                pstatement.setInt(1, user.getId());
                pstatement.setString(2, user.getFirstName());
                pstatement.setString(3, user.getLastName());
                pstatement.setString(4, user.getEmail());
                pstatement.setString(5, user.getLogin());
                pstatement.setString(6, user.getPassword());
                pstatement.setDate(7, Date.valueOf(user.getDateOfRegistration()));
                pstatement.setBoolean(8, user.isAdmin());
                pstatement.setBoolean(9, user.isLock());
                pstatement.addBatch();
            }
            pstatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }
    public static User getByIdWithTasksLists(int id) throws UserDAOException {
        User user=null;
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "SELECT * FROM public.user where id=?");
            pstatement.setInt(1, id);
            ResultSet resultSet = pstatement.executeQuery();
            resultSet.next();

            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getDate("dateofregistration").toLocalDate(),
                    resultSet.getBoolean("isadmin"),
                    resultSet.getBoolean("islock"));
//            user.setTasksLists(TasksListDAO.getTasksListsSetByUser(resultSet.getInt("id"), true));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
/*
        } catch (TasksListDAO.TasksListDAOException e) {
            e.printStackTrace();
        } catch (TaskDAO.TaskDAOException e) {
            e.printStackTrace();
*/
        }
        return user;
    }


}
