package testCases.Capital.accountingReport.singleReports.variationAnalysisReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.singleReports.variationAnalysisReport.SearchVariationAnalysisReport;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.singleReports.variationAnalysisReport.IndexVariationAnalysisReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午2:18
 * 会计核算->月度报表->单体报表->单体变动分析表
 * 页面打开、搜索、重置
 */
public class SearchVariationAnalysisReportTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "单体变动分析表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();

        //前往 会计核算->月度报表->单体报表->单体变动分析表
        Menu menuAction = new Menu();
        menuAction.gotoVariationAnalysisReport();

        //检查标题是否正确
        driver.getTitle().contains("全部板块公司单体变动分析表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/singleReports/variationAnalysisReport/",
                "searchVariationAnalysisReport.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param month
     * @param company_id
     */
    @Test(dataProvider = "data", description = "单体变动分析表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String month, String company_id) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchVariationAnalysisReport searchAction = new SearchVariationAnalysisReport();
        searchAction.search(case_number, month, company_id);

        boolean a = ElementOperation.isElementPresent(driver, IndexVariationAnalysisReport.found);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, IndexVariationAnalysisReport.not_found);
        Reporter.log(String.valueOf(b));

        if (a) {
            //有搜索结果
            Reporter.log("搜索正常，报表正常生成。");
        } else if (b) {
            //无搜索结果
            Reporter.log("当前搜索条件下，无报表生成。");
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param month
     * @param company_id
     */
    @Test(dataProvider = "data", description = "单体变动分析表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String month, String company_id) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchVariationAnalysisReport searchAction = new SearchVariationAnalysisReport();
        searchAction.search(case_number, month, company_id);

        boolean a = ElementOperation.isElementPresent(driver, IndexVariationAnalysisReport.found);

        if (a) {
            //有搜索结果，重置前记录文案
            String text_before_found = driver.findElement(IndexVariationAnalysisReport.found).getText();

            //点击重置搜索
            SearchVariationAnalysisReport resetSearchAction = new SearchVariationAnalysisReport();
            resetSearchAction.resetSearch(driver);

            //重置后记录文案
            String text_after_found = driver.findElement(IndexVariationAnalysisReport.found).getText();

            //对比
            Assert.assertEquals(text_before_found, text_after_found);
        } else {
            //无搜索结果，重置前记录文案
            String text_before_not_found = driver.findElement(IndexVariationAnalysisReport.not_found).getText();

            //点击重置搜索
            SearchVariationAnalysisReport resetSearchAction = new SearchVariationAnalysisReport();
            resetSearchAction.resetSearch(driver);

            //重置后记录文案
            String text_after_not_found = driver.findElement(IndexVariationAnalysisReport.not_found).getText();

            //对比
            Assert.assertEquals(text_before_not_found, text_after_not_found);
        }

        //记录重置后搜索条件
        String reset_after_month = driver.findElement(IndexVariationAnalysisReport.month).getText();
        String reset_after_company_id = ElementOperation.getSelectedValue(driver, IndexVariationAnalysisReport.company_id);

        Assert.assertEquals(reset_after_month, "");
        Assert.assertEquals(reset_after_company_id, "萍乡市云智网络科技有限公司");
        Reporter.log("重置正常。");
        Reporter.log("重置后:" + "\n月份：" + reset_after_month
                + "\n公司：" + reset_after_company_id + "\n");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
