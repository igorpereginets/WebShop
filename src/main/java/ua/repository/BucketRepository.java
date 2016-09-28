package ua.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.entity.Bucket;

@Repository
public interface BucketRepository extends CrudRepository<Bucket, Integer> {

	Bucket findByUserLogin(String login);

	@Query("SELECT count(g.id) FROM Bucket b LEFT JOIN b.goods g WHERE b.user.login = ?1")
	long countGoods(String name);

}
