package hexlet.code.dto.taskDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskUpdateDto {
    private int index;
    private long assigneeId;
    private String title;
    private String content;
    private String status;
}
