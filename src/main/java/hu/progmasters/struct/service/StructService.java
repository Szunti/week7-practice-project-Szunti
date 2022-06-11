package hu.progmasters.struct.service;

import hu.progmasters.struct.domain.Registration;
import hu.progmasters.struct.domain.Status;
import hu.progmasters.struct.domain.Student;
import hu.progmasters.struct.domain.TrainingClass;
import hu.progmasters.struct.dto.*;
import hu.progmasters.struct.exceptionhandling.AlreadyRegisteredException;
import hu.progmasters.struct.repository.RegistrationRepository;
import hu.progmasters.struct.repository.StudentRepository;
import hu.progmasters.struct.repository.TrainingClassRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StructService {

    private StudentRepository studentRepository;
    private TrainingClassRepository trainingClassRepository;
    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public StructService(StudentRepository studentRepository, TrainingClassRepository trainingClassRepository, RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.trainingClassRepository = trainingClassRepository;
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    public TrainingClassInfo save(TrainingClassCreateCommand command) {
        TrainingClass toSave = modelMapper.map(command, TrainingClass.class);
        TrainingClass saved = trainingClassRepository.save(toSave);
        return modelMapper.map(saved, TrainingClassInfo.class);
    }

    public StudentInfo save(StudentCreateCommand command) {
        Student toSave = modelMapper.map(command, Student.class);
        Student saved = studentRepository.save(toSave);
        return modelMapper.map(saved, StudentInfo.class);
    }

    public RegistrationInfo registrate(RegistrationCommand command) {
        TrainingClass trainingClass = trainingClassRepository.findById(command.getTrainingClassId())
                .orElseThrow();
        Student student = studentRepository.findById(command.getStudentId()).orElseThrow();

        if (alreadyRegistrated(student, trainingClass)) {
            throw new AlreadyRegisteredException(trainingClass.getId(), student.getId());
        }

        Registration registration = new Registration();
        registration.setTrainingClass(trainingClass);
        registration.setStudent(student);

        student.getRegistrations().stream()
                .filter(reg -> reg.getStatus() == Status.ACTIVE)
                .forEach(reg -> reg.setStatus(Status.EXIT_IN_PROGRESS));

        registration.setStatus(Status.ACTIVE);

        Registration saved = registrationRepository.save(registration);
        return modelMapper.map(saved, RegistrationInfo.class);
    }

    private boolean alreadyRegistrated(Student student, TrainingClass trainingClass) {
        return registrationRepository.hasRegistration(student, trainingClass);
    }
}
