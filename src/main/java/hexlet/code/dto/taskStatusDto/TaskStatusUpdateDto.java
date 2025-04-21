package hexlet.code.dto.taskStatusDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusUpdateDto {
    @NotBlank
    private String name;
}
