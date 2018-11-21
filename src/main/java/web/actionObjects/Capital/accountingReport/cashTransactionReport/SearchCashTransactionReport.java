package web.actionObjects.Capital.accountingReport.cashTransactionReport;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.cashTransactionReport.IndexCashTransactionReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午4:32
 * 会计核算->月度报表->分析报表->现金流量表
 * 搜索、重置
 */
public class SearchCashTransactionReport extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param month
     * @param company
     */
    public void search(String case_number, String month, String company) {
        //前往 会计核算->月度报表->分析报表->现金流量表
        Menu menuAction = new Menu();
        menuAction.gotoCashTransactionReport();

        //选择 月份、公司
        ElementOperation.InputClick(driver, IndexCashTransactionReport.month, month);
        ElementOperation.selectDropdownValue(driver, IndexCashTransactionReport.company, company);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexCashTransactionReport.search);
        wait3s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexCashTransactionReport.reset);
    }
}
