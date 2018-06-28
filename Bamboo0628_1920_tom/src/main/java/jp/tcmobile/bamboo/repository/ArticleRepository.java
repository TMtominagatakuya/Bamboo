package jp.tcmobile.bamboo.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.tcmobile.bamboo.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	public Page<Article> findAll
	(Pageable pageable);
	
	public Page<Article> findByUser_id
	(int user_id,Pageable pageable);
	
	public Page<Article> findByUser_idAndTestIsDeleted
	(int user_id,byte isDeleted,Pageable pageable);
	
	public Page<Article> findByUser_idAndTestCategoryIdAndTestIsDeleted
	(int user_id, int category_id,byte isDeleted, Pageable pageable);
	
	public Page<Article> findByUserIdAndStatusIdAndTestIsDeleted
	(int user_id, int status_id,byte isDeleted, Pageable pageable);
	
	public Page<Article> findByUserIdAndTestCategoryIdAndStatusIdAndTestIsDeleted
	(int user_id, int category_id, int status_id,byte isDeleted,Pageable pageable);	
	
	public List<Article> findAll();
	
	public List<Article> findByUser_id(int user_id);
	
	//ユーザ名
	public Article findByUser_idAndTest_id(int user_id, int test_id);
}
