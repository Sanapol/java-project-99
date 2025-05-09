package hexlet.code.dto.taskStatusDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusCreateDto {
    @NotBlank
    private String name;
    @NotBlank
    private String slug;
}
