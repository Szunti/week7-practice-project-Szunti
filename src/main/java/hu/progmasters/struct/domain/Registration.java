package hu.progmasters.struct.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private TrainingClass trainingClass;

    @ManyToOne(optional = false)
    private Student student;

    @Enumerated(EnumType.STRING)
    private Status status;
}
