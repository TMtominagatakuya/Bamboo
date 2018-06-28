package jp.tcmobile.bamboo.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import jp.tcmobile.bamboo.repository.TestRepository;
import jp.tcmobile.bamboo.model.Category;
import jp.tcmobile.bamboo.model.Choice;
import jp.tcmobile.bamboo.model.Description;
import jp.tcmobile.bamboo.model.Problem;
import jp.tcmobile.bamboo.model.Test;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	TestRepository testRepository;
	
	@Override
	public List<Test> getTestList(){
		List<Test> tests = testRepository.findAll();
		return tests;
	}
	@Override
	public List<Test> getTestListByCategory(int category_id){
		List<Test> tests = testRepository.findByCategoryId(category_id);
		return tests;
	}
	@Override
	public Page<Test> getAllTestPageList(int page){
		page -= 1; //page1 → PageRequest.of(0, 20);を指定
		Pageable pageable = PageRequest.of(page, 20);
		//削除フラグを検討、カテゴリの指定などがあった場合はそれも適用すること。
		Page<Test> tests = testRepository.findAll(pageable);
		return tests;
	}

	@Override
	public Page<Test> getTestPageList(int page){
		page -= 1; //page1 → PageRequest.of(0, 20);を指定
		Pageable pageable = PageRequest.of(page, 20);
		//削除フラグを検討、カテゴリの指定などがあった場合はそれも適用すること。
		Page<Test> tests = testRepository.findByIsDeleted((byte)0,pageable);
		return tests;
	}
	@Override
	public Page<Test> getTestPageListByCategory(int page, int category_id){
		page -= 1; //page1 → PageRequest.of(0, 20);を指定
		Pageable pageable = PageRequest.of(page, 20);
		//削除フラグを検討、カテゴリの指定などがあった場合はそれも適用すること。
		Page<Test> tests = testRepository.findByCategoryId(category_id,pageable);
		return tests;
	}
	@Override
	public Test findTestById(int id) {
		return testRepository.findById(id).orElse(null);
	}
	@Override
	public void saveTest(Test test) {
		// TODO 自動生成されたメソッド・スタブ
		testRepository.saveAndFlush(test);
	}


	/* 以下、Entityの初期化処理 */

	//問題Entityの初期化
	public void addProblem(Test test) {
		Problem p = new Problem();
		addChoice(p);
		addChoice(p);
		List<Problem>plist = test.getProblemList();
		p.setTest(test);
		plist.add(p);
		return;
	}

	//選択肢Entityの初期化
	public void addChoice(Problem p) {
		Choice c = new Choice();
		List<Choice> clist = p.getChoiceList();
		c.setIs_correct((byte)0);
		c.setProblem(p);
		clist.add(c);
		p.setChoiceList(clist);
		return;
	}

	//カテゴリEntityの初期化
	public void addCategory(Test t) {
		Category ca = new Category();
		List<Test> testList = new ArrayList<Test>();
		testList.add(t);
		ca.setTestList(testList);
		t.setCategory(ca);
	}

	//テストEntityの初期化
	public Test emptyTest() {
		Test test = new Test();
		addCategory(test);
		addProblem(test);
		return test;
	}

	//Entityの関係を定義し、Testを組み立てる。DBに登録する前に行うこと。
	public void assemble(Test t) {
		t.getCategory().getTestList().add(t);
		for(Problem p : t.getProblemList()) {
			p.setTest(t);
			if(p.getTypeId()==0) {
				for(Choice c : p.getChoiceList()) {
					c.setProblem(p);
				}
			}
			else if(p.getTypeId()==1) {
				p.getDescription().setProblem(p);
			}
		}
	}


	//テストに削除フラグを作る(is_deletedフラグを1に更新する)
	//TODO レコードにないIDを指定したときの例外処理
	public void deleteTestByid(int id) {//0626tom
		if(testRepository.existsById(id)==true) {//仮の例外処理
			Test test = testRepository.getOne(id);
			test.setIsDeleted((byte)1);
			testRepository.saveAndFlush(test);
		}
		else {
			System.out.println("IDが見つかりません");
		}
	}

	//テストをDBから削除する
	//TODO レコードにないIDを指定したときの例外処理
	public void deleteTestByidFromDB(int id) {;//0626tom
	if(testRepository.existsById(id)==true) {//仮の例外処理
		testRepository.deleteById(id);
	}else {
		System.out.println("IDが見つかりません");
	}
	}
	

	//問題の中身を検査する。
	@Override
	public boolean checkEmpty(List<Problem> problems, ModelAndView mav) {
		boolean result = true;
		for(Problem problem : problems) {
			if(problem.getQuestion().isEmpty()) {
				mav.addObject("questionErr", "問題文が入力されていません");
				result = false;
			}
			byte typeId = problem.getTypeId();
			if(typeId == 0) {
				List<Choice> choises = problem.getChoiceList();
				for(Choice c : choises) {
					if(c.getContent().isEmpty()) {
						mav.addObject("choiceValErr", "選択肢の内容が入力されていません");
						result = false;
					}
				}
			} else {
				Description description = problem.getDescription();
				if(description.getDescription_answer().isEmpty()) {
					mav.addObject("descriptionErr", "記述式問題の解答が入力されていません");
					result = false;
				}
			}
		}
		return result;
	}
}