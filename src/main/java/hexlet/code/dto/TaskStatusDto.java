package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskStatusDto {
    private long id;
    private String name;
    private String slug;
    private LocalDate createdAt;
}
