package com.company.xmlroutine;

import com.company.pojo.TasksList;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "TasksLists")
public class TasksLists {
    private List<TasksList> tasks=new ArrayList<>();

    public TasksLists() {
    }

    public List<TasksList> getTasksLists() {
        return tasks;
    }

    public void setTasksLists(List<TasksList> tasks) {
        this.tasks = tasks;
    }
}
