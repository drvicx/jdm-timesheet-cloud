package ru.jdm.timesheet.cloud.service_timesheet.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


/**
 *=JPA ENTITY CLASS "User"
 */
//--Lombok @Data -- known bug with OneToMany relation
//@Data
@Getter
@Setter
@Entity
@Table(name="USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long userId;

    //--OneToMany relation with Timedata Entity/Table
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Timedata> timedata;

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

    @Column(name="HIRED")
    private LocalDate hireDate;

    @Column(name="FIRED")
    private LocalDate fireDate;

}
