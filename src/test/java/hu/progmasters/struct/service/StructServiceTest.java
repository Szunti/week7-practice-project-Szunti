package hu.progmasters.struct.service;

import hu.progmasters.struct.dto.*;
import hu.progmasters.struct.exceptionhandling.AlreadyRegisteredException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StructServiceTest {

    @Autowired
    StructService service;

    @Test
    void testRegistrate() {
        StudentInfo studentInfo = createStudent();
        TrainingClassInfo trainingClassInfo = createMethod();

        RegistrationCommand registrationCommand = new RegistrationCommand();
        registrationCommand.setStudentId(studentInfo.getId());
        registrationCommand.setTrainingClassId(trainingClassInfo.getId());

        RegistrationInfo registrationInfo = null;
        try {
            registrationInfo = service.registrate(registrationCommand);
        } catch (Exception exception) {
            fail(exception);
        }

        assertNotNull(registrationInfo);
        assertNotNull(registrationInfo.getStudent());
        assertEquals(studentInfo.getId(), registrationInfo.getStudent().getId());
        assertEquals(trainingClassInfo.getId(), registrationInfo.getTrainingClass().getId());
    }

    @Test
    void testRegistrate_multi() {
        StudentInfo studentInfo = createStudent();

        TrainingClassInfo trainingClassInfo = createMethod();

        RegistrationCommand registrationCommand = new RegistrationCommand();
        registrationCommand.setStudentId(studentInfo.getId());
        registrationCommand.setTrainingClassId(trainingClassInfo.getId());

        RegistrationInfo regInfoFirst = service.registrate(registrationCommand);
        assertThrows(AlreadyRegisteredException.class, () -> service.registrate(registrationCommand));
    }

    private TrainingClassInfo createMethod() {
        TrainingClassCreateCommand trainingClassCreateCommand = new TrainingClassCreateCommand();
        trainingClassCreateCommand.setName("Java Programming");
        TrainingClassInfo trainingClassInfo = service.save(trainingClassCreateCommand);
        return trainingClassInfo;
    }

    private StudentInfo createStudent() {
        StudentCreateCommand studentCreateCommand = new StudentCreateCommand();
        studentCreateCommand.setName("Dani");

        StudentInfo studentInfo = service.save(studentCreateCommand);
        return studentInfo;
    }

}