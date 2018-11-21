package testCases.Capital.accountingReport.singleReports.singleCompanyReport.entryBatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.singleReports.singleCompanyReport.entryBatch.SearchEntryBatch;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.singleReports.singleCompanyReport.entryBatch.IndexEntryBatch;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 上午10:40
 * 会计核算->月度报表->单体报表->管报税报公司单体报表->添加调整分录
 * 搜索、重置
 */
public class SearchEntryBatchTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/singleReports/singleCompanyReport/entryBatch/",
                "searchEntryBatch.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param month_start
     * @param month_end
     * @param company_id
     * @param version
     * @param elimination
     * @param project_name
     * @param project_code
     * @param project_comment
     */
    @Test(dataProvider = "data", description = "添加调整分录_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String month_start, String month_end, String company_id, String version,
                            String elimination, String project_name, String project_code, String project_comment) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //调整分录下搜索
        SearchEntryBatch searchAction = new SearchEntryBatch();
        searchAction.search(case_number, month_start, month_end, company_id, version, elimination, project_name,
                project_code, project_comment);

        boolean a = ElementOperation.isElementPresent(driver, IndexEntryBatch.found);
        Reporter.log(String.valueOf(a));

        if (a) {
            //有搜索结果
            Reporter.log("当前搜索条件下，有调整分录。");
        } else {
            //无搜索结果
            Reporter.log("当前搜索条件下，无调整分录。");
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param month_start
     * @param month_end
     * @param company_id
     * @param version
     * @param elimination
     * @param project_name
     * @param project_code
     * @param project_comment
     */
    @Test(dataProvider = "data", description = "添加调整分录_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String month_start, String month_end, String company_id, String version,
                            String elimination, String project_name, String project_code, String project_comment) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //调整分录下搜索
        SearchEntryBatch searchAction = new SearchEntryBatch();
        searchAction.search(case_number, month_start, month_end, company_id, version, elimination, project_name,
                project_code, project_comment);

        //点击重置搜索
        SearchEntryBatch resetSearchAction = new SearchEntryBatch();
        resetSearchAction.resetSearch(driver);

        //记录重置后结果
        String reset_after_elimination = ElementOperation.getSelectedValue(driver, IndexEntryBatch.elimination);
        String reset_after_project_name = driver.findElement(IndexEntryBatch.project_name).getText();
        String reset_after_project_code = driver.findElement(IndexEntryBatch.project_code).getText();
        String reset_after_project_comment = driver.findElement(IndexEntryBatch.project_comment).getText();

        Assert.assertEquals(reset_after_elimination, "是否参与抵消");
        Assert.assertEquals(reset_after_project_name, "");
        Assert.assertEquals(reset_after_project_code, "");
        Assert.assertEquals(reset_after_project_comment, "");
        Reporter.log("重置正常。");
        Reporter.log("重置后:" + "\n是否参与抵消：" + reset_after_elimination
                + "\n截止请输入需要过滤的项目名称：" + reset_after_project_name
                + "\n请输入需要过滤的科目代码：" + reset_after_project_code
                + "\n请输入需要过滤的调整事项：" + reset_after_project_comment + "\n");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
