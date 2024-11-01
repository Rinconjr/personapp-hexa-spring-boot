package co.edu.javeriana.as.personapp.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.in.ProfessionInputPort;
import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.application.usecase.ProfessionUseCase;
import co.edu.javeriana.as.personapp.application.usecase.StudyUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.mapper.StudyMapperRest;
import co.edu.javeriana.as.personapp.model.request.StudyRequest;
import co.edu.javeriana.as.personapp.model.response.StudyResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class StudyInputAdapterRest {

    // MariaDB Adapters
    @Autowired
    @Qualifier("studyOutputAdapterMaria")
    private StudyOutputPort studyOutputPortMaria;

    @Autowired
    @Qualifier("personOutputAdapterMaria")
    private PersonOutputPort personOutputPortMaria;

    @Autowired
    @Qualifier("professionOutputAdapterMaria")
    private ProfessionOutPort professionOutputPortMaria;

    // MongoDB Adapters
    @Autowired
    @Qualifier("studyOutputAdapterMongo")
    private StudyOutputPort studyOutputPortMongo;

    @Autowired
    @Qualifier("personOutputAdapterMongo")
    private PersonOutputPort personOutputPortMongo;

    @Autowired
    @Qualifier("professionOutputAdapterMongo")
    private ProfessionOutPort professionOutputPortMongo;

    // Mapper
    @Autowired
    private StudyMapperRest studyMapperRest;

    // UseCase
    private StudyInputPort studyInputPort;
    private PersonInputPort personInputPort;
    private ProfessionInputPort professionInputPort;

    private String setStudyOutputPortInjection(String dbOption) throws InvalidOptionException {
        if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {

            // Assign the correct output port to the input port
            studyInputPort = new StudyUseCase(studyOutputPortMaria);
            personInputPort = new PersonUseCase(personOutputPortMaria);
            professionInputPort = new ProfessionUseCase(professionOutputPortMaria);

            return DatabaseOption.MARIA.toString();

        } else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {

            // Assign the correct output port to the input port
            studyInputPort = new StudyUseCase(studyOutputPortMongo);
            personInputPort = new PersonUseCase(personOutputPortMongo);
            professionInputPort = new ProfessionUseCase(professionOutputPortMongo);

            return  DatabaseOption.MONGO.toString();
            
        } else {
            throw new InvalidOptionException("Invalid database option: " + dbOption);
        }
    }

    public List<StudyResponse> historial(String database) {
        log.info("Into historial StudyEntity in Input Adapter");
        try {
            if(setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
                return studyInputPort.findAll().stream().map(studyMapperRest::fromDomainToAdapterRestMaria)
                        .collect(Collectors.toList());
            }else {
                return studyInputPort.findAll().stream().map(studyMapperRest::fromDomainToAdapterRestMongo)
                        .collect(Collectors.toList());
            }
        } catch (InvalidOptionException e) {
            log.error(e.getMessage());
            return new ArrayList<StudyResponse>();
        }
    }

    public StudyResponse crearEstudio(StudyRequest request) {
        log.info("Into crearEstudio StudyEntity in Input Adapter");
        

        return null;
    }
    
}
