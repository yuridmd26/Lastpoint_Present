package br.com.present.groups.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.present.commons.repository.IBaseRepository;
import br.com.present.commons.model.GroupEntity;

@Repository
public interface IGroupRepository extends IBaseRepository<GroupEntity, Long> {

	Optional<List<GroupEntity>> findByCode(String code);

}
