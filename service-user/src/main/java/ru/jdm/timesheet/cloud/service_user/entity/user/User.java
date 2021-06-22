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

    @Column(name="PERSONALNUMBER")
    private Long personalNumber;

    @Column(name="FIRSTNAME")
    private String firstName;

    @Column(name="LASTNAME")
    private String lastName;

    @Column(name="MIDLENAME")
    private String middleName;

    @Column(name="LOGIN")
    private String login;

    @Column(name="POSITION_E")
    private String positionEng;

    @Column(name="POSITION_R")
    private String positionRus;

    @Column(name="ACCESSLEVEL")
    private String accessLevel;


    //--define noArg constructors
    // *default constructor
    public User() {
    }

    // *noArg constructors needed for Hibernate
    public User(Long userId, Long personalNumber, String firstName, String lastName, String middleName, String login, String positionEng, String positionRus, String accessLevel) {
        this.userId = userId;
        this.personalNumber = personalNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.login = login;
        this.positionEng = positionEng;
        this.positionRus = positionRus;
        this.accessLevel = accessLevel;
    }

    //--Getters/Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(Long personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPositionEng() {
        return positionEng;
    }

    public void setPositionEng(String positionEng) {
        this.positionEng = positionEng;
    }

    public String getPositionRus() {
        return positionRus;
    }

    public void setPositionRus(String positionRus) {
        this.positionRus = positionRus;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    //--define tostring

    @Override
    public String toString() {
        return "User {" +
                "userId=" + userId +
                ", personalNumber=" + personalNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", login='" + login + '\'' +
                ", positionEng='" + positionEng + '\'' +
                ", positionRus='" + positionRus + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                '}';
    }

}
