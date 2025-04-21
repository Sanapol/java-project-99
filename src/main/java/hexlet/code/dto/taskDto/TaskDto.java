package hexlet.code.dto.taskDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class TaskDto {
    private long id;
    private int index;
    private LocalDate createdAt;
    private long assigneeId;
    private String title;
    private String content;
    private String status;
}
