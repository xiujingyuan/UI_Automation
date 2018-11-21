package testCases.Capital.accountingReport.A1A2A3Report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.A1A2A3Report.SearchA1A2A3Report;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.A1A2A3Report.IndexA1A2A3Report;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午3:02
 * 会计核算->月度报表->单体报表->单体A1A2A3报表
 * 页面打开、搜索、重置
 */
public class SearchA1A2A3ReportTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "单体A1A2A3报表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 会计核算->月度报表->单体报表->单体A1A2A3报表
        Menu menuAction = new Menu();
        menuAction.gotoA1A2A3Report();

        //检查标题是否正确
        driver.getTitle().contains("公司单体A1A2A3表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/A1A2A3Report/",
                "searchA1A2A3Report.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param month
     * @param company_id
     */
    @Test(dataProvider = "data", description = "单体A1A2A3报表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String month, String company_id) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchA1A2A3Report searchAction = new SearchA1A2A3Report();
        searchAction.search(case_number, month, company_id);

        boolean a = ElementOperation.isElementPresent(driver, IndexA1A2A3Report.found);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, IndexA1A2A3Report.not_found);
        Reporter.log(String.valueOf(b));

        if (a) {
            //有搜索结果
            Reporter.log("搜索正常，报表正常生成。");
        } else if (b) {
            //无搜索结果
            Reporter.log("当前搜索条件下，无报表生成。");
        }
    }

    /**
     * @param case_number
     * @param month
     * @param company_id
     */
    @Test(dataProvider = "data", description = "单体A1A2A3报表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String month, String company_id) {
        //搜索
        SearchA1A2A3Report searchAction = new SearchA1A2A3Report();
        searchAction.search(case_number, month, company_id);

        boolean m = ElementOperation.isElementPresent(driver, IndexA1A2A3Report.found);
        Reporter.log(String.valueOf(m));
        boolean n = ElementOperation.isElementPresent(driver, IndexA1A2A3Report.not_found);
        Reporter.log(String.valueOf(n));

        if (m) {
            //有搜索结果
            String reset_after_text = driver.findElement(IndexA1A2A3Report.found).getText();
            //点击重置搜索
            SearchA1A2A3Report resetSearchAction = new SearchA1A2A3Report();
            resetSearchAction.resetSearch(driver);
            //对比
            Assert.assertEquals(reset_after_text, "项目名称");
            Reporter.log("搜索结果非空。");
        } else if (n) {
            //无搜索结果
            String reset_after_text = driver.findElement(IndexA1A2A3Report.not_found).getText();
            //点击重置搜索
            SearchA1A2A3Report resetSearchAction = new SearchA1A2A3Report();
            resetSearchAction.resetSearch(driver);
            //对比
            Assert.assertEquals(reset_after_text, "没有找到数据。");
            Reporter.log("没有找到数据。");
        }
        //记录重置后结果
        String reset_after_month = driver.findElement(IndexA1A2A3Report.month).getText();
        String reset_after_company_id = ElementOperation.getSelectedValue(driver, IndexA1A2A3Report.company_id);

        Assert.assertEquals(reset_after_month, "");
        Assert.assertEquals(reset_after_company_id, "萍乡市云智网络科技有限公司");
        Reporter.log("重置正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
