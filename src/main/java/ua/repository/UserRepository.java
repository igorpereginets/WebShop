package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User> {

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.address WHERE u.id = ?1")
	User findOneWithAddress(int id);

	Page<User> findAll(Pageable pageable);

	User findByLogin(String login);

	User findByEmail(String email);

}
