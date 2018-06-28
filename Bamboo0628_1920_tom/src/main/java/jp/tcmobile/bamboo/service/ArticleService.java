package jp.tcmobile.bamboo.service;

import java.util.List;
import org.springframework.data.domain.Page;

import jp.tcmobile.bamboo.model.Article;

public interface ArticleService {
	
	public Article getArticleById(int id);
	
	public List<Article> getArticleList();
	public List<Article> getArticleListByUser_id(int user_id);	
	
	public Page<Article> getArticlePageList(int page);
	public Page<Article> getAllArticlePageListByUser_id(int page, int user_id);
	public Page<Article> getArticlePageListByUser_id( int page,int user_id );
	public Page<Article> getArticlePageListByUser_idAndStatusId(int page, int user_id, int status_id);
	public Page<Article> getArticlePageListByUser_idAndCategory(int page, int user_id, int category_id);
	public Page<Article> getArticlePageListByUser_idAndCategoryAndStatusId(int page, int user_id, int category_id, int status_id);
	
	
	public void saveArticle(Article article);

}
