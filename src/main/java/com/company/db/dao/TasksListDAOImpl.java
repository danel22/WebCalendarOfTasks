package com.company.db.dao;

import com.company.db.ConnectionManagerPostresSQL;
import com.company.db.IConnectionManager;
import com.company.pojo.TasksList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**Класс взаимодействия с БД по Спискам задач
 *
 */
public class TasksListDAO {
    public static class TasksListDAOException extends Exception {
    }

    private static IConnectionManager manager;
    static {
        manager = ConnectionManagerPostresSQL.getInstance();
    }

    /**Функция возвращает список всех Списков задач, хранящихся в БД
     * @return ArrayList<TasksList> - список Списков задач
     * @throws UserDAO.UserDAOException
     */
    public static List<TasksList> getAll(boolean isAddTasksSet) throws TasksListDAOException {
        List<TasksList> listOftasksList = new ArrayList<TasksList>();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.taskslist");

            while (resultSet.next()) {
                TasksList tasksList = new TasksList(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("userId"));

                        listOftasksList.add(tasksList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TasksListDAO.TasksListDAOException();
        }
        return listOftasksList;
    }

    /**Функция возвращает из БД конкретный Список задач
     * @param id - id Списка задач
     * @return TasksList - Список задач
     * @throws TasksListDAOException
     */
    public static TasksList getById(int id) throws TasksListDAOException {
        TasksList tasksList;
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "SELECT * FROM public.taskslist where id=?");
            pstatement.setInt(1,id);
            ResultSet resultSet = pstatement.executeQuery();
            resultSet.next();

            tasksList= new TasksList(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("userId"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw new TasksListDAO.TasksListDAOException();
        }
        return tasksList;
    }

    /**Функция удаляет конкретный Список задач из БД
     * @param id - id Списка задач
     * @throws TasksListDAOException
     */
    public static void deleteById(int id) throws TasksListDAOException {
        TasksList tasksList;

        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "DELETE FROM public.task where taskslistid=?");
            pstatement.setInt(1, id);
            pstatement.execute();
            pstatement.close();
            PreparedStatement pstatement1 = manager.getConnection().prepareStatement(
                    "DELETE FROM public.tasksList where id=?");
            pstatement1.setInt(1, id);
            pstatement1.execute();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new TasksListDAOException();
        }
    }

    /**Функция обновляет информацию в БД о конкретном Списке задач
     * @param tasksList - Список задач
     * @throws TasksListDAOException
     */
    public static void update(TasksList tasksList) throws TasksListDAOException {
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "UPDATE public.taskslist SET (name,userid)=(?,?) WHERE id=?");
            pstatement.setString(1,tasksList.getName());
            pstatement.setInt(2,tasksList.getUserId());
            pstatement.setInt(3,tasksList.getId());
            pstatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TasksListDAOException();
        }
    }

    /**Функция обновляет информацию в БД о Списках здач, переданных в списке
     * @param tasksListList - список Списков задач
     * @throws TasksListDAOException
     */
    public static void updateAll(List<TasksList> tasksListList) throws TasksListDAOException {
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "UPDATE public.taskslist SET (name,userid)=(?,?) WHERE id=?");
            for(TasksList tasksList:tasksListList) {
                pstatement.setString(1,tasksList.getName());
                pstatement.setInt(2,tasksList.getUserId());
                pstatement.setInt(3,tasksList.getId());
                pstatement.addBatch();
            }
            pstatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TasksListDAOException();
        }
    }

    /**Функция добавляет в БД один Список задач
     * @param tasksList - Список задач
     * @throws TasksListDAOException
     */
    public static void insertOne(TasksList tasksList) throws TasksListDAOException {
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "INSERT INTO public.taskslist (id,name,userid) VALUES (?,?,?)");
            pstatement.setInt(1, tasksList.getId());
            pstatement.setString(2,tasksList.getName());
            pstatement.setInt(3,tasksList.getUserId());
            pstatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TasksListDAOException();
        }
    }

    /**Функция добавляет в БД Списки задач, переданных в списке
     * @param tasksListList - список Списков задач
     * @throws TasksListDAOException
     */
    public static void insertAll(List<TasksList> tasksListList) throws TasksListDAOException {
        try {
            PreparedStatement pstatement = manager.getConnection().prepareStatement(
                    "INSERT INTO public.taskslist (id,name,userid) VALUES (?,?,?)");
            for(TasksList tasksList:tasksListList) {
                pstatement.setInt(1, tasksList.getId());
                pstatement.setString(2,tasksList.getName());
                pstatement.setInt(3,tasksList.getUserId());
                pstatement.addBatch();
            }
            pstatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TasksListDAOException();
        }
    }


    /**Функция возвращает набор Списков задач, принадлежащих конкретному ползователю
     * @param uid - уникальный идентификатор Пользователя
     * @param isAddTasksSet - получать или не получать набор Задач для Списка задач
     * @return Set<TasksList> - набор Списков задач
     * @throws TasksListDAOException
     */
    public static HashSet<TasksList> getTasksListsSetByUser(int uid, boolean isAddTasksSet) throws TasksListDAOException, TaskDAO.TaskDAOException {
        HashSet<TasksList> setOftasksList = new HashSet<>();

        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.taskslist where userid="+uid);

            while (resultSet.next()) {
                setOftasksList.add(createTaskList(resultSet,isAddTasksSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TasksListDAO.TasksListDAOException();
        }
        return setOftasksList;
    }
    public static HashSet<TasksList> getTasksListsSetByUser(int uid) throws TaskDAO.TaskDAOException, TasksListDAOException {
        return getTasksListsSetByUser(uid, false);
    }

    private static TasksList createTaskList(ResultSet rs, boolean isAddTasksSet) throws TasksListDAOException, TaskDAO.TaskDAOException {
        TasksList tasksList = null;
        try {
            tasksList = new TasksList(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("userId"));
            if (isAddTasksSet) {
                tasksList.setTasks(TaskDAO.getTasksSetByTasksListID(rs.getInt("id")));
            }
        }catch (SQLException e) {
                e.printStackTrace();
                throw new TasksListDAO.TasksListDAOException();
        } catch (TaskDAO.TaskDAOException e) {
            e.printStackTrace();
            throw new TaskDAO.TaskDAOException();
        }
        return tasksList;
    }


}
