package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>, JpaSpecificationExecutor<Address> {

	Page<Address> findAll(Pageable pageable);

	Address findByStreet(String street);

}
