package ru.jdm.timesheet.cloud.service_user.entity.user;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

//import lombok.Data;
//import lombok.NoArgsConstructor;


/**
 *=USER ENTITY
 */
//@NoArgsConstructor
//@Data
@Entity
@Table(name="USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long userId;

    @Column(name="LOGIN")
    private String login;

    @Column(name="NAME")
    private String name;

    @Column(name="SURNAME")
    private String surname;

    // define noArg constructors
    // *default constructor
    public User() {
    }

    // *noArg constructors needed for Hibernate
    public User(Long userId, String login, String name, String surname) {
        this.login = login;
        this.name = name;
        this.surname = surname;
    }

    //--Getters/Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    // define tostring
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
