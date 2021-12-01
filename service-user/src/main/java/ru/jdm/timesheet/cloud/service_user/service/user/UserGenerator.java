package ru.jdm.timesheet.cloud.service_user.service.user;

import ru.jdm.timesheet.cloud.service_user.entity.user.User;

import org.springframework.stereotype.Service;


/**
 *=PREDEFINED USER GENERATOR | ГЕНЕРАТОР ПРЕДУСТАНОВЛЕННЫХ ДАННЫХ
 */
@Service
public class UserGenerator {

    //--return empty User Object
    public User getDefaultUserObj() {
        //--создаем пустой Объект
        User userData = new User();
        //--инициализируем поля объекта дефолтными значениями
        //  *id объекта/записи явно не заполняем: userData.setId(null) - не нужно
        userData.setUserId(null);
        userData.setPersonalNumber(null);
        userData.setLogin("null_nn");
        userData.setFirstName("Нулл");
        userData.setLastName("Нуллов");
        userData.setMiddleName("Нуллович");
        userData.setPositionEng("Workless");
        userData.setPositionRus("Безработный");
        userData.setAccessLevel("none");
        userData.setHireDate(null);
        userData.setFireDate(null);
        //(!)при установке полей объекта null значения явно указывать не нужно
        //--возвращаем созданный объект с инициализированными полями
        return userData;
    }

}
