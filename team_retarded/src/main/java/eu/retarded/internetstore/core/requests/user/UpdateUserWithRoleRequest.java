package eu.retarded.internetstore.core.requests.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class UpdateUserWithRoleRequest {

    private final long id;

    @NotBlank(message = "Name must not be empty!")
    @Length(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private final String name;

    @NotBlank(message = "Surname must not be empty!")
    @Length(min = 2, max = 100, message = "Surname must be between 2 and 100 characters")
    private final String surname;

    @NotBlank(message = "Email must not be empty!")
    @Email(message = "Incorrect email format!")
    private final String email;

    @NotNull(message = "User must have at least one role")
    private final Set<Long> rolesId;
}
