package testCases.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.SearchCompanyList;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.IndexCompanyList;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午4:06
 * 会计核算->月度报表->合并报表->口径列表->口径公司列表
 * 页面打开、搜索、重置
 */
public class SearchCompanyListTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/combinedReport/acctCombinedPlate/companyList/",
                "searchCompanyList.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param start_date
     * @param end_date
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "口径公司列表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String start_date, String end_date, String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchCompanyList searchAction = new SearchCompanyList();
        searchAction.search(case_number, start_date, end_date, expect_result);

        Reporter.log("请求参数：{ 开始年月：" + start_date + ";截止年月：" + end_date + ";预期结果：" + expect_result + " }");

        boolean a = ElementOperation.isElementPresent(driver, IndexCompanyList.not_found);
        Reporter.log(String.valueOf(a));

        if (a) {
            //无搜索结果
            int expect_num = Integer.parseInt(expect_result);
            Assert.assertEquals(expect_num, 0);
            Reporter.log("当前搜索条件下，无数据。");
        } else {
            //有搜索结果
            int expect_num = Integer.parseInt(expect_result);
            int text_sum = Integer.parseInt(driver.findElement(IndexCompanyList.sum).getText());
            Assert.assertEquals(text_sum, expect_num);
            Reporter.log("当前搜索条件下，有数据。数据总数：" + driver.findElement(IndexCompanyList.sum).getText());
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param start_date
     * @param end_date
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "口径公司列表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String start_date, String end_date, String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchCompanyList searchAction = new SearchCompanyList();
        searchAction.search(case_number, start_date, end_date, expect_result);

        boolean a = ElementOperation.isElementPresent(driver, IndexCompanyList.not_found);
        Reporter.log(String.valueOf(a));

        if (a) {
            //无搜索结果，记录重置前文案
            String reset_before_text = driver.findElement(IndexCompanyList.not_found).getText();
            //点击重置搜索
            SearchCompanyList resetSearchAction = new SearchCompanyList();
            resetSearchAction.resetSearch(driver);
            //记录重置后文案
            String reset_after_text = driver.findElement(IndexCompanyList.not_found).getText();
            //对比
            Assert.assertEquals(reset_before_text, reset_after_text);
            Reporter.log("重置前后文案无变化，均为：" + reset_after_text);
        } else {
            //有搜索结果，记录重置前数据条数
            int reset_before_sum = Integer.parseInt(driver.findElement(IndexCompanyList.sum).getText());
            //点击重置搜索
            SearchCompanyList resetSearchAction = new SearchCompanyList();
            resetSearchAction.resetSearch(driver);
            //记录重置后数据条数
            int reset_after_sum = Integer.parseInt(driver.findElement(IndexCompanyList.sum).getText());
            Assert.assertEquals(reset_before_sum, reset_after_sum);
            Reporter.log("重置前后数据条数无变化，均为：" + reset_after_sum);
        }

        //记录重置后结果
        String reset_after_start_date = driver.findElement(IndexCompanyList.start_date).getText();
        String reset_after_end_date = driver.findElement(IndexCompanyList.end_date).getText();

        Assert.assertEquals(reset_after_start_date, "");
        Assert.assertEquals(reset_after_end_date, "");
        Reporter.log("重置正常。");
        Reporter.log("重置后:" + "\n开始年月：" + reset_after_start_date + "\n截止年月：" + reset_after_end_date + "\n");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
