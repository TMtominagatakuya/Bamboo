package jp.tcmobile.bamboo.service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.ModelAndView;

import jp.tcmobile.bamboo.model.Problem;
import jp.tcmobile.bamboo.model.Test;

public interface TestService {
    public Test findTestById(int id);
    public void saveTest(Test test);
    public List<Test> getTestList();
    public List<Test> getTestListByCategory(int category_id);
    public Page<Test> getAllTestPageList(int page);
    public Page<Test> getTestPageList(int page);
    public Page<Test> getTestPageListByCategory(int page, int category_id);
    public void deleteTestByid(int id);//0626tom isdeleatedフラグを1にする
    public void deleteTestByidFromDB(int id);//0626tom
    public boolean checkEmpty(List<Problem> problems, ModelAndView mav);
}