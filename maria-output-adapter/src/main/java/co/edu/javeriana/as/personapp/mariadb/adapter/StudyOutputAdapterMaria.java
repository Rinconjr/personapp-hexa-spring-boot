package co.edu.javeriana.as.personapp.mariadb.adapter;

import java.util.List;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntity;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntityPK;
import co.edu.javeriana.as.personapp.mariadb.mapper.EstudiosMapperMaria;
//import co.edu.javeriana.as.personapp.mariadb.repository.StudyRepositoryMaria;
import lombok.extern.slf4j.Slf4j;

/*
@Slf4j
@Adapter("studyOutputAdapterMaria")
@Transactional
public class StudyOutputAdapterMaria implements StudyOutputPort {

	@Autowired
	private StudyRepositoryMaria studyRepositoryMaria;

	@Autowired
	private EstudiosMapperMaria personaMapperMaria;

	@Override
	public Study save(Study study) {
		log.debug("Into save on Adapter MariaDB");
        EstudiosEntity persistedStudio = studyRepositoryMaria.save(personaMapperMaria.fromDomainToAdapter(study));
		return personaMapperMaria.fromAdapterToDomain(persistedStudio);
	}

	@Override
	public Boolean delete(Integer professionID, Integer personID) {
		log.debug("Into delete on Adapter MariaDB");
        studyRepositoryMaria.deleteById(professionID, personID);
		return studyRepositoryMaria.findById(professionID, personID).isEmpty();
	}

	@Override
	public List<Study> find() {
		log.debug("Into find on Adapter MariaDB");
		return studyRepositoryMaria.findAll().stream().map(personaMapperMaria::fromAdapterToDomain)
				.collect(Collectors.toList());
	}

	@Override
	public Study findById(Integer professionID, Integer personID) {
		log.debug("Into findById on Adapter MariaDB");
		if (studyRepositoryMaria.findById(professionID, personID).isEmpty()) {
			return null;
		} else {
			return personaMapperMaria.fromAdapterToDomain(studyRepositoryMaria.findById(professionID, personID).get());
		}
	}

}
    */
