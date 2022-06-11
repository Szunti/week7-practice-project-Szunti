package hu.progmasters.struct.dto;

import hu.progmasters.struct.domain.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationInfo {
    private Integer id;

    private TrainingClassInfo trainingClass;

    private StudentInfo student;

    private Status status;
}
