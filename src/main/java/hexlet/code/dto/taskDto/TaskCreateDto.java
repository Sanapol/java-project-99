package hexlet.code.dto.taskDto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskCreateDto {
    private int index;
    private long assigneeId;
    @NotBlank
    private String title;
    private String content;
    @NotBlank
    private String status;
}
