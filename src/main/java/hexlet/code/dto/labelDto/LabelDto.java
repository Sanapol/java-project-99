package hexlet.code.dto.labelDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class LabelDto {
    private long id;
    private String name;
    private LocalDate createdAt;
}
