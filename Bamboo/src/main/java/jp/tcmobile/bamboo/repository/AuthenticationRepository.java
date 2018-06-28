package jp.tcmobile.bamboo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jp.tcmobile.bamboo.model.Authentication;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, String>{
}