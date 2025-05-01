package hexlet.code.dto.taskStatusDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class TaskStatusUpdateDto {
    @NotBlank
    private JsonNullable<String> name;

    private JsonNullable<String> slug;
}
