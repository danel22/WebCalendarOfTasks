package com.company.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;

@XmlRootElement(name="TasksList")
public class TasksList {
    private int id=0;
    private String name="";
    private HashSet<Task> tasks=new HashSet<>();
    private User user=null;
    private int userId=0;

    public TasksList(int id, String name, int userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }
    public TasksList(String name, User user) {
        this.name = name;
        this.user = user;
    }
    public TasksList() {
    }

    @Override
    public String toString() {
        String str="";
        int i=0;
        for (Task task:tasks){
            i++;
            str=str+"задача "+i+" списка "+ name+"\n"+task.toString()+"\n";
        }
        return "Список задач: id="+id+"\n"+
                "name="+name+"\n"+
                "userId="+ userId+"\n"+
                "amount of tasks="+ tasks.size()+"\n"+"------------------------------\n"+str;

    }
    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HashSet<Task> getTasks() {
        return tasks;
    }

    public void setTasks(HashSet<Task> tasks) {
        this.tasks = tasks;
    }
    @XmlAttribute
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public void removeTask(Task task){
        this.tasks.remove(task);

    }


}
