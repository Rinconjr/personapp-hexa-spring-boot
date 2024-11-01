package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.model.request.StudyRequest;
import co.edu.javeriana.as.personapp.model.response.StudyResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper
public class StudyMapperRest {
    private static final Logger log = LoggerFactory.getLogger(StudyMapperRest.class);

    public StudyResponse fromDomainToAdapterRestMaria(Study study) {
        return fromDomainToAdapterRest(study, "MariaDB");
    }

    public StudyResponse fromDomainToAdapterRestMongo(Study study) {
        return fromDomainToAdapterRest(study, "MongoDB");
    }

    public StudyResponse fromDomainToAdapterRest(Study study, String database) {
        return new StudyResponse(
                study.getProfession().getIdentification()+"",
                study.getPerson().getIdentification()+"",
                study.getGraduationDate(),
                study.getUniversityName(),
                database,
                "OK");
    }

    public Study fromAdapterToDomain(StudyRequest request, Profession profession, Person person) {
        log.info("Into fromAdapterToDomain");
        Study study = new Study();
        study.setProfession(profession);
        study.setPerson(person);
        study.setGraduationDate(request.getGraduationDate());
        study.setUniversityName(request.getUniversity());
        return study;
    }
    
}
