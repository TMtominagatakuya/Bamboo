package jp.tcmobile.bamboo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.tcmobile.bamboo.model.Problem;
import jp.tcmobile.bamboo.model.User;
import jp.tcmobile.bamboo.model.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {
	
	public UserAnswer findbyUserAndProblem(User user,Problem problem);
	
}

