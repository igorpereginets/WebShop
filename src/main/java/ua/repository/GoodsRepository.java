package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ua.entity.Goods;

@Repository
public interface GoodsRepository extends JpaSpecificationExecutor<Goods>, JpaRepository<Goods, Integer> {

	Page<Goods> findAll(Pageable pageable);

}
