package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.entity.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Integer>, JpaSpecificationExecutor<Status> {

	Page<Status> findAll(Pageable pageable);

	Status findByStatus(String status);

}
