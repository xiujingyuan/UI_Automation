package web.actionObjects.Capital.accountingReport.combinedReport.relatedTransactionCancellation;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.combinedReport.relatedTransactionCancellation.IndexRelatedTransactionCancellation;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午6:31
 * 会计核算->月度报表->合并报表->关联交易抵消报表
 * 搜索、重置
 */
public class SearchRelatedTransactionCancellation extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param month
     * @param diameter
     */
    public void search(String case_number, String month, String diameter) {
        //前往 会计核算->月度报表->合并报表->关联交易抵消报表
        Menu menuAction = new Menu();
        menuAction.gotoRelatedTransactionCancellation();

        //选择 月份、板块
        ElementOperation.InputClick(driver, IndexRelatedTransactionCancellation.month, month);
        ElementOperation.selectDropdownValue(driver, IndexRelatedTransactionCancellation.diameter, diameter);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexRelatedTransactionCancellation.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexRelatedTransactionCancellation.reset);
    }
}
