package testCases.Capital.accountingReport.singleReports.capitalDerivationReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.singleReports.capitalDerivationReport.SearchCapitalDerivationReport;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.singleReports.capitalDerivationReport.IndexCapitalDerivationReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午2:41
 * 会计核算->月度报表->单体报表->单体资金推导表
 * 页面打开、搜索、重置
 */
public class SearchCapitalDerivationReportTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "单体资金推导表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 会计核算->月度报表->单体报表->单体资金推导表
        Menu menuAction = new Menu();
        menuAction.gotoCapitalDerivationReport();

        //检查标题是否正确
        driver.getTitle().contains("单体资金推导表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/singleReports/capitalDerivationReport/",
                "searchCapitalDerivationReport.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param month
     * @param company_id
     */
    @Test(dataProvider = "data", description = "单体资金推导表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String month, String company_id) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchCapitalDerivationReport searchAction = new SearchCapitalDerivationReport();
        searchAction.search(case_number, month, company_id);

        boolean a = ElementOperation.isElementPresent(driver, IndexCapitalDerivationReport.found);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, IndexCapitalDerivationReport.not_found);
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
    @Test(dataProvider = "data", description = "单体资金推导表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String month, String company_id) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchCapitalDerivationReport searchAction = new SearchCapitalDerivationReport();
        searchAction.search(case_number, month, company_id);

        boolean a = ElementOperation.isElementPresent(driver, IndexCapitalDerivationReport.found);

        if (a) {
            //有报表生成，重置前记录文案
            String reset_before_text = driver.findElement(IndexCapitalDerivationReport.found).getText();
            //点击重置搜索
            SearchCapitalDerivationReport resetSearchAction = new SearchCapitalDerivationReport();
            resetSearchAction.resetSearch(driver);
            //记录重置后文案
            String reset_after_text = driver.findElement(IndexCapitalDerivationReport.found).getText();
            //对比
            Assert.assertEquals(reset_before_text, reset_after_text);
        } else {
            //无报表生成，重置前记录文案
            String reset_before_text = driver.findElement(IndexCapitalDerivationReport.not_found).getText();
            //点击重置搜索
            SearchCapitalDerivationReport resetSearchAction = new SearchCapitalDerivationReport();
            resetSearchAction.resetSearch(driver);
            //记录重置后文案
            String reset_after_text = driver.findElement(IndexCapitalDerivationReport.not_found).getText();
            //对比
            Assert.assertEquals(reset_before_text, reset_after_text);
        }
        //记录重置后搜索条件
        String reset_after_month = driver.findElement(IndexCapitalDerivationReport.month).getText();
        String reset_after_company_id = ElementOperation.getSelectedValue(driver, IndexCapitalDerivationReport.company_id);

        Assert.assertEquals(reset_after_month, "");
        Assert.assertEquals(reset_after_company_id, "萍乡市云智网络科技有限公司");
        Reporter.log("重置后:" + "\n月份：" + reset_after_month
                + "\n公司：" + reset_after_company_id + "\n");

        Reporter.log("重置正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
