package web.actionObjects.Capital.accountingReport.singleReports.singleCompanyReport.entryBatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import web.actionObjects.Capital.accountingReport.singleReports.singleCompanyReport.SearchSingleCompanyReport;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.singleReports.singleCompanyReport.IndexSingleCompanyReport;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 上午11:29
 * 会计核算->月度报表->单体报表->管报税报公司单体报表
 * 进入页面 添加调整分录
 */
public class ViewEntryBatch extends BaseAction {
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
    public void view(String case_number, String month_start, String month_end, String company_id, String version) {
        //搜索
        SearchSingleCompanyReport searchAction = new SearchSingleCompanyReport();
        searchAction.search(case_number, month_start, month_end, company_id, version);

        //点击【添加调整分录】
        ElementOperation.buttonClick(driver, IndexSingleCompanyReport.add_entry_batch);

        //焦点移至新打开的页面
        Set<String> allWindowsId = driver.getWindowHandles();
        Iterator<String> it = allWindowsId.iterator();
        while (it.hasNext()) {
            String wind = it.next();
            wait1s();
            if (driver.switchTo().window(wind).getTitle().contains("添加分录")) {
                driver.switchTo().window(wind);
                Reporter.log("当前页面：" + driver.getTitle());
                break;
            }
        }
    }
}
