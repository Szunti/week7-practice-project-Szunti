package hu.progmasters.struct.dto;

import hu.progmasters.struct.domain.Syllabus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrainingClassInfo {
    private Integer id;
    private String name;
//    private SyllabusInfo syllabus;
}
