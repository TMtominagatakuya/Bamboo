package jp.tcmobile.bamboo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import jp.tcmobile.bamboo.model.Article;
import jp.tcmobile.bamboo.model.Choice;
import jp.tcmobile.bamboo.model.Description;
import jp.tcmobile.bamboo.model.Problem;
import jp.tcmobile.bamboo.model.User;
import jp.tcmobile.bamboo.model.UserAnswer;
import jp.tcmobile.bamboo.repository.ArticleRepository;
import jp.tcmobile.bamboo.repository.TestRepository;
import jp.tcmobile.bamboo.repository.UserAnswerRepository;

public class UserAnswerServiceImpl implements UserAnswerService {

	@Autowired
	UserAnswerRepository userAnswerRepository;

	@Autowired
	ArticleRepository articleRepository;



	//未解答かどうかのチェック
	public boolean isAnswered(Article article) {
		if(article.getStatusId() != 0) {	//1,2,3:解答済み、再提出、完了
			return true;
		} else {							//0:未完了
			return false;
		}
	}

	//DBにユーザ解答を登録
	public void saveUserAnswer(List<UserAnswer> userAnswers) {
		userAnswerRepository.saveAndFlush(userAnswers);
	}

	//DBからユーザ解答を抽出
	public UserAnswer getUserAnswer(User user,Problem problem) {
		return userAnswerRepository.findbyUserAndProblem(user, problem);
	}

	//DBから問題の正解選択肢番号を抽出
	public Set<Integer> getChoiceAnswer(Problem problem) {
		List<Choice> choices = problem.getChoiceList();//選択肢一覧
		Set<Integer> collect_choices = new HashSet<Integer>();//正解セット
		choices.stream()
		.filter(c -> c.getIs_correct() == (byte)1)//正解フラグ抽出
		.forEach(c -> collect_choices.add(c.getId()));//正解セットに格納
		return collect_choices;
	}

	//DBから問題の正解記述内容を抽出
	public String getDescriptionAnswer(Problem problem) {
		return problem.getDescription().getDescription_answer();
	}



}
