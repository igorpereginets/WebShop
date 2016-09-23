package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, Integer>, JpaSpecificationExecutor<City> {

	@Query("SELECT c FROM City c LEFT JOIN FETCH c.country")
	Iterable<City> findAllWithCountries();
	
	@Query("SELECT c FROM City c LEFT JOIN FETCH c.country WHERE c.id = ?1")
	City findOneWithCountry(int id);

	@EntityGraph("city.country")
	Page<City> findAll(Specification<City> spec, Pageable pageable);

	City findByName(String name);
}
