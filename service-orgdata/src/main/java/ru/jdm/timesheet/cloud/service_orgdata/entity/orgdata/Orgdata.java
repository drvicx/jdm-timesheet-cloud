package ru.jdm.timesheet.cloud.service_orgdata.entity.orgdata;

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
 *=ORGDATA ENTITY
 */
@NoArgsConstructor
@Data
@Entity
@Table(name="ORGDATA")
public class Orgdata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="ORGNAME")
    private String orgName;

    @Column(name="DEPNAME")
    private String depName;

    @Column(name="DEPBOSS")
    private String depBoss;

    @Column(name="RESPONDER")
    private String responder;

    @Column(name="BEGINDATE")
    private LocalDate dateBegin;

    @Column(name="ENDDATE")
    private LocalDate dateEnd;

}
