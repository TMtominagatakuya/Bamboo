package jp.tcmobile.bamboo.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.tcmobile.bamboo.model.Category;
import jp.tcmobile.bamboo.model.Test;
import jp.tcmobile.bamboo.model.Authorization.Role;
import jp.tcmobile.bamboo.repository.CategoryRepository;
import jp.tcmobile.bamboo.service.TestServiceImpl;
import jp.tcmobile.bamboo.service.UserDetail;

@Controller
public class TestController {

	@Autowired
	TestServiceImpl testServiceImpl;

	@Autowired
	CategoryRepository categoryRepository;

	//カテゴリやページの指定が特になければ、すべてのカテゴリ、１ページ目を表示
	@GetMapping(value= {"/test","/test/testlist", "/test/testlist/category_all"})
	public ModelAndView showTestList() {
		return new ModelAndView("redirect:/test/testlist/category_all/page1");
	}

	//カテゴリのみ指定してあれば、そのカテゴリの１ページ目を表示
	@GetMapping("/test/testlist/category_id{category_id}")
	public ModelAndView showTestByCategory(@PathVariable("category_id") String category_id ){
		ModelAndView mav = new ModelAndView("redirect:/test/testlist/category_id"+category_id+"/page1");
		return mav;
	}
	
	//ページだけ指定して、カテゴリの情報がなければ、すべてのカテゴリの当該ページを表示。
	@GetMapping("/test/testlist/page{pageId}")
	public ModelAndView showTestByPage(@PathVariable("pageId") String category_id ){
		ModelAndView mav = new ModelAndView("redirect:/test/testlist/category_all/page{pageId}");
		return mav;
	}

	//カテゴリとページの指定があればそれを表示
	@GetMapping("/test/testlist/category_id{category_id}/page{pageId}")
	public ModelAndView showPageTestByCategory(
			@AuthenticationPrincipal UserDetail userDetail,
			ModelAndView mav,
			@PathVariable("category_id") int category_id,
			@PathVariable("pageId") int pageId){
		Page<Test> tests = testServiceImpl.getTestPageListByCategory(pageId, category_id);
		mav.addObject("value", tests);
		mav.setViewName("/test/testlist");
		boolean isAdmin = userDetail.getAuthorities().toString().contains(Role.admin.toString());
		String accountName = userDetail.getUsername();
		mav.addObject("isAdmin", isAdmin);
		mav.addObject("accountName", accountName);	
		return mav;
	}

	//すべてのカテゴリーの当該ページを表示
	@GetMapping(value= {"/test/testlist/category_all/page{pageId}"})
	public ModelAndView showPageTestByAllCategories(
			@AuthenticationPrincipal UserDetail userDetail, ModelAndView mav,
			@PathVariable("pageId") int pageId) {
		boolean isAdmin = userDetail.getAuthorities().toString().contains(Role.admin.toString());
		String accountName = userDetail.getUsername();
		mav.addObject("isAdmin", isAdmin);
		mav.addObject("accountName", accountName);
		Page<Test> tests = testServiceImpl.getAllTestPageList(pageId);
		mav.addObject("value", tests);
		mav.setViewName("/test/testlist");
		return mav;
	}

	/*    @GetMapping("/test/testlist/category_id{old_category_id}/category_id{new_category_id}/")
    public ModelAndView movePageTestByCategory(String old_category_id, String new_category_id) {
            return new ModelAndView("redirect:/test/testlist/category_id{new_category_id}/page1");
    }*/

	//当該テストのプレビューを表示
	@GetMapping("/test/testpreview{id}")
	public ModelAndView showTest(@AuthenticationPrincipal UserDetail userDetail,ModelAndView mav, @PathVariable("id") String id) {
		mav.setViewName("/test/testpreview");
		boolean isAdmin = userDetail.getAuthorities().toString().contains(Role.admin.toString());
		String accountName = userDetail.getUsername();
		mav.addObject("isAdmin", isAdmin);
		mav.addObject("accountName", accountName);
		Test test = testServiceImpl.findTestById(Integer.parseInt(id));
		mav.addObject(test);
		return mav;
	}

	//テスト作成画面
	@GetMapping("/test/addtest")
	public ModelAndView showAddTest(ModelAndView mav, @AuthenticationPrincipal UserDetail userDetail) {
		mav.setViewName("/test/addtest");
		boolean isAdmin = userDetail.getAuthorities().toString().contains(Role.admin.toString());
		String accountName = userDetail.getUsername();
		mav.addObject("isAdmin", isAdmin);
		mav.addObject("accountName", accountName);
		Test test = testServiceImpl.emptyTest();
		mav.addObject("test", test);
		return mav;
	}

	//テスト作成や更新の、問題追加処理
	@RequestMapping(value= {"/test/addtest", "/test/edittest"}, params={"addProblem"})
	public ModelAndView addProblem(@AuthenticationPrincipal UserDetail userDetail,Test test, ModelAndView mav) {
		testServiceImpl.addProblem(test);
		mav.addObject("test", test);
		boolean isAdmin = userDetail.getAuthorities().toString().contains(Role.admin.toString());
		String accountName = userDetail.getUsername();
		mav.addObject("isAdmin", isAdmin);
		mav.addObject("accountName", accountName);	
		return mav;
	}

	//テスト作成や更新の、問題削除処理
	@RequestMapping(value= {"/test/addtest", "/test/edittest"}, params={"removeProblem"})
	public ModelAndView removeProblem(
			@AuthenticationPrincipal UserDetail userDetail,
			Test test, 
			final HttpServletRequest req, ModelAndView mav) {
		if(test.getProblemList().size()>1) {
			int pId = Integer.valueOf(req.getParameter("removeProblem"));
			test.getProblemList().remove(pId);
		}
		boolean isAdmin = userDetail.getAuthorities().toString().contains(Role.admin.toString());
		String accountName = userDetail.getUsername();
		mav.addObject("isAdmin", isAdmin);
		mav.addObject("accountName", accountName);	
		return mav;
	}

	//テスト作成や更新の、選択肢追加処理
	@RequestMapping(value= {"test/addtest", "test/edittest{id}"}, params={"addChoice"})
	public ModelAndView addChoice(@AuthenticationPrincipal UserDetail userDetail, Test test, ModelAndView mav, HttpServletRequest req) {
		int pId = Integer.valueOf(req.getParameter("addChoice"));
		testServiceImpl.addChoice(test.getProblemList().get(pId));
		mav.addObject("test", test);
		boolean isAdmin = userDetail.getAuthorities().toString().contains(Role.admin.toString());
		String accountName = userDetail.getUsername();
		mav.addObject("isAdmin", isAdmin);
		mav.addObject("accountName", accountName);	
		return mav;
	}

	//テスト作成や更新の、選択肢削除処理
	@RequestMapping(value= {"test/addtest", "test/edittest{id}"}, params={"removeChoice"})
	public ModelAndView removeChoice(@AuthenticationPrincipal UserDetail userDetail, Test test, ModelAndView mav, HttpServletRequest req) {
		// = Integer.valueOf(req.getParameter("addChoice"));
		List<String> idList = Arrays.asList(req.getParameter("removeChoice").split(","));
		int pId = Integer.parseInt(idList.get(0));
		int cId = Integer.parseInt(idList.get(1));
		if(test.getProblemList().get(pId).getChoiceList().size()>2) {
		test.getProblemList().get(pId).getChoiceList().remove(cId);
		mav.addObject("test", test);
		}
		boolean isAdmin = userDetail.getAuthorities().toString().contains(Role.admin.toString());
		String accountName = userDetail.getUsername();
		mav.addObject("isAdmin", isAdmin);
		mav.addObject("accountName", accountName);	
		return mav;
	}

	//テスト更新
	@GetMapping("test/edittest{id}")
	public ModelAndView showEditTest(@AuthenticationPrincipal UserDetail userDetail, ModelAndView mav, @PathVariable("id") String id) {
		mav.setViewName("/test/edittest");
		//萓句､門�ｦ逅�繧偵☆繧九％縺ｨ
		Test test = testServiceImpl.findTestById(Integer.parseInt(id));
		boolean isAdmin = userDetail.getAuthorities().toString().contains(Role.admin.toString());
		String accountName = userDetail.getUsername();
		mav.addObject("isAdmin", isAdmin);
		mav.addObject("accountName", accountName);
		mav.addObject(test);
		// ViewName縺ｯ莉ｮ鄂ｮ縺�
		return mav;
	}

	//テスト作成・編集後の、確認画面への遷移処理
	@RequestMapping(value= {"/test/addtest", "test/edittest{id}"}, params={"TestConfirm"})
	public ModelAndView saveEditedTest(@AuthenticationPrincipal UserDetail userDetail, @ModelAttribute("test") @Validated Test test, BindingResult result, ModelAndView mav) {
		boolean isAdmin = userDetail.getAuthorities().toString().contains(Role.admin.toString());
		String accountName = userDetail.getUsername();
		mav.addObject("isAdmin", isAdmin);
		mav.addObject("accountName", accountName);	
		Category c = categoryRepository.findById(test.getCategory().getId()).orElse(null);
		boolean notEmpty = testServiceImpl.checkEmpty(test.getProblemList(), mav);
		test.getCategory().setName(c.getName());
		if(!result.hasErrors() && notEmpty == true) {
			mav.setViewName("test/testconfirm");
			mav.addObject("test", test);
		} else {
			mav.addObject("msg", "sorry, error is occured...");
		}
		return mav;
	}

	//作成したテストをＤＢに記録すし、一覧画面に戻る。
	//失敗したときの例外処理すること
	@PostMapping("test/testconfirm")
	public ModelAndView saveConfirmedTest(Test test) {
		Test t = test;
		testServiceImpl.assemble(t);
		testServiceImpl.saveTest(t);
		return new ModelAndView("redirect:/test/testlist/category_all/page1");
	}
	
	//テストの削除確認ページ
	@GetMapping("test/deletetest{id}")
	public ModelAndView confirmDeleateTest(@AuthenticationPrincipal UserDetail userDetail, Test test, @PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("test/deletetest");
		boolean isAdmin = userDetail.getAuthorities().toString().contains(Role.admin.toString());
		String accountName = userDetail.getUsername();
		mav.addObject("isAdmin", isAdmin);
		mav.addObject("accountName", accountName);		
		test = testServiceImpl.findTestById(id);
		mav.addObject(test);
		return mav;
	}

	//テストの削除フラグを1にし、一覧画面に戻る。
	@PostMapping("test/deletetest{id}")
	public ModelAndView deleteTest(Test test, @PathVariable("id") int id) {
		testServiceImpl.deleteTestByid(id);
		//DB縺九ｉ蜑企勁縺励◆縺�蝣ｴ蜷医�ｯ荳玖ｨ倥�ｮ繝｡繧ｽ繝�繝峨ｒ蛻ｩ逕ｨ縺吶ｋ
		//testServiceImpl.deleteTestByidFromDB(id);	
		return new ModelAndView("redirect:/test/testlist/category_all/page1");
	}
}