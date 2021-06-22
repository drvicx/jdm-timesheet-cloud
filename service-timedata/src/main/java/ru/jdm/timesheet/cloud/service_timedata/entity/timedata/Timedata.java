package ru.jdm.timesheet.cloud.service_timedata.entity.timedata;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/**
 *=TIMEDATA ENTITY
 */
@NoArgsConstructor
@Data
@Entity
@Table(name="TIMEDATA")
public class Timedata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="USERID")
    private Long userId;

    @Column(name="HOUR")
    private Integer hour;

    @Column(name="TYPE_")
    private String type;
    //private TimedataType type;

    @Column(name="DATE")
    private LocalDate date;
    //private String date;

}
