package web.actionObjects.Capital.flow.classify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.flow.classify.IndexPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/6
 * Time: 下午4:02
 * 资金管理->银行流水管理->银行流水科目管理->分类管理
 * 删除分类
 */
public class DeleteClassify extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param classify_name
     * @param classify_level
     * @param classify_code
     * @param expect_result
     */
    public void delete(String case_number, String classify_name, String classify_level, String classify_code,
                       String expect_result) {
        //搜索需要删除的分类
        SearchClassify searchAction = new SearchClassify();
        searchAction.search(case_number, classify_name, classify_level, classify_code, expect_result);

        //点击【删除】
        ElementOperation.buttonClick(driver, IndexPage.delete);

        String alt_text = "您确定要删除此项吗？";
        ElementOperation.closeAlertAndGetItsText(driver, alt_text);

        //log中打印页面标题
        Reporter.log("当前页面：" + driver.getTitle());

    }
}