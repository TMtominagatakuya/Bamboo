package jp.tcmobile.bamboo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import jp.tcmobile.bamboo.model.Article;
import jp.tcmobile.bamboo.model.User;
import jp.tcmobile.bamboo.model.UserAnswer;
import jp.tcmobile.bamboo.repository.UserRepository;
import jp.tcmobile.bamboo.service.ArticleServiceImpl;
import jp.tcmobile.bamboo.service.UserAnswerServiceImpl;
import jp.tcmobile.bamboo.service.UserDetail;
@Controller
public class ArticleController {
	@Autowired 
	ArticleServiceImpl articleServiceImpl;
	
	@Autowired
	UserAnswerServiceImpl userAnswerServiceImpl;

	@Autowired 
	UserRepository userRepository;

	//全カテゴリー全ステータスの課題解答をページ表示する画面へのリダイレクト
	@GetMapping({"article/category_all","/article/category_all/status_all","article/","article/category_all/page1"})
	public ModelAndView redirectCategory_all() {
		return new ModelAndView("redirect:/article/category_all/status_all/page1") ;
	}

	//全カテゴリー全ステータスの課題解答をページ表示する画面
	@GetMapping("article/category_all/status_all/page{page_id}")
	public ModelAndView showArticlesByAllCategoryAndAllStatus(
			ModelAndView mav,
			@AuthenticationPrincipal UserDetail userDetail,
			@PathVariable("page_id") int page_id
			){
		User user =userRepository.findByAcountName(userDetail.getUsername());
		Page<Article> articles = articleServiceImpl.getArticlePageListByUser_id(page_id, user.getId());
		mav.addObject("value", articles);
		mav.addObject("categoryType","category_all");
		mav.addObject("statusType","status_all");
		mav.setViewName("/article/articlelist");
		return mav;
	}

	//個別カテゴリー全ステータスの課題解答をページ表示する画面へのリダイレクト
	@GetMapping({"/article/category_id{category_id}","/article/category_id{category_id}/status_all"})
	public ModelAndView redirectCategory_id(
			@PathVariable("category_id") int category_id
			) {
		return new ModelAndView("redirect:/article/category_id{category_id}/status_all/page1");
	}

	//個別カテゴリー全ステータスの課題解答をページ表示する画面
	@GetMapping("/article/category_id{category_id}/status_all/page{page_id}")
	public ModelAndView showUserPageArticleByAllCategoryAndOneStatus(
			ModelAndView mav,
			@AuthenticationPrincipal UserDetail userDetail,
			@PathVariable("category_id") int category_id,
			@PathVariable("page_id") int page_id
			){
		User user =userRepository.findByAcountName(userDetail.getUsername());
		Page<Article> articles = articleServiceImpl.getArticlePageListByUser_idAndCategory(page_id, user.getId(), category_id);
		mav.addObject("value", articles);
		
		
		mav.addObject("categoryType","category_id"+category_id);
		mav.addObject("statusType","status_all");
		mav.setViewName("/article/articlelist");
		return mav;
	}

	//全カテゴリー個別ステータスの課題解答をページ表示する画面へのリダイレクト
	@GetMapping({"/article/category_all/status_id{status_id}"})
	public ModelAndView redirectAllCategoryAndOneStatus(
			@PathVariable("status_id") int status_id
			) {
		return new ModelAndView("redirect:/article/category_all/status_id{status_id}/page1");
	}


	//全カテゴリー個別ステータスの課題解答をページ表示する画面
	@GetMapping("/article/category_all/status_id{status_id}/page{page_id}")
	public ModelAndView showUserPageArticleByStatusAndCategory(
			ModelAndView mav,
			@AuthenticationPrincipal UserDetail userDetail,
			@PathVariable("status_id") int status_id,
			@PathVariable("page_id") int page_id
			){
		User user =userRepository.findByAcountName(userDetail.getUsername());
		Page<Article> articles = articleServiceImpl.getArticlePageListByUser_idAndStatusId(page_id, user.getId(), status_id);
		mav.addObject("value", articles);
		mav.addObject("categoryType","category_all");
		mav.addObject("statusType","status_id"+status_id);
		mav.setViewName("/article/articlelist");
		return mav;
	}

	//個別カテゴリー個別ステータスの課題解答をページ表示する画面へのリダイレクト
	@GetMapping({"/article/category_id{category_id}/status_id{status_id}"})
	public ModelAndView redirectOneCategoryOneStatus(
			@PathVariable("category_id") int category_id,
			@PathVariable("status_id") int status_id
			) {
		return new ModelAndView("redirect:/article/category_id{category_id}/status_id{status_id}/page1");
	}

	//個別カテゴリー個別ステータスの課題解答をページ表示する画面
	@GetMapping("/article/category_id{category_id}/status_id{status_id}/page{page_id}")
	public ModelAndView showUserArticleByStatusAndCategoryAndStatus(
			ModelAndView mav,
			@AuthenticationPrincipal UserDetail userDetail,
			@PathVariable("status_id") int status_id,
			@PathVariable("category_id") int category_id,
			@PathVariable("page_id") int page_id
			){
		User user =userRepository.findByAcountName(userDetail.getUsername());
		Page<Article> articles = articleServiceImpl.getArticlePageListByUser_idAndCategoryAndStatusId(page_id, user.getId(), category_id, status_id);
		mav.addObject("value", articles);
		mav.addObject("categoryType","category_id"+category_id);
		mav.addObject("statusType","status_id"+status_id);
		mav.setViewName("/article/articlelist");
		return mav;
	}
	
	//個別カテゴリー個別ステータスの課題解答をページ表示する画面へのリダイレクト
	@GetMapping({"/article{article_id}","/article/category_id{category_id}/status_all/{article_id}","/article/category_all/status_id{status_id]/{article_id}"})
	public ModelAndView redirectArticle(
			@PathVariable("article_id") int article_id
			) {
		Article article = articleServiceImpl.getArticleById(article_id);
		return new ModelAndView("redirect:/article/category_id"+article.getTest().getCategory().getId()+"/status_id"+article.getStatusId()+"/"+article_id);
	}

	//課題解答詳細ページを表示する画面
	@GetMapping("/article/category_id{category_id}/status_id{status_id}/{article_id}")
	public ModelAndView showArticle(
			ModelAndView mav,
			@AuthenticationPrincipal UserDetail userDetail,
			@PathVariable("status_id") int status_id,
			@PathVariable("category_id") int category_id,
			@PathVariable("article_id") int article_id
			){
		Article article = articleServiceImpl.getArticleById(article_id);
		mav.addObject("article", article);
		UserAnswer userAnswer = new UserAnswer();
		mav.addObject("userAnswer",userAnswer);
		mav.setViewName("article/article");
		return mav;
	}
	
	//ユーザ解答を登録する処理
//	@PostMapping("/article/article")
//	public ModelAndView saveUserAnswer(ModelAndView mav,
//			@AuthenticationPrincipal UserDetail userDetail,
//			@ModelAttribute("testAnswer") UserAnswer userAnswer,
//			Article article){
//		userAnswerServiceImpl.saveUserAnswer(userAnswer);
//		mav.addObject("userAnswer", userAnswer);
//		mav.addObject("article", article);
//		mav.setViewName("article/articleanswer");
//		return mav;
//	}
	
	@PostMapping("/article/category_id{category_id}/status_id{status_id}/{article_id}")
	public ModelAndView saveUserAnswer(ModelAndView mav,
			@AuthenticationPrincipal UserDetail userDetail,
			UserAnswer userAnswer,
			Article article){
		if(userAnswerServiceImpl.isAnswered(article)) {
			throw new IllegalArgumentException("解答済みです。");//例外処理
		}
		userAnswerServiceImpl.saveUserAnswer(userAnswers);
		mav.addObject("userAnswer", userAnswer);
		mav.addObject("article", article);
		mav.setViewName("article/articleanswer");
		return mav;
	}
	
	


}