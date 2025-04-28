package hexlet.code.dto.taskDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class TaskDto {
    private long id;
    private int index;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    private long assignee_id;
    private String title;
    private String content;
    private String status;
    private List<Long> taskLabelIds;
}
