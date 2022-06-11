package hu.progmasters.struct.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
public class StudentCreateCommand {
    private String name;

    @Email
    private String email;

    private String githubUser;

    private String comment;
}
