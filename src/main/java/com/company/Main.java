package com.company;

import com.company.db.dao.*;
import com.company.pojo.*;
import com.company.xmlroutine.JAXB;
import com.company.xmlroutine.Tasks;
import com.company.xmlroutine.TasksLists;
import com.company.xmlroutine.Users;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JAXBException {

//        try {

            Main.loadDBtoXML();
//            System.out.println(u1.toString());
/*
            List<User> userList = UserDAO.getAll();
            for(User user : userList){
                System.out.println(user.toString());
            }
            User user1=UserDAO.getById(1);
            System.out.println(user1.toString());
*/

/*
            List<TasksList> tasksListList = TasksListDAO.getAll();
            for(TasksList tasksList : tasksListList){
                System.out.println(tasksList.toString());
            }
            TasksList tasksList1=TasksListDAO.getById(3);
            System.out.println(tasksList1.toString());

        } catch (UserDAO.UserDAOException e) {
            e.printStackTrace();
        }
*/

/*
            List<Task> taskList = TaskDAO.getAll();
            for(Task task : taskList){
                System.out.println(task.toString());
            }
            Task task1=TaskDAO.getById(6);
            System.out.println(task1.toString());

        } catch (TaskDAO.TaskDAOException e) {
            e.printStackTrace();
        }
*/

/*           User user = UserDAO.getById(1);
            user.setFirstName("Иван");
            user.setLastName("Иванов");
            user.setDateOfRegistration(LocalDate.parse("2016-12-01", XMLDateTimeFormatter.ofPattern("yyyy-MM-dd")));
            listUser.add(user);

            User user1 = UserDAO.getById(2);
            user1.setEmail("Prekrasnoe@daleko.ru");
            user1.setLogin("daleko-daleko");
            user1.setLock(true);
            listUser.add(user1);
                        UserDAO.updateAll(listUser);*/

//            User u1=new User
//                    (3,"Маргарита","Веселова","mrgo@veselova.com","KorolevaMargo","123456",LocalDate.now(),false,false);
/*
            User u2=new User
                    (4,"Юлий","Цезарь","mir@rim.may","julius","gaius",LocalDate.now(),false,false);
            listUser.add(u2);
            User u3=new User
                    (5,"Филипп","Киркоров","filya@kira.us","pugecheva","FK99999",LocalDate.now(),false,false);
            listUser.add(u3);
//            UserDAO.insertOne(u1);
            UserDAO.insertAll(listUser);
*/
//            List<TasksList> tasksListList= new ArrayList<>();
/*
            TasksList tl1=TasksListDAO.getById(1);
            TasksList tl2=TasksListDAO.getById(2);
            TasksList tl3=TasksListDAO.getById(3);

            tl1.setName("Ежедневные задачи");
            tl2.setUserId(1);
            tasksListList.add(tl2);
            tl3.setName("Ежемесячные задачи");
            tasksListList.add(tl3);

            TasksListDAO.update(tl1);
            TasksListDAO.updateAll(tasksListList);
*/
/*
            TasksList tl1=new TasksList(5,"Стать директором предприятия",2);
            TasksList tl2=new TasksList(6,"Научиться водить машину",2);
            TasksList tl3=new TasksList(7,"Ежегодные задачи",1);
            tasksListList.add(tl2);
            tasksListList.add(tl3);

            TasksListDAO.insertOne(tl1);
            TasksListDAO.insertAll(tasksListList);
*/
/*
            Task t1=TaskDAO.getById(2);
            Task t2=TaskDAO.getById(3);
            Task t3=TaskDAO.getById(4);

            t1.setNote("956-87-46-85");
            t1.setPriority((short)1);
            t1.setState((short)2);
            t2.setDateBegin(LocalDateTime.parse("2017-10-04 18:00:00", XMLDateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            t2.setDateEnd(LocalDateTime.parse("2017-10-04 19:20:00", XMLDateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            t3.setName("Купить керамическую плитку");

            Task t1=new Task(1,"Проверить уроки","математика, русский, история",null,null,(short)2,(short)1,1);
            Task t2=new Task(9,"Забрать из ремонта машину",null,LocalDateTime.parse("2017-10-04 00:00:00", XMLDateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),null,(short)1,(short)1,2);
            Task t3=new Task(10,"Написать заявление в ЖЭУ",null,null,LocalDateTime.parse("2017-10-04 00:00:00", XMLDateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),(short)2,(short)1,4);

            List<Task> tasks=new ArrayList<>();
            tasks.add(t2);
            tasks.add(t3);

//            TaskDAO.insertOne(t1);
            TaskDAO.insertAll(tasks);
*/
//        } catch (UserDAO.UserDAOException e) {
 //           e.printStackTrace();
  //      } catch (TasksListDAO.TasksListDAOException e) {
//            e.printStackTrace();
//        } catch (TaskDAO.TaskDAOException e) {
//            e.printStackTrace();
//        }
    }

    public static void loadDBtoXML() {
        Users users = new Users();
        TasksLists taskslists = new TasksLists();
        Tasks tasks = new Tasks();
        try {
            users.setUsers(UserDAO.getAll());
            taskslists.setTasksLists(TasksListDAO.getAll(false));
            tasks.setTasks(TaskDAO.getAll());
//            u1.setTasksLists(TasksListDAO.getTasksListsSetByUser(1,false));
            JAXB jaxb = new JAXB();
            jaxb.serialize(users, "users.xml");
            jaxb.serialize(taskslists, "taskslists.xml");
            jaxb.serialize(tasks, "tasks.xml");
        } catch (UserDAO.UserDAOException e) {
            e.printStackTrace();
        } catch (TasksListDAO.TasksListDAOException e) {
            e.printStackTrace();
        } catch (TaskDAO.TaskDAOException e) {
            e.printStackTrace();
        }

    }

    public static void loadXMLtoDB() {
        try {
            Users users = (Users) JAXB.unmarshal(Users.class, new File("users.xml"));
            List<User> userList = users.getUsers();
            UserDAO.insertAll(userList);

            TasksLists tasksLists = (TasksLists) JAXB.unmarshal(TasksLists.class, new File("taskslists.xml"));
            List<TasksList> tasksListList = tasksLists.getTasksLists();
            TasksListDAO.insertAll(tasksListList);

            Tasks tasks = (Tasks) JAXB.unmarshal(Tasks.class, new File("tasks.xml"));
            List<Task> taskList = tasks.getTasks();
            TaskDAO.insertAll(taskList);

     } catch (UserDAO.UserDAOException e) {
            e.printStackTrace();
        } catch (TasksListDAO.TasksListDAOException e) {
            e.printStackTrace();
        } catch (TaskDAO.TaskDAOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}