package luong.lit.security.repository;

import luong.lit.security.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Iterable<User> findAllByIdIn(Iterable<Long> ids);
    Iterable<User> findAllByIdNotIn(Iterable<Long> ids);
}
