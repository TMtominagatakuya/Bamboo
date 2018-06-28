package jp.tcmobile.bamboo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jp.tcmobile.bamboo.repository.ArticleRepository;
import jp.tcmobile.bamboo.model.Article;

@Service
public class ArticleServiceImpl implements ArticleService {
	
@Autowired
ArticleRepository articleRepository;

@Override
public Article getArticleById(int id) {
    return articleRepository.findById(id).orElse(null);
}

@Override
public List<Article> getArticleList(){
    List<Article> articles = articleRepository.findAll();
    return articles;
}

@Override
public List<Article> getArticleListByUser_id(int user_id){
    List<Article> articles = articleRepository.findByUser_id(user_id);
    return articles;
}





@Override
public Page<Article> getArticlePageList(int page){
    page -= 1;//page1（1ページ目） → PageRequest.of(0, 20);になるようにする
    Pageable pageable = PageRequest.of(page, 20);
    //削除フラグを検討、カテゴリの指定などがあった場合はそれも適用すること。
    Page<Article> articles = articleRepository.findAll(pageable);
    return articles;
}

@Override
public Page<Article> getAllArticlePageListByUser_id(int page_number, int user_id){ 
    page_number -= 1;//page1（1ページ目） → PageRequest.of(0, 20);になるようにする
    Pageable pageable = PageRequest.of(page_number, 20);
    //削除フラグを検討、カテゴリの指定などがあった場合はそれも適用すること。
    Page<Article> articles = articleRepository.findByUser_id(user_id, pageable);
    return articles;
}

@Override
public Page<Article> getArticlePageListByUser_id(int page_number, int user_id){ 
    page_number -= 1;//page1（1ページ目） → PageRequest.of(0, 20);になるようにする
    Pageable pageable = PageRequest.of(page_number, 20);
    //削除フラグを検討、カテゴリの指定などがあった場合はそれも適用すること。
    Page<Article> articles = articleRepository.findByUser_idAndTestIsDeleted(user_id,(byte)0,pageable);
    return articles;
}



//user_id と　カテゴリーから課題解答を抽出
@Override
public Page<Article> getArticlePageListByUser_idAndCategory(int page, int user_id, int category_id){
    page -= 1; //page1 → PageRequest.of(0, 20);を指定
    Pageable pageable = PageRequest.of(page, 20);
    //削除フラグを検討、カテゴリの指定などがあった場合はそれも適用すること。
    Page<Article> articles = articleRepository.findByUser_idAndTestCategoryIdAndTestIsDeleted(user_id,category_id,(byte)0,pageable);
    return articles;
}

@Override
public Page<Article> getArticlePageListByUser_idAndStatusId(int page, int user_id, int status_id) {
	page -= 1; //page1 → PageRequest.of(0, 20);を指定
	Pageable pageable = PageRequest.of(page, 20);
	//削除フラグを検討、カテゴリの指定などがあった場合はそれも適用すること。
	Page<Article> articles = articleRepository.findByUserIdAndStatusIdAndTestIsDeleted(user_id,status_id,(byte)0,pageable);
	return articles;
}

@Override
public Page<Article> getArticlePageListByUser_idAndCategoryAndStatusId(int page, int user_id, int category_id,
		int status_id) {
	page -= 1; //page1 → PageRequest.of(0, 20);を指定
	Pageable pageable = PageRequest.of(page, 20);
	//削除フラグを検討、カテゴリの指定などがあった場合はそれも適用すること。
	Page<Article> articles = articleRepository.findByUserIdAndTestCategoryIdAndStatusIdAndTestIsDeleted(user_id,category_id,status_id,(byte)0,pageable);
	return articles;
}



@Override
public void saveArticle(Article article) {
    // TODO 自動生成されたメソッド・スタブ
    articleRepository.saveAndFlush(article);
}
}