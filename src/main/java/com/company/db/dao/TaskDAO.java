package com.company.db.dao;

import com.company.db.ConnectionManagerPostresSQL;
import com.company.db.IConnectionManager;
import com.company.pojo.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**Класс взаимодействия с БД по Задачам
 *
 */
public class TaskDAO {
    public static class TaskDAOException extends Exception {
    }

    private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostresSQL.getInstance();
    }

    /**Функция возвращает список всех задач, хранящихся в БД
     * @return Список всех задач
     * @throws TaskDAOException
     */

    public static List<Task> getAll() throws TaskDAOException {
        List<Task> taskList = new ArrayList<Task>();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.task");

            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("note"),
                        resultSet.getTimestamp("datebegin")==null?null:resultSet.getTimestamp("datebegin").toLocalDateTime(),
                        resultSet.getTimestamp("dateend")==null?null:resultSet.getTimestamp("dateend").toLocalDateTime(),
                        resultSet.getShort("priority"),
                        resultSet.getShort("state"),
                        resultSet.getInt("taskslistid"));

                taskList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaskDAOException();
        }
        return taskList;
    }

    /**Функция возвращает конкретную Задачу из БД
     * @param id
     * @return Задача с указанным id
     * @throws TaskDAOException
     */
    public static Task getById(int id) throws TaskDAOException {
        Task task;

        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "SELECT * FROM public.task where id=?");
            pstatement.setInt(1, id);
            ResultSet resultSet = pstatement.executeQuery();
            resultSet.next();

            task = new Task(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("note"),
                    resultSet.getTimestamp("datebegin")==null?null:resultSet.getTimestamp("datebegin").toLocalDateTime(),
                    resultSet.getTimestamp("dateend")==null?null:resultSet.getTimestamp("dateend").toLocalDateTime(),
                    resultSet.getShort("priority"),
                    resultSet.getShort("state"),
                    resultSet.getInt("taskslistid"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaskDAOException();
        }
        return task;
    }

    /** Функция удаляет информацию о конкретной Задаче из БД
     * @param id
     * @throws TaskDAOException
     */
    public static void deleteById(int id) throws TaskDAOException {
        Task task;

        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "DELETE FROM public.task where id=?");
            pstatement.setInt(1, id);
            pstatement.execute();
            pstatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaskDAOException();
        }
    }

    /**Функция обновляет информацию о Задаче в БД
     * @param task - Задача
     * @throws TaskDAOException
     */
    public static void update(Task task) throws TaskDAOException {
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "UPDATE public.task " +
                            "SET (name,note,datebegin,dateend,priority,state,taskslistid)=" +
                            "(?,?,?,?,?,?,?) WHERE id=?");
            pstatement.setString(1, task.getName());
            pstatement.setString(2, task.getNote());
            pstatement.setTimestamp(3, Timestamp.valueOf(task.getDateBegin()));
            pstatement.setTimestamp(4, Timestamp.valueOf(task.getDateEnd()));
            pstatement.setShort(5, task.getPriority());
            pstatement.setShort(6, task.getState());
            pstatement.setInt(7, task.getTasksListId());
            pstatement.setInt(8, task.getId());
            pstatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaskDAOException();
        }
    }

    /**Функция обновляет информацию в БД о Задачах, переданных в списке
     * @param tasks - список Задач
     * @throws TaskDAOException
     */
    public static void updateAll(List<Task> tasks) throws TaskDAOException {
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "UPDATE public.task " +
                            "SET (name,note,datebegin,dateend,priority,state,taskslistid)=" +
                            "(?,?,?,?,?,?,?) WHERE id=?");
            for (Task task : tasks) {
                pstatement.setString(1, task.getName());
                pstatement.setString(2, task.getNote());
                pstatement.setTimestamp(3, Timestamp.valueOf(task.getDateBegin()));
                pstatement.setTimestamp(4, Timestamp.valueOf(task.getDateEnd()));
                pstatement.setShort(5, task.getPriority());
                pstatement.setShort(6, task.getState());
                pstatement.setInt(7, task.getTasksListId());
                pstatement.setInt(8, task.getId());
                pstatement.addBatch();
            }
            pstatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaskDAOException();
        }
    }

    /**Функция добавляет информацию в БД об одной Задаче
     * @param task - Задача
     * @throws TaskDAOException
     */
    public static void insertOne(Task task) throws TaskDAOException {
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "INSERT INTO public.task " +
                            "(id,name,note,datebegin,dateend,priority,state,taskslistid) " +
                            "VALUES (?,?,?,?,?,?,?,?)");
            pstatement.setInt(1, task.getId());
            pstatement.setString(2, task.getName());
            pstatement.setString(3, task.getNote());
            if (task.getDateBegin() != null) {
                pstatement.setTimestamp(4, Timestamp.valueOf(task.getDateBegin()));
            } else {
                pstatement.setNull(4, Types.TIMESTAMP);
            }
            if (task.getDateEnd() != null) {
                pstatement.setTimestamp(5, Timestamp.valueOf(task.getDateEnd()));
            } else {
                pstatement.setNull(5, Types.TIMESTAMP);
            }
            pstatement.setShort(6, task.getPriority());
            pstatement.setShort(7, task.getState());
            pstatement.setInt(8, task.getTasksListId());
            pstatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaskDAOException();
        }
    }

    /**Функция добавляет информацию в БД о Задачах, переданных в Списке
     * @param tasks - список Задач
     * @throws TaskDAOException
     */
    public static void insertAll(List<Task> tasks) throws TaskDAOException {
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "INSERT INTO public.task " +
                            "(id,name,note,datebegin,dateend,priority,state,taskslistid) " +
                            "VALUES (?,?,?,?,?,?,?,?)");
            for (Task task : tasks) {
                pstatement.setInt(1, task.getId());
                pstatement.setString(2, task.getName());
                pstatement.setString(3, task.getNote());
                if (task.getDateBegin() != null) {
                    pstatement.setTimestamp(4, Timestamp.valueOf(task.getDateBegin()));
                } else {
                    pstatement.setNull(4, Types.TIMESTAMP);
                }
                if (task.getDateEnd() != null) {
                    pstatement.setTimestamp(5, Timestamp.valueOf(task.getDateEnd()));
                } else {
                    pstatement.setNull(5, Types.TIMESTAMP);
                }
                pstatement.setShort(6, task.getPriority());
                pstatement.setShort(7, task.getState());
                pstatement.setInt(8, task.getTasksListId());
                pstatement.addBatch();
            }
            pstatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaskDAOException();
        }
    }

    /** Функция возвращает набор задач, входящих в конкретный Список задач
     * @param tasksListid - id Списка задач
     * @return HashSet<Task> - набор Задач
     * @throws TaskDAOException
     */
    public static HashSet<Task> getTasksSetByTasksListID(int tasksListid) throws TaskDAOException {
        HashSet<Task> tasks = new HashSet<Task>();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.task where taskslistid="+tasksListid);

            while (resultSet.next()) {
                if (resultSet.getTimestamp("datebegin")==null){

                }
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("note"),
                        resultSet.getTimestamp("datebegin")==null?null:resultSet.getTimestamp("datebegin").toLocalDateTime(),
                        resultSet.getTimestamp("dateend")==null?null:resultSet.getTimestamp("dateend").toLocalDateTime(),
                        resultSet.getShort("priority"),
                        resultSet.getShort("state"),
                        resultSet.getInt("taskslistid"));

                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaskDAOException();
        }
        return tasks;

    }

}
