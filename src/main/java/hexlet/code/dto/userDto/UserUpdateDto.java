package hexlet.code.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class UserUpdateDto {
    @Email
    private JsonNullable<String> email;

    @NotBlank
    private JsonNullable<String> password;

    private JsonNullable<String> firstName;

    private JsonNullable<String> lastName;
}
