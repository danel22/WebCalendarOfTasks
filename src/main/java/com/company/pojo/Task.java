package com.company.pojo;

import com.company.xmlroutine.XMLDateTimeFormatter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

/**Класс Задача
 *
 */
@XmlRootElement(name="Task")
public class Task {
    private int id=0;
    private String name="";
    private String note="";
    private LocalDateTime dateBegin=null;
    private LocalDateTime dateEnd=null;
    private short priority=4;
    private short state=1;
    private TasksList tasksList=null;
    private int tasksListId=0;

    public Task() {
    }

    /**Создание новой задачи
     * @param id - id Задачи
     * @param name - название
     * @param note - заметка
     * @param dateBegin - дата и время начала выполнения
     * @param dateEnd - дата и время окончания выполнения
     * @param priority - приоритет задачи
     * @param state - состояние задачи
     * @param tasksListId - ссылка на Список задач
     */
    public Task(int id, String name, String note, LocalDateTime dateBegin, LocalDateTime dateEnd, short priority, short state, int tasksListId) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.priority = priority;
        this.state = state;
        this.tasksListId = tasksListId;
    }

    /**Функция выводит на экран информацию о Задаче
     * @return
     */
    @Override
    public String toString() {
        return "id="+id+"\n"+
            "name="+name+"\n"+
            "note="+note+"\n"+
            "dateBegin="+ dateBegin+"\n"+
            "dateEnd="+ dateEnd+"\n"+
            "priority="+ priority+"\n"+
            "state="+ state+"\n"+
            "tasksListId="+ tasksListId+"\n"+
            "------------------------------";
    }

    @XmlAttribute
    /**Функция возвращает id Задачи
     * @return
     */
    public int getId() {
        return id;
    }

    /**Функция устанавливает id Задачи
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**Функция возвращает название Задачи
     * @return - название задач
     */
    public String getName() {
        return name;
    }

    /**Функция устанавливает название Задачи
     * @param name название Задачи
     */
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    /**Функция возвращает заметку к Задаче
     * @return
     */
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    @XmlAttribute
    @XmlJavaTypeAdapter(XMLDateTimeFormatter.class)
    /**Функция возвращает дату и время начала выполнения Задачи
     * @return - дата и время начала выполнения задачи
     */
    public LocalDateTime getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDateTime dateBegin) {
        this.dateBegin=dateBegin;
    }
    @XmlAttribute
    @XmlJavaTypeAdapter(XMLDateTimeFormatter.class)
    /**Функция возвращает дату и время окончания выполнения Задачи
     * @return
     */
    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    /**Функция устанавливает дату и время окончания выполнения Задачи
     * @param dateEnd
     */

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }
    @XmlAttribute
    /**Функция возвращает приоритет Задачи
     * @return
     */
    public short getPriority() {
        return priority;
    }

    /**Функция устанавливает приоритет Задачи
     * @param priority
     */

    public void setPriority(short priority) {
        this.priority = priority;
    }
    @XmlAttribute
    /**Функция возвращает состояние Задачи
     * @return
     */
    public short getState() {
        return state;
    }

    /**Функция устанавливает состояние Задачи
     * @param state
     */

    public void setState(short state) {
        this.state = state;
    }

    /**Функция возвращает Список задач, в который входит данная Задача
     * @return TasksList - список задач
     */
    public TasksList getTasksList() {
        return tasksList;
    }

    /**Функция устанавливает Список задач, в который входит данная Задача
     * @param tasksList11
     */
    public void setTasksList(TasksList tasksList11) {
        this.tasksList = tasksList;
    }
    @XmlAttribute
    /**Функция возвращает идентификатор Списка задач, в который входит данная Задача
     * @return - идентификатор Списка задач
     */
    public int getTasksListId() {
        return tasksListId;
    }

    /** Функция устанавливает идентификатор Списка задач, в который входит данная Задача
     * @param tasksListId
     */
    public void setTasksListId(int tasksListId) {
        this.tasksListId = tasksListId;
    }

}
