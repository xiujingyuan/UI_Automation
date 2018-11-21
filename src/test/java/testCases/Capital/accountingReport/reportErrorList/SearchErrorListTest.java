package testCases.Capital.accountingReport.reportErrorList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.reportErrorList.SearchErrorList;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.reportErrorList.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/7
 * Time: 下午3:20
 * 会计核算->月度报表->分析报表->异常统计页
 * 搜索、重置
 */
public class SearchErrorListTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "异常统计页_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //前往 会计核算->月度报表->分析报表->异常统计页
        Menu menuAction = new Menu();
        menuAction.gotoAccountingReportErrorList();

        //检查标题是否正确
        driver.getTitle().contains("报表校验异常统计界面");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/reportErrorList/",
                "searchReportErrorList.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param cate
     * @param expect_count
     * @param version
     */
    @Test(dataProvider = "data", description = "异常统计页_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String cate, String version, String expect_count) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();

        //搜索
        SearchErrorList searchAction = new SearchErrorList();
        searchAction.search(case_number, cate, version, expect_count);

        boolean a = ElementOperation.isElementPresent(driver, Index.count);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, Index.not_found);
        Reporter.log(String.valueOf(b));

        if (a) {
            //搜索结果非0时，检查搜索后结果 是否与期望结果一致
            int number1 = Integer.parseInt(driver.findElement(Index.count).getText());
            //Excel中result值为string，转为int再比较
            int r1 = Integer.parseInt(expect_count);
            Assert.assertEquals(number1, r1);

            Reporter.log(String.valueOf(expect_count));
            Reporter.log("搜索正常");
            Reporter.log("数据总条数：" + driver.findElement(Index.count).getText() + "\n");
        } else if (b) {
            //搜索结果为0
            Assert.assertEquals(driver.findElement(Index.not_found).getText(), "没有找到数据。");
            Reporter.log("没有找到数据。");
        }
        Reporter.log("搜索正常。");
    }

    /**
     *
     * @param case_number
     * @param cate
     * @param version
     * @param expect_count
     */


    @Test(dataProvider = "data", description = "异常统计页_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String cate, String version, String expect_count) {
        //搜索
        SearchErrorList searchAction = new SearchErrorList();
        searchAction.search(case_number, cate, version, expect_count);

        boolean a = ElementOperation.isElementPresent(driver, Index.count);

        if (a) {
            //有搜索结果，记录重置前结果
            int reset_before_number = Integer.parseInt(driver.findElement(Index.count).getText());

            //点击重置搜索
            SearchErrorList resetSearchAction = new SearchErrorList();
            resetSearchAction.resetSearch(driver);

            //记录重置后结果
            int reset_after_number = Integer.parseInt(driver.findElement(Index.count).getText());

            Assert.assertEquals(reset_before_number, reset_after_number);
            Reporter.log("重置前后数据数一致，均为" + driver.findElement(Index.count).getText() + "\n");
        } else {
            //无搜索结果，记录重置前文案
            String reset_before_text = driver.findElement(Index.not_found).getText();

            //点击重置搜索
            SearchErrorList resetSearchAction = new SearchErrorList();
            resetSearchAction.resetSearch(driver);

            //记录重置后文案
            String reset_after_text = driver.findElement(Index.not_found).getText();

            //对比
            Assert.assertEquals(reset_before_text, reset_after_text);
        }
        Reporter.log("重置正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
