package hu.progmasters.struct.dto;

import hu.progmasters.struct.domain.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
public class RegistrationCommand {
    private Integer trainingClassId;

    private Integer studentId;
}