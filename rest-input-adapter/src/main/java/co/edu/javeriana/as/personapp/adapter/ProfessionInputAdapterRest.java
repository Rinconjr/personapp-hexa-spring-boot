package co.edu.javeriana.as.personapp.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.ProfessionInputPort;
import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutPort;
import co.edu.javeriana.as.personapp.application.usecase.ProfessionUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.mapper.ProfessionMapperRest;
import co.edu.javeriana.as.personapp.model.request.ProfessionRequest;
import co.edu.javeriana.as.personapp.model.response.ProfessionResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class ProfessionInputAdapterRest {

	@Autowired
	@Qualifier("professionOutputAdapterMaria")
	private ProfessionOutPort professionOutputPortMaria;

	@Autowired
	@Qualifier("professionOutputAdapterMongo")
	private ProfessionOutPort professionOutputPortMongo;

	@Autowired
	private ProfessionMapperRest professionMapperRest;

	ProfessionInputPort professionInputPort;

	private String setProfessionOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			professionInputPort = new ProfessionUseCase(professionOutputPortMaria);
			return DatabaseOption.MARIA.toString();
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
            professionInputPort = new ProfessionUseCase(professionOutputPortMongo);
			return  DatabaseOption.MONGO.toString();
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

	public List<ProfessionResponse> historial(String database) {
		log.info("Into historial PersonaEntity in Input Adapter");
		try {
			if(setProfessionOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return professionInputPort.findAll().stream().map(professionMapperRest::fromDomainToAdapterRestMaria)
						.collect(Collectors.toList());
			}else {
				return professionInputPort.findAll().stream().map(professionMapperRest::fromDomainToAdapterRestMongo)
						.collect(Collectors.toList());
			}
			
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ArrayList<ProfessionResponse>();
		}
	}

	// TODO: Falta implementar el if else para MariaDB y MongoDB
	public ProfessionResponse crearProfesion(ProfessionRequest request) {
		try {
			setProfessionOutputPortInjection(request.getDatabase());
			Profession profession = professionInputPort.create(professionMapperRest.fromAdapterToDomain(request));
			return professionMapperRest.fromDomainToAdapterRestMaria(profession);
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			//return new PersonaResponse("", "", "", "", "", "", "");
		}
		return null;
	}

	// TODO: Falta implementar el if else para MariaDB y MongoDB
	public ProfessionResponse buscarProfession(String database, Integer idInteger) {
		try {
			if (database.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			setProfessionOutputPortInjection(database);
			Profession profession = professionInputPort.findOne(idInteger);
			return professionMapperRest.fromDomainToAdapterRestMaria(profession);
			}else {
				setProfessionOutputPortInjection(database);
                Profession profession = professionInputPort.findOne(idInteger);
				return professionMapperRest.fromDomainToAdapterRestMongo(profession);
			}
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
		} catch (NoExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// TODO: Falta implementar el if else para MariaDB y MongoDB
	public ProfessionResponse eliminarProfession(String database, Integer idInteger) {
		try {
			setProfessionOutputPortInjection(database);
			Boolean eliminado = professionInputPort.drop(idInteger);
			return new ProfessionResponse("", "", "",  "", eliminado.toString());
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// TODO: Falta implementar el if else para MariaDB y MongoDB
	public ProfessionResponse actualizarProfesion(ProfessionRequest request) {
		try {
			setProfessionOutputPortInjection(request.getDatabase());
            Profession profession = professionInputPort.edit(Integer.parseInt(request.getIdentification()),professionMapperRest.fromAdapterToDomain(request));
			return professionMapperRest.fromDomainToAdapterRestMaria(profession);
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}