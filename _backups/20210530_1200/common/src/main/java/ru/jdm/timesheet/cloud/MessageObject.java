package ru.jdm.timesheet.cloud;

import java.io.Serializable;
import com.google.gson.GsonBuilder;


public interface MessageObject extends Serializable {

    default String toJson() {
        return new GsonBuilder().create().toJson(this);
    }
}
