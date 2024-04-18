package br.com.present.disciplines.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.present.commons.repository.IBaseRepository;
import br.com.present.commons.model.DisciplineEntity;

@Repository
public interface IDisciplineRepository extends IBaseRepository<DisciplineEntity, Long> {

	Optional<List<DisciplineEntity>> findByCode(String code);

}