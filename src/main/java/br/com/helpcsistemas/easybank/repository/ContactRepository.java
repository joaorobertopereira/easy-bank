package br.com.helpcsistemas.easybank.repository;

import br.com.helpcsistemas.easybank.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	
}
