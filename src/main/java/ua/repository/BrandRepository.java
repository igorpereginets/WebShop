package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.entity.Brand;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Integer>, JpaSpecificationExecutor<Brand> {

	Page<Brand> findAll(Pageable pageable);

	Brand findByName(String name);

}
