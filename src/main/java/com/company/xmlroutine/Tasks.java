package com.company.xmlroutine;

import com.company.pojo.Task;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Tasks")
public class Tasks {

    private List<Task> tasks=new ArrayList<>();

    public Tasks() {
    }
    @XmlElement(name = "Task", type = Task.class)
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
