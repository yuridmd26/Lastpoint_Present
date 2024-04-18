package br.com.present.courses.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.present.commons.repository.IBaseRepository;
import br.com.present.commons.model.CourseEntity;

@Repository
public interface ICourseRepository extends IBaseRepository<CourseEntity, Long> {

	Optional<List<CourseEntity>> findByCode(String code);

}