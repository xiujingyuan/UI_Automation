package testCases.Capital.accountingReport.cashTransactionReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.cashTransactionReport.SearchCashTransactionReport;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.cashTransactionReport.IndexCashTransactionReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午4:35
 * 会计核算->月度报表->分析报表->现金流量表
 * 页面打开、搜索、重置
 */
public class SearchCashTransactionReportTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "现金流量表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 会计核算->月度报表->分析报表->现金流量表
        Menu menuAction = new Menu();
        menuAction.gotoCashTransactionReport();

        //检查标题是否正确
        driver.getTitle().contains("现金流量表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/cashTransactionReport/",
                "searchCashTransactionReport.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param month
     * @param company
     */
    @Test(dataProvider = "data", description = "现金流量表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String month, String company) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();

        //搜索
        SearchCashTransactionReport searchAction = new SearchCashTransactionReport();
        searchAction.search(case_number, month, company);

        String a = driver.findElement(IndexCashTransactionReport.income_sum).getText();
        Reporter.log(a);

        if (a != "0.0") {
            //有搜索结果
            Reporter.log("搜索正常，报表正常生成。");
        } else {
            //无搜索结果
            Reporter.log("报表数据未生成。");
            Reporter.log(driver.findElement(IndexCashTransactionReport.income_sum).getText());
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param month
     * @param company
     */
    @Test(dataProvider = "data", description = "现金流量表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String month, String company) {
        //搜索
        SearchCashTransactionReport searchAction = new SearchCashTransactionReport();
        searchAction.search(case_number, month, company);

        //记录重置前结果
        String reset_before_month = driver.findElement(IndexCashTransactionReport.month).getText();
        String reset_before_company = ElementOperation.getSelectedValue(driver, IndexCashTransactionReport.company);

        //点击重置搜索
        SearchCashTransactionReport resetSearchAction = new SearchCashTransactionReport();
        resetSearchAction.resetSearch(driver);

        //记录重置后结果
        String reset_after_month = driver.findElement(IndexCashTransactionReport.month).getText();
        String reset_after_company = ElementOperation.getSelectedValue(driver, IndexCashTransactionReport.company);

        Assert.assertEquals(reset_after_month, "");
        Assert.assertEquals(reset_after_company, "萍乡市云智网络科技有限公司");
        Reporter.log("重置正常。");
        Reporter.log("重置前:" + "\n月份：" + reset_before_month + "\n公司：" + reset_before_company +
                "\n重置后：" + "\n月份：" + reset_after_month + "\n公司：" + reset_after_company);
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
