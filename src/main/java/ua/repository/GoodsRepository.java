package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.entity.Goods;

@Repository
public interface GoodsRepository extends JpaSpecificationExecutor<Goods>, JpaRepository<Goods, Integer> {

	Page<Goods> findAll(Pageable pageable);

	@Query("SELECT g FROM Goods g LEFT JOIN FETCH g.tags WHERE g.id = ?1")
	Goods findOneWithTags(int id);

	List<Goods> findByUserLogin(String login);
}
