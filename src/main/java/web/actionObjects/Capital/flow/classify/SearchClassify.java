package web.actionObjects.Capital.flow.classify;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.flow.classify.IndexPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/6
 * Time: 上午11:02
 * 资金管理->银行流水管理->银行流水科目管理->分类管理
 * 搜索
 */
public class SearchClassify extends BaseAction {
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
    public void search(String case_number, String classify_name, String classify_level, String classify_code,
                       String expect_result) {
        //前往 资金管理->银行流水管理->银行流水科目管理->分类管理
        Menu menuAction = new Menu();
        menuAction.gotoFlowClassifyIndex();

        //输入 分类名称，选择 分类级别，输入 分类编码
        ElementOperation.editInput(driver, IndexPage.classify_name, classify_name);
        ElementOperation.selectDropdownValue(driver, IndexPage.classify_level, classify_level);
        ElementOperation.editInput(driver, IndexPage.classify_code, classify_code);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexPage.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexPage.reset);
    }


}
