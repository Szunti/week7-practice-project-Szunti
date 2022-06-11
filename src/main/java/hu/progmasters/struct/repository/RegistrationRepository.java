package hu.progmasters.struct.repository;

import hu.progmasters.struct.domain.Registration;
import hu.progmasters.struct.domain.Student;
import hu.progmasters.struct.domain.TrainingClass;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class RegistrationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Registration save(Registration toSave) {
        entityManager.persist(toSave);
        return toSave;
    }

    public Optional<Registration> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Registration.class, id));
    }

    public List<Registration> findAll() {
        return entityManager.createQuery("SELECT r FROM Registration r", Registration.class).getResultList();
    }

    public boolean hasRegistration(Student student, TrainingClass trainingClass) {
        return !entityManager.createQuery("SELECT TRUE FROM Registration r " +
                        "WHERE r.student = :studentParam AND r.trainingClass = :trainingClassParam",
                Boolean.class)
                .setParameter("studentParam", student)
                .setParameter("trainingClassParam", trainingClass)
                .getResultList().isEmpty();
    }
}
