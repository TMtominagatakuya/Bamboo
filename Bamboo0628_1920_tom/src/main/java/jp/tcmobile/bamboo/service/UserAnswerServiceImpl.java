package jp.tcmobile.bamboo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jp.tcmobile.bamboo.model.Article;
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
	
	//DBにユーザ解答をDBに登録(成功true失敗false)
	public boolean saveUserAnswer(Article article, User user) {
		
		if(article.getStatusId() != 0) return false; //完了・解答済み・再提出
		
		
		List<Problem> problems = article.getTest().getProblemList();
		for(Problem p :problems) {
			UserAnswer userAnswer = new UserAnswer();
			userAnswer.setUser(user);
			userAnswer.setProblem(p);
			if(p.getTypeId()==(byte)0) {
				userAnswer.setChoice_id();
			}
			else if(p.getTypeId()==(byte)1) {
				
			}
			else {
				System.out.println("選択肢、記述以外の問題の出力");
			}
		}	
	}
	
	//ユーザーの解答済みの問題は解けないようにする
	
	//課題解答の状態（未完了、解答済み、再提出、完了）を変更
	
	//DBからユーザ解答を抽出
	public UserAnswer getUserAnswer(String userName) {
		
	}

}
