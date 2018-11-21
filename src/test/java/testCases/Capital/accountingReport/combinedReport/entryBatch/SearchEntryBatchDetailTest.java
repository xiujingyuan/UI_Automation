package testCases.Capital.accountingReport.combinedReport.entryBatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.entryBatch.SearchEntryBatchDetail;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.combinedReport.entryBatch.ViewEntryBatch;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午3:27
 * 会计核算->月度报表->合并报表->调整分录抵消明细
 * 页面打开、搜索、重置
 */
public class SearchEntryBatchDetailTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "调整分录抵消明细_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 会计核算->月度报表->合并报表->调整分录抵消明细
        Menu menuAction = new Menu();
        menuAction.gotoReportViewEntryBatch();

        //检查标题是否正确
        driver.getTitle().contains("参与抵消调整分录明细");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/combinedReport/entryBatch/",
                "searchEntryBatchDetail.xlsx"
        );
        return (retObjArr);
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
    @Test(dataProvider = "data", description = "调整分录抵消明细_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String company_id, String project_name,
                            String project_code, String affiliation_plate, String version, String project_comment) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchEntryBatchDetail searchAction = new SearchEntryBatchDetail();
        searchAction.search(case_number, company_id, project_name, project_code, affiliation_plate, version, project_comment);

        boolean a = ElementOperation.isElementPresent(driver, ViewEntryBatch.found);
        Reporter.log(String.valueOf(a));

        if (a) {
            //有搜索结果
            Reporter.log("当前搜索条件下，有调整分录明细。");
        } else {
            //无搜索结果
            Reporter.log("当前搜索条件下，无调整分录明细。");
        }
        Reporter.log("搜索正常。");
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
    @Test(dataProvider = "data", description = "调整分录抵消明细_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String company_id, String project_name, String project_code,
                            String affiliation_plate, String version, String project_comment) {
        //搜索
        SearchEntryBatchDetail searchAction = new SearchEntryBatchDetail();
        searchAction.search(case_number, company_id, project_name, project_code, affiliation_plate, version, project_comment);

        //点击重置搜索
        SearchEntryBatchDetail resetSearchAction = new SearchEntryBatchDetail();
        resetSearchAction.resetSearch(driver);

        //记录重置后结果
        String reset_after_company_id = ElementOperation.getSelectedValue(driver, ViewEntryBatch.company_id);
        String reset_after_project_name = driver.findElement(ViewEntryBatch.project_name).getText();
        String reset_after_project_code = driver.findElement(ViewEntryBatch.project_code).getText();
        String reset_after_affiliation_plate = ElementOperation.getSelectedValue(driver, ViewEntryBatch.affiliation_plate);
//        String reset_after_version = ElementOperation.getSelectedValue(driver, ViewEntryBatch.version);
        String reset_after_project_comment = driver.findElement(ViewEntryBatch.project_comment).getText();

        Assert.assertEquals(reset_after_company_id, "请选择公司");
        Assert.assertEquals(reset_after_project_name, "");
        Assert.assertEquals(reset_after_project_code, "");
        Assert.assertEquals(reset_after_affiliation_plate, "请选择板块类型");
//        Assert.assertEquals(reset_after_version, "");
        Assert.assertEquals(reset_after_project_comment, "");
        Reporter.log("重置正常。");

        Reporter.log("重置后:" + "\n是否参与抵消：" + reset_after_company_id
                + "\n请输入需要过滤的项目名称：" + reset_after_project_name
                + "\n请输入需要过滤的科目代码：" + reset_after_project_code
                + "\n请选择板块类型：" + reset_after_affiliation_plate
//                + "\n版本：" + reset_after_version
                + "\n请输入需要过滤的调整事项：" + reset_after_project_comment + "\n");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}