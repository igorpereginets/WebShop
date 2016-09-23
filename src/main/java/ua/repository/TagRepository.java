package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.entity.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer>, JpaSpecificationExecutor<Tag> {

	Page<Tag> findAll(Pageable pageable);

	Tag findByName(String name);

}
