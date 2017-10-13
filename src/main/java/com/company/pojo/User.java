package com.company.pojo;

import com.company.xmlroutine.XMLDateFormatter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name="User")
@XmlType(propOrder = {"id","dateOfRegistration","admin","lock","firstName","lastName","email","login","password"})
public class User {
    private int id=0;
    private String firstName="";
    private String lastName="";
    private String email="";
    private String login="";
    private String password="";
    private LocalDate dateOfRegistration;
    private boolean isAdmin=false;
    private boolean isLock=false;
//    private HashSet<TasksList> tasksLists=new HashSet<TasksList>();

    @Override
    public String toString() {
        String str2="";
        String str= "id="+id+"\n"+
                "firstName="+firstName+"\n"+
                "lastName="+lastName+"\n"+
                "email="+ email+"\n"+
                "login="+ login+"\n"+
                "password="+ password+"\n"+
                "dateOfRegistration="+ dateOfRegistration+"\n"+
                "isAdmin="+ isAdmin+"\n"+
                "isLock="+ isLock+"\n"+
                "Списки задач:"+"\n";
/*        int i=0;
        for (TasksList tasksList:this.tasksLists) {
            i++;
            str2 = str2 + i + "\n" + tasksLists.toString();
        }*/
        return str+str2+"\n"+"----------------------------";
    }

    public User(int id,
                String firstName,
                String lastName,
                String email,
                String login,
                String password,
                LocalDate dateOfRegistration,
                boolean isAdmin,
                boolean isLock) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.dateOfRegistration = dateOfRegistration;
        this.isAdmin = isAdmin;
        this.isLock = isLock;
    }
    public User(){}
    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @XmlElement
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    @XmlElement
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @XmlAttribute
    @XmlJavaTypeAdapter(XMLDateFormatter.class)
    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate date) {
        this.dateOfRegistration =  date;
    }
    @XmlAttribute
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }
    @XmlAttribute
    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

/*    public HashSet<TasksList> getTasksLists() {
        return tasksLists;
    }

    public void setTasksLists(HashSet<TasksList> tasksLists) {
        this.tasksLists = tasksLists;
    }*/
}
