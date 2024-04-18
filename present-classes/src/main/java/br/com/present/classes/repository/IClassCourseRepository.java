package br.com.present.classes.repository;

import br.com.present.commons.model.ClassCourseEntity;
import br.com.present.commons.repository.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClassCourseRepository extends IBaseRepository<ClassCourseEntity, Long> {

    @Query("SELECT cc FROM ClassCourseEntity cc WHERE cc.classEntity.id = :classId" +
            " AND cc.courseEntity.id = :courseId")
    Optional<List<ClassCourseEntity>> findByClassIdAndCourseId(@Param("classId") Long classId, @Param("courseId") Long courseId);

}