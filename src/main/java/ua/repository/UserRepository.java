package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User>, JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.address WHERE u.id = ?1")
	User findOneWithAddress(int id);

	Page<User> findAll(Pageable pageable);

	User findByLogin(String login);

	User findByEmail(String email);

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.address a LEFT JOIN FETCH a.city c LEFT JOIN FETCH c.country WHERE u.login = ?1")
	User findByLoginWithAddress(String login);

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.bucket WHERE u.login = ?1")
	User findByLoginWithBucket(String login);

}
