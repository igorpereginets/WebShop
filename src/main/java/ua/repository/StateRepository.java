package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.entity.State;

@Repository
public interface StateRepository extends CrudRepository<State, Integer>, JpaSpecificationExecutor<State> {

	Page<State> findAll(Pageable pageable);

	State findByName(String name);

}
