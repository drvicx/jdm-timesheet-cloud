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

    //--this field mapping is not required when ManyToOne relation is used on that field
    //@Column(name="USERID")
    //private Long userId;

    @Column(name="HOUR")
    private Integer hour;

    @Column(name="TYPE_")
    private String type;
    //private TimedataType type;

    @Column(name="DATE")
    private LocalDate date;

}
