package br.com.present.users.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.present.commons.repository.IBaseRepository;
import br.com.present.commons.model.UserEntity;

@Repository
public interface IUserRepository extends IBaseRepository<UserEntity, Long> {

	Optional<List<UserEntity>> findByLogin(String login);

	@Query("SELECT u.type FROM UserEntity u WHERE u.id = :id")
	Optional<String> findTypeById(@Param("id") Long id);

}