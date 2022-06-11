package hu.progmasters.struct.exceptionhandling;

public class AlreadyRegisteredException extends RuntimeException {
    private final Integer trainingClassId;
    private final Integer studentId;

    public AlreadyRegisteredException(Integer trainingClassId, Integer studentId) {
        super(trainingClassId + "," + studentId);
        this.trainingClassId = trainingClassId;
        this.studentId = studentId;
    }

    public Integer getTrainingClassId() {
        return trainingClassId;
    }

    public Integer getStudentId() {
        return studentId;
    }
}
