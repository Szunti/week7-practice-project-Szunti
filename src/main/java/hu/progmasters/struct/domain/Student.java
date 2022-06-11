package hu.progmasters.struct.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String githubUser;

    private String comment;

    @OneToMany(mappedBy = "student")
    private List<Registration> registrations;
}
