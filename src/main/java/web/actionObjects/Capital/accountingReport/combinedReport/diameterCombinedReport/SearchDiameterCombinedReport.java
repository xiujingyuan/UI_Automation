package web.actionObjects.Capital.accountingReport.combinedReport.diameterCombinedReport;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.IndexDiameterCombinedReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 上午10:35
 * 会计核算->月度报表->合并报表->管报税报公司合并报表
 * 搜索、重置
 */
public class SearchDiameterCombinedReport extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param month_start
     * @param month_end
     * @param diameter
     * @param version
     */
    public void search(String case_number, String month_start, String month_end, String diameter, String version) {
        //前往 会计核算->月度报表->合并报表->管报税报公司合并报表
        Menu menuAction = new Menu();
        menuAction.gotoDiameterCombinedReport();

        //选择 起止月份、板块、版本
        ElementOperation.InputClick(driver, IndexDiameterCombinedReport.month_start, month_start);
        ElementOperation.InputClick(driver, IndexDiameterCombinedReport.month_end, month_end);
        ElementOperation.selectDropdownValue(driver, IndexDiameterCombinedReport.diameter, diameter);
        ElementOperation.selectDropdownValue(driver, IndexDiameterCombinedReport.version, version);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexDiameterCombinedReport.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexDiameterCombinedReport.reset);
    }
}
