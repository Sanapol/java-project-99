package hexlet.code.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class UserUpdateDto {
    @Email
    @Column(unique = true)
    private JsonNullable<String> email;

    @NotBlank
    private JsonNullable<String> password;
}
