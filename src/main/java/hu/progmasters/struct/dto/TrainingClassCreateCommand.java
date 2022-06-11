package hu.progmasters.struct.dto;

import hu.progmasters.struct.domain.Registration;
import hu.progmasters.struct.domain.Syllabus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class TrainingClassCreateCommand {
    private String name;
    private Syllabus syllabus;
}
