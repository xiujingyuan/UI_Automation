package web.actionObjects.Capital.accountingReport.reportErrorList;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.reportErrorList.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/7
 * Time: 下午3:16
 * 会计核算->月度报表->分析报表->异常统计页
 * 搜索
 */
public class SearchErrorList extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param cate
     * @param expect_count
     * @param version
     */
    public void search(String case_number, String cate, String version, String expect_count) {
        //前往 会计核算->月度报表->分析报表->异常统计页
        Menu menuAction = new Menu();
        menuAction.gotoAccountingReportErrorList();

        //选择 报表名称
        ElementOperation.selectDropdownValue(driver, Index.cate, cate);
        ElementOperation.selectDropdownValue(driver,Index.version,version);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, Index.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, Index.reset);
    }
}
