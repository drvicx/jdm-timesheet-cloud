package ru.jdm.timesheet.cloud.service_timesheet.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import lombok.Data;
import java.time.LocalDate;


/**
 *=JPA ENTITY CLASS "Timedata"
 */
@Data
@Entity
@Table(name="TIMEDATA")
public class Timedata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    //--ManyToOne relation with User Entity/Table
    @ManyToOne
    @JoinColumn(name = "USERID", nullable = false)
    private User user;

    //--PROBLEM(fixed): field "userId" is not exposed at JSON-response when ManyToOne relation used
    @Column(name="USERID", insertable = false, updatable = false)
    private Long userId;
    //........... default: insertable = true,  updatable = true
    //........... Q: But how we can insert or update this field with this (false) flags?

    @Column(name="HOUR")
    private Integer hour;

    @Column(name="TYPE_")
    private String type;
    //private TimedataType type;

    @Column(name="DATE")
    private LocalDate date;

}
