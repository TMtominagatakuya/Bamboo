package jp.tcmobile.bamboo.service;

import java.util.List;
import java.util.Set;

import jp.tcmobile.bamboo.model.Article;
import jp.tcmobile.bamboo.model.Problem;
import jp.tcmobile.bamboo.model.User;
import jp.tcmobile.bamboo.model.UserAnswer;

public interface UserAnswerService {
	//未解答かどうかのチェック
	public boolean isAnswered(Article article);
	//DBにユーザ解答を登録
	public void saveUserAnswer(List<UserAnswer> userAnswers);
	//DBからユーザ解答を抽出
	public UserAnswer getUserAnswer(User user, Problem problem);
	//DBから問題の正解選択肢番号を抽出
	public Set<Integer> getChoiceAnswer(Problem problem);
	//DBから問題の正解記述内容を抽出
	public String getDescriptionAnswer(Problem problem);
	}
