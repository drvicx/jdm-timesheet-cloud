package main.java.ru.jdm.timesheet.cloud.message_protocol;

import ru.jdm.timesheet.cloud.MessageObject;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PingMO implements MessageObject {
    private String id;
    private boolean success;
    private String applicationId;
}
