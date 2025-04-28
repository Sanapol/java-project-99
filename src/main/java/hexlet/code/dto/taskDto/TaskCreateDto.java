package hexlet.code.dto.taskDto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TaskCreateDto {
    private int index;
    private long assignee_id;
    @NotBlank
    private String title;
    private String content;
    @NotBlank
    private String status;
    private List<Long> taskLabelIds;
}
