package web.actionObjects.Capital.accountingReport.combinedReport.entryBatch;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.combinedReport.entryBatch.ViewEntryBatch;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午3:21
 * 会计核算->月度报表->合并报表->调整分录抵消明细
 * 搜索、重置
 */
public class SearchEntryBatchDetail extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param company_id
     * @param project_name
     * @param project_code
     * @param affiliation_plate
     * @param version
     * @param project_comment
     */
    public void search(String case_number, String company_id, String project_name, String project_code,
                       String affiliation_plate, String version, String project_comment) {
        //前往 会计核算->月度报表->合并报表->调整分录抵消明细
        Menu menuAction = new Menu();
        menuAction.gotoReportViewEntryBatch();

        //选择 公司，输入 需要过滤的项目名称、科目代码，选择板块类型，输入 需要过滤的调整事项
        ElementOperation.selectDropdownValue(driver, ViewEntryBatch.company_id, company_id);
        ElementOperation.editInput(driver, ViewEntryBatch.project_name, project_name);
        ElementOperation.editInput(driver, ViewEntryBatch.project_code, project_code);
        ElementOperation.selectDropdownValue(driver, ViewEntryBatch.affiliation_plate, affiliation_plate);
        ElementOperation.selectDropdownValue(driver, ViewEntryBatch.version, version);
        ElementOperation.editInput(driver, ViewEntryBatch.project_comment, project_comment);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, ViewEntryBatch.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, ViewEntryBatch.reset);
    }
}
