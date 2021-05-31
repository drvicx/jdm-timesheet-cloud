package ru.jdm.timesheet.cloud.service_user;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *=USER DAO
 */
@NoArgsConstructor
@Data
@Entity
@Table(name="USER")
public class UserDAO {
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

    //--метод для диагностики состояния Объектов;
    @Override
    public String toString() {
        return "User{"  + "userId=" + userId
                + ", login='" + login
                + '\'' + ", name='" + name
                + '\'' + ", surname='" + surname + '\'' + '}';
    }
}
