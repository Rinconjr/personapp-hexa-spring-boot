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

	public ProfessionResponse crearProfesion(ProfessionRequest request) {
		log.info("Into crearProfesion ProfessionEntity in Input Adapter");
		try {
			setProfessionOutputPortInjection(request.getDatabase());
			Profession profession = professionInputPort.create(professionMapperRest.fromAdapterToDomain(request));
	
			if (request.getDatabase().equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
				return professionMapperRest.fromDomainToAdapterRestMaria(profession);
			} else {
				return professionMapperRest.fromDomainToAdapterRestMongo(profession);
			}
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ProfessionResponse(request.getIdentification(), request.getName(), request.getDescription(), request.getDatabase(), "Error: Invalid Database Option");
		}
	}
	

	public ProfessionResponse buscarProfession(String database, Integer idInteger) {
		log.info("Into buscarProfession ProfessionEntity in Input Adapter");
		try {
			setProfessionOutputPortInjection(database);
			Profession profession = professionInputPort.findOne(idInteger);
	
			if (database.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
				return professionMapperRest.fromDomainToAdapterRestMaria(profession);
			} else {
				return professionMapperRest.fromDomainToAdapterRestMongo(profession);
			}
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ProfessionResponse(idInteger.toString(), "", "", database, "Error: Invalid Database Option");
		} catch (NoExistException e) {
			log.warn(e.getMessage());
			return new ProfessionResponse(idInteger.toString(), "", "", database, "Error: Profession not found");
		}
	}
	

	public ProfessionResponse eliminarProfession(String database, Integer idInteger) {
		log.info("Into eliminarProfession ProfessionEntity in Input Adapter");
		try {
			setProfessionOutputPortInjection(database);
			Boolean eliminado = professionInputPort.drop(idInteger);
	
			return new ProfessionResponse(idInteger.toString(), "", "", database, eliminado ? "Deleted" : "Failed to Delete");
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ProfessionResponse(idInteger.toString(), "", "", database, "Error: Invalid Database Option");
		} catch (NoExistException e) {
			log.warn(e.getMessage());
			return new ProfessionResponse(idInteger.toString(), "", "", database, "Error: Profession not found");
		}
	}
	

	public ProfessionResponse actualizarProfesion(ProfessionRequest request) {
		log.info("Into actualizarProfesion ProfessionEntity in Input Adapter");
		try {
			setProfessionOutputPortInjection(request.getDatabase());
			Profession profession = professionInputPort.edit(Integer.parseInt(request.getIdentification()), professionMapperRest.fromAdapterToDomain(request));
	
			if (request.getDatabase().equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
				return professionMapperRest.fromDomainToAdapterRestMaria(profession);
			} else {
				return professionMapperRest.fromDomainToAdapterRestMongo(profession);
			}
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ProfessionResponse(request.getIdentification(), request.getName(), request.getDescription(), request.getDatabase(), "Error: Invalid Database Option");
		} catch (NoExistException e) {
			log.warn(e.getMessage());
			return new ProfessionResponse(request.getIdentification(), request.getName(), request.getDescription(), request.getDatabase(), "Error: Update Failed - Profession not found");
		}
	}
	


}
