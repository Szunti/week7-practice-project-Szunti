package hu.progmasters.struct.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Syllabus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "syllabus")
    private List<TrainingClass> trainingClasses;

    @OneToMany(mappedBy = "syllabus")
    private List<Module> modules;
}
