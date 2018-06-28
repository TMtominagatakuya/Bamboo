package jp.tcmobile.bamboo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import jp.tcmobile.bamboo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByAcountName(String acountName);
}
