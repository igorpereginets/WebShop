package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import ua.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Integer>, JpaSpecificationExecutor<Country> {

	Country findByName(String name);

	Page<Country> findAll(Pageable pageable);

}
