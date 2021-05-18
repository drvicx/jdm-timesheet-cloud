package main.java.ru.jdm.timesheet.cloud.service_sample;

import ru.jdm.timesheet.cloud.IdGenerator;
import main.java.ru.jdm.timesheet.cloud.message_protocol.PingMO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PingResource {
    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody PingMO getAll() {
        return new PingMO(IdGenerator.uuid(), true, ServiceSampleApplication.APPLICATION_ID);
    }
}
