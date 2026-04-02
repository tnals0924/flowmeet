package kr.flowmeet.domain.user.repository;

import kr.flowmeet.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
