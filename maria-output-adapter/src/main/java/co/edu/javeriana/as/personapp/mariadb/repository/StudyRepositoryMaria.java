package co.edu.javeriana.as.personapp.mariadb.repository;

import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudyRepositoryMaria extends JpaRepository<EstudiosEntity, Integer> {
    public <Optional> EstudiosEntity findByProfesionAndPersona(Integer professionID, Integer personID);
}