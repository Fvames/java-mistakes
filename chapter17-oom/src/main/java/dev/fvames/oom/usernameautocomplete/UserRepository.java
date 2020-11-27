package dev.fvames.oom.usernameautocomplete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO 类描述
 *
 * @author
 * @version 2020/11/27 15:00
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
