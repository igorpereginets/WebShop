package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.DTO.CategoryAndCount;
import ua.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

	Page<Category> findAll(Pageable pageable);

	Category findByName(String name);

	//"SELECT new ua.DTO.CategoryAndCount(c.id, c.name, count(g.id)) FROM Category c LEFT JOIN c.goods g WHERE g.state.name <> 'SOLD' GROUP BY c.id"
	//TODO create query to show only not sold goods
	@Query("SELECT new ua.DTO.CategoryAndCount(c.id, c.name, count(g.id)) FROM Category c LEFT JOIN c.goods g GROUP BY c.id")
	List<CategoryAndCount> findAllWithGoodsCount();

}
