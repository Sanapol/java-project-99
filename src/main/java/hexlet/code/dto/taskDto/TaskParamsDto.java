package hexlet.code.dto.taskDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskParamsDto {
    private String titleCount;
    private Long assigneeId;
    private String status;
    private Long labelId;
}
