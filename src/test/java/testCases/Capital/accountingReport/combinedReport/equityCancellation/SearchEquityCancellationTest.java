package testCases.Capital.accountingReport.combinedReport.equityCancellation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.equityCancellation.SearchEquityCancellation;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.combinedReport.equityCancellation.IndexEquityCancellation;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午6:58
 * 会计核算->月度报表->合并报表->股权抵消报表
 * 页面打开、搜索、重置
 */
public class SearchEquityCancellationTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "股权抵消报表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 会计核算->月度报表->合并报表->股权抵消报表
        Menu menuAction = new Menu();
        menuAction.gotoEquityCancellation();

        //检查标题是否正确
        driver.getTitle().contains("股权抵消报表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/combinedReport/equityCancellation/",
                "searchEquityCancellation.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param month
     * @param diameter
     */
    @Test(dataProvider = "data", description = "股权抵消报表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String month, String diameter) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //前往 会计核算->月度报表->合并报表->股权抵消报表
        Menu menuAction = new Menu();
        menuAction.gotoEquityCancellation();

        //搜索
        SearchEquityCancellation searchAction = new SearchEquityCancellation();
        searchAction.search(case_number, month, diameter);

        boolean a = ElementOperation.isElementPresent(driver, IndexEquityCancellation.found);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, IndexEquityCancellation.not_found);
        Reporter.log(String.valueOf(b));

        if (a) {
            //有搜索结果
            Reporter.log("搜索正常，有数据。");
        } else if (b) {
            //无搜索结果
            Reporter.log("当前搜索条件下，无数据。");
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param month
     * @param diameter
     */
    @Test(dataProvider = "data", description = "股权抵消报表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String month, String diameter) {
        //前往 会计核算->月度报表->合并报表->股权抵消报表
        Menu menuAction = new Menu();
        menuAction.gotoEquityCancellation();

        //搜索
        SearchEquityCancellation searchAction = new SearchEquityCancellation();
        searchAction.search(case_number, month, diameter);

        //点击重置搜索
        SearchEquityCancellation resetSearchAction = new SearchEquityCancellation();
        resetSearchAction.resetSearch(driver);

        //记录重置后结果
        String reset_after_month = driver.findElement(IndexEquityCancellation.month).getText();
//        String reset_after_diameter = ElementOperation.getSelectedValue(driver, IndexEquityCancellation.diameter);

        Assert.assertEquals(reset_after_month, "");
//        Assert.assertEquals(reset_after_diameter, "");
        Reporter.log("重置正常。");
        Reporter.log("重置后:" + "\n月份：" + reset_after_month
//                + "\n板块：" + reset_after_diameter
                + "\n");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
