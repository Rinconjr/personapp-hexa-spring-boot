package co.edu.javeriana.as.personapp.mongo.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoWriteException;

import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mongo.document.TelefonoDocument;
import co.edu.javeriana.as.personapp.mongo.mapper.TelefonoMapperMongo;
import co.edu.javeriana.as.personapp.mongo.repository.PhoneRepositoryMongo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter("phoneOutputAdapterMongo")
public class PhoneOutputAdapterMongo implements PhoneOutputPort {
	
	@Autowired
    private PhoneRepositoryMongo phoneRepositoryMongo;
	
	@Autowired
	private TelefonoMapperMongo phoneMapperMongo;
	
	@Override
	public Phone save(Phone phone) {
		log.debug("Into save on Adapter MongoDB");
		try {
            TelefonoDocument persistedPhone = phoneRepositoryMongo.save(phoneMapperMongo.fromDomainToAdapter(phone));
			return phoneMapperMongo.fromAdapterToDomain(persistedPhone);
		} catch (MongoWriteException e) {
			log.warn(e.getMessage());
			return phone;
		}		
	}

	@Override
	public Boolean delete(String identification) {
		log.debug("Into delete on Adapter MongoDB");
        phoneRepositoryMongo.deleteById(identification);
		return phoneRepositoryMongo.findById(identification).isEmpty();
	}

	@Override
	public List<Phone> find() {
		log.debug("Into find on Adapter MongoDB");
		return phoneRepositoryMongo.findAll().stream().map(phoneMapperMongo::fromAdapterToDomain)
				.collect(Collectors.toList());
	}

	@Override
	public Phone findById(String identification) {
		log.debug("Into findById on Adapter MongoDB");
		if (phoneRepositoryMongo.findById(identification).isEmpty()) {
			return null;
		} else {
			return phoneMapperMongo.fromAdapterToDomain(phoneRepositoryMongo.findById(identification).get());
		}
	}

}
