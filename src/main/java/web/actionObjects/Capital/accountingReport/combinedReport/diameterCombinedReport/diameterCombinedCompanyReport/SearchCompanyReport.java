package web.actionObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.diameterCombinedCompanyReport;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.diameterCombinedCompanyReport.IndexCompanyReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 上午11:01
 * 会计核算->月度报表->合并报表->管报税报公司合并报表->查看明细数据
 * 搜索、重置
 */
public class SearchCompanyReport extends BaseAction {
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
        //进入 会计核算->月度报表->合并报表->管报税报公司合并报表->查看明细数据
        ViewCompanyReport viewAction = new ViewCompanyReport();
        viewAction.view(case_number, month_start, month_end, diameter, version);

        //选择 起止月份、板块、版本
        ElementOperation.InputClick(driver, IndexCompanyReport.month_start, month_start);
        ElementOperation.InputClick(driver, IndexCompanyReport.month_end, month_end);
        ElementOperation.selectDropdownValue(driver, IndexCompanyReport.diameter, diameter);
        ElementOperation.selectDropdownValue(driver, IndexCompanyReport.version, version);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexCompanyReport.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexCompanyReport.reset);
    }
}
