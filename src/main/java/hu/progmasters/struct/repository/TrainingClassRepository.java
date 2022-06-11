package hu.progmasters.struct.repository;

import hu.progmasters.struct.domain.TrainingClass;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TrainingClassRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TrainingClass save(TrainingClass toSave) {
        entityManager.persist(toSave);
        return toSave;
    }

    public Optional<TrainingClass> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(TrainingClass.class, id));
    }

    public List<TrainingClass> findAll() {
        return entityManager.createQuery("SELECT tc FROM TrainingClass tc").getResultList();
    }
}
