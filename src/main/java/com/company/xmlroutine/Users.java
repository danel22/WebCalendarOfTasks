package com.company.xmlroutine;

import com.company.pojo.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Users")
public class Users {

    private List<User> users=new ArrayList<>();

    public Users() {
     }
    @XmlElement(name = "User", type = User.class)
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
