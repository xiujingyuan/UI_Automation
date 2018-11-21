package web.actionObjects.Capital.accountingReport.singleReports.singleCompanyReport;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.singleReports.singleCompanyReport.IndexSingleCompanyReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午5:05
 * 会计核算->月度报表->单体报表->管报税报公司单体报表
 * 搜索、重置
 */
public class SearchSingleCompanyReport extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param month_start
     * @param month_end
     * @param company_id
     * @param version
     */
    public void search(String case_number, String month_start, String month_end, String company_id, String version) {
        //前往 会计核算->月度报表->单体报表->管报税报公司单体报表
        Menu menuAction = new Menu();
        menuAction.gotoSingleCompanyReport();

        //选择 起止月份、公司、版本
        ElementOperation.InputClick(driver, IndexSingleCompanyReport.month_start, month_start);
        ElementOperation.InputClick(driver, IndexSingleCompanyReport.month_end, month_end);
        ElementOperation.selectDropdownValue(driver, IndexSingleCompanyReport.company_id, company_id);
        ElementOperation.selectDropdownValue(driver, IndexSingleCompanyReport.version, version);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexSingleCompanyReport.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexSingleCompanyReport.reset);
    }
}
