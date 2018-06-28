package jp.tcmobile.bamboo.service;

import jp.tcmobile.bamboo.model.Article;
import jp.tcmobile.bamboo.model.UserAnswer;

public interface UserAnswerService {
	//DBにユーザ解答を登録
	public void saveUserAnswer(Article article,String userName);
	//DBからユーザ解答を抽出
	public UserAnswer getUserAnswer(String userName);

}
