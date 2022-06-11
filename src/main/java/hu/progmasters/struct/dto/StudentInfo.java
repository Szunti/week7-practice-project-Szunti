package hu.progmasters.struct.dto;

import hu.progmasters.struct.domain.Registration;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentInfo {
    private Integer id;

    private String name;

    private String email;

    private String githubUser;

    private String comment;
}
