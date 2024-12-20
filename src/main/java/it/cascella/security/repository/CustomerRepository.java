package it.cascella.security.repository;

import it.cascella.security.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository  extends CrudRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

}
