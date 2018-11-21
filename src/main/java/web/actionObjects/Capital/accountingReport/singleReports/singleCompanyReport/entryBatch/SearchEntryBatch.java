package web.actionObjects.Capital.accountingReport.singleReports.singleCompanyReport.entryBatch;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.singleReports.singleCompanyReport.entryBatch.IndexEntryBatch;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 上午10:17
 * 会计核算->月度报表->单体报表->管报税报公司单体报表->添加调整分录
 * 搜索、重置
 */
public class SearchEntryBatch extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param elimination
     * @param project_name
     * @param project_code
     * @param project_comment
     */
    public void search(String case_number, String month_start, String month_end, String company_id, String version,
                       String elimination, String project_name, String project_code, String project_comment) {
        //进入 会计核算->月度报表->单体报表->管报税报公司单体报表->添加调整分录
        ViewEntryBatch viewAction = new ViewEntryBatch();
        viewAction.view(case_number, month_start, month_end, company_id, version);

        //选择 是否参与抵消，输入 需要过滤的项目名称、科目代码、调整事项
        ElementOperation.selectDropdownValue(driver, IndexEntryBatch.elimination, elimination);
        ElementOperation.editInput(driver, IndexEntryBatch.project_name, project_name);
        ElementOperation.editInput(driver, IndexEntryBatch.project_code, project_code);
        ElementOperation.editInput(driver, IndexEntryBatch.project_comment, project_comment);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexEntryBatch.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexEntryBatch.reset);
    }
}
