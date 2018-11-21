package testCases.Capital.accountingReport.combinedReport.diameterCombinedReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.SearchDiameterCombinedReport;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.IndexDiameterCombinedReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 上午10:37
 * 会计核算->月度报表->合并报表->管报税报公司合并报表
 * 页面打开、搜索、重置
 */
public class SearchDiameterCombinedReportTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "管报税报公司合并报表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 会计核算->月度报表->合并报表->管报税报公司合并报表
        Menu menuAction = new Menu();
        menuAction.gotoDiameterCombinedReport();

        //检查标题是否正确
        driver.getTitle().contains("管报税报公司合并报表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/combinedReport/diameterCombinedReport/",
                "searchDiameterCombinedReport.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param month_start
     * @param month_end
     * @param diameter
     * @param version
     */
    @Test(dataProvider = "data", description = "管报税报公司合并报表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String month_start, String month_end, String diameter,
                            String version) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchDiameterCombinedReport searchAction = new SearchDiameterCombinedReport();
        searchAction.search(case_number, month_start, month_end, diameter, version);

        boolean a = ElementOperation.isElementPresent(driver, IndexDiameterCombinedReport.found);
        Reporter.log(String.valueOf(a));

        if (a) {
            //有搜索结果
            Reporter.log("搜索正常，有数据。");
        } else {
            //无搜索结果
            Reporter.log("当前搜索条件下，无数据。");
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param month_start
     * @param month_end
     * @param diameter
     * @param version
     */
    @Test(dataProvider = "data", description = "管报税报公司合并报表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String month_start, String month_end, String diameter,
                            String version) {
        //搜索
        SearchDiameterCombinedReport searchAction = new SearchDiameterCombinedReport();
        searchAction.search(case_number, month_start, month_end, diameter, version);

        //点击重置搜索
        SearchDiameterCombinedReport resetSearchAction = new SearchDiameterCombinedReport();
        resetSearchAction.resetSearch(driver);

        //记录重置后结果
        String reset_after_month_start = driver.findElement(IndexDiameterCombinedReport.month_start).getText();
        String reset_after_month_end = driver.findElement(IndexDiameterCombinedReport.month_end).getText();
        //重置后 板块、版本无选中值
//        String reset_after_diameter = ElementOperation.getSelectedValue(driver, IndexDiameterCombinedReport.diameter);
//        String reset_after_version = ElementOperation.getSelectedValue(driver, IndexDiameterCombinedReport.version);

        Assert.assertEquals(reset_after_month_start, "");
        Assert.assertEquals(reset_after_month_end, "");
//        Assert.assertEquals(reset_before_diameter, "");
//        Assert.assertEquals(reset_before_version, "");
        Reporter.log("重置正常");
        Reporter.log("重置后:" + "\n开始月份：" + reset_after_month_start
                + "\n截止月份：" + reset_after_month_end
//                + "\n板块：" + reset_after_diameter
//                + "\n版本：" + reset_after_version
                + "\n");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
