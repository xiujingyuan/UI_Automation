package testCases.Capital.accountingReport.singleReports.singleCompanyReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.singleReports.singleCompanyReport.SearchSingleCompanyReport;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.singleReports.singleCompanyReport.IndexSingleCompanyReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午5:14
 * 会计核算->月度报表->单体报表->管报税报公司单体报表
 * 页面打开、搜索、重置
 */
public class SearchSingleCompanyReportTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "管报税报公司单体报表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 会计核算->月度报表->单体报表->管报税报公司单体报表
        Menu menuAction = new Menu();
        menuAction.gotoSingleCompanyReport();

        //检查标题是否正确
        driver.getTitle().contains("管报税报公司单体报表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/singleReports/singleCompanyReport/",
                "searchSingleCompanyReport.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param month_start
     * @param month_end
     * @param company_id
     * @param version
     */
    @Test(dataProvider = "data", description = "管报税报公司单体报表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String month_start, String month_end, String company_id,
                            String version) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchSingleCompanyReport searchAction = new SearchSingleCompanyReport();
        searchAction.search(case_number, month_start, month_end, company_id, version);

        boolean a = ElementOperation.isElementPresent(driver, IndexSingleCompanyReport.found);
        Reporter.log(String.valueOf(a));

        if (a) {
            //有搜索结果
            Reporter.log("搜索正常，报表正常生成。");
        } else {
            //无搜索结果
            Reporter.log("当前搜索条件下，无报表生成。");
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param month_start
     * @param month_end
     * @param company_id
     * @param version
     */
    @Test(dataProvider = "data", description = "管报税报公司单体报表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String month_start, String month_end, String company_id,
                            String version) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchSingleCompanyReport searchAction = new SearchSingleCompanyReport();
        searchAction.search(case_number, month_start, month_end, company_id, version);

        boolean a = ElementOperation.isElementPresent(driver, IndexSingleCompanyReport.found);

        if (a) {
            //有报表生成，重置前记录文案
            String reset_before_text = driver.findElement(IndexSingleCompanyReport.found).getText();
            //点击重置搜索
            SearchSingleCompanyReport resetSearchAction = new SearchSingleCompanyReport();
            resetSearchAction.resetSearch(driver);
            //记录重置后文案
            String reset_after_text = driver.findElement(IndexSingleCompanyReport.found).getText();
            //对比
            Assert.assertEquals(reset_before_text, reset_after_text);
        } else {
            //无报表生成，点击重置搜索
            SearchSingleCompanyReport resetSearchAction = new SearchSingleCompanyReport();
            resetSearchAction.resetSearch(driver);
        }
        //记录重置后结果
        String reset_after_month_start = driver.findElement(IndexSingleCompanyReport.month_start).getText();
        String reset_after_month_end = driver.findElement(IndexSingleCompanyReport.month_end).getText();
        String reset_after_company_id = ElementOperation.getSelectedValue(driver, IndexSingleCompanyReport.company_id);

        //重置后的选项值与预期，对比
        Assert.assertEquals(reset_after_month_start, "");
        Assert.assertEquals(reset_after_month_end, "");
        Assert.assertEquals(reset_after_company_id, "萍乡市云智网络科技有限公司");

        Reporter.log("重置正常。");
        Reporter.log("重置后:" + "\n开始月份：" + reset_after_month_start
                + "\n截止月份：" + reset_after_month_end
                + "\n公司：" + reset_after_company_id
                + "\n");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}