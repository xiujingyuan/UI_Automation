package testCases.Capital.flow.everydayaccountbalance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.everydayAccountBalance.SearchAccountBalance;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.flow.classify.IndexPage;
import web.pageObjects.Capital.flow.everydayAccountBalance.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/3
 * Time: 上午10:45
 * 页面：资金管理->银行流水管理->账户余额列表
 * 检查页面正常打开
 * 按钮：搜索，重置搜索条件
 */
public class SearchAccountBalanceTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "账户余额列表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 账户余额列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowEverydayAccountBalanceIndex();

        //检查是否成功进入页面
        driver.getTitle().contains("全部板块银行账户余额报表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/everydayaccountbalance/",
                "searchAccountBalance.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param account_id
     * @param company_short
     * @param affiliation_plate
     * @param min_trade_date
     * @param max_trade_date
     * @param error_status
     * @param money_type
     * @param expect_first_balance_result
     * @param expect_end_balance_result
     */
    @Test(dataProvider = "data", description = "账户余额列表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String account_id, String company_short,
                            String affiliation_plate, String min_trade_date, String max_trade_date, String error_status,
                            String money_type, String expect_first_balance_result, String expect_end_balance_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchAccountBalance searchAction = new SearchAccountBalance();
        searchAction.search(case_number, account_id, company_short, affiliation_plate, min_trade_date,
                max_trade_date, error_status, money_type, expect_first_balance_result, expect_end_balance_result);

        boolean b = ElementOperation.isElementPresent(driver, IndexPage.unfound);
        Reporter.log(String.valueOf(b));
        //取搜索后余额合计值
        String text_first_balance = driver.findElement(Index.first_balance).getText();
        String text_end_balance = driver.findElement(Index.end_balance).getText();

        if (b) {
            //搜索结果为0
            Assert.assertEquals(driver.findElement(IndexPage.unfound).getText(), "没有找到数据。");
            Assert.assertEquals(text_first_balance, "0.00");
            Assert.assertEquals(text_end_balance, "0.00");
            Reporter.log("没有找到数据。");
        } else {
            //搜索结果条数非0，对比
            Assert.assertEquals(text_first_balance, expect_first_balance_result);
            Assert.assertEquals(text_end_balance, expect_end_balance_result);
            Reporter.log("搜索正常");
            Reporter.log("期初人民币余额合计：" + text_first_balance + "\n" +
                    "期末人民币余额合计：" + text_end_balance + "\n");
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param account_id
     * @param company_short
     * @param affiliation_plate
     * @param min_trade_date
     * @param max_trade_date
     * @param error_status
     * @param money_type
     * @param expect_first_balance_result
     * @param expect_end_balance_result
     */
    @Test(dataProvider = "data", description = "账户余额列表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String account_id, String company_short,
                            String affiliation_plate, String min_trade_date, String max_trade_date, String error_status,
                            String money_type, String expect_first_balance_result, String expect_end_balance_result) {
        //搜索
        SearchAccountBalance searchAction = new SearchAccountBalance();
        searchAction.search(case_number, account_id, company_short, affiliation_plate, min_trade_date,
                max_trade_date, error_status, money_type, expect_first_balance_result, expect_end_balance_result);

        //记录重置前结果
        String reset_before_first = driver.findElement(Index.first_balance).getText();
        String reset_before_end = driver.findElement(Index.end_balance).getText();

        //点击重置搜索
        SearchAccountBalance resetSearchAction = new SearchAccountBalance();
        resetSearchAction.resetSearch(driver);

        //记录重置后结果
        String reset_after_first = driver.findElement(Index.first_balance).getText();
        String reset_after_end = driver.findElement(Index.end_balance).getText();

        String reset_after_account_id = driver.findElement(Index.account_balance_account_id).getText();
        String reset_after_company_short = driver.findElement(Index.account_balance_company_short).getText();
        String reset_after_affiliation_plate = ElementOperation.getSelectedValue(driver, Index.account_balance_affiliation_plate);
        String reset_after_min_trade_date = driver.findElement(Index.min_trade_date).getText();
        String reset_after_max_trade_date = driver.findElement(Index.max_trade_date).getText();
        String reset_after_error_status = ElementOperation.getSelectedValue(driver, Index.account_balance_error_status);
        String reset_after_money_type = ElementOperation.getSelectedValue(driver, Index.account_balance_money_type);
        //判断重置前后的余额是否一致
        Assert.assertEquals(reset_before_first, reset_after_first);
        Assert.assertEquals(reset_before_end, reset_after_end);
        //检查搜索条件是否重置
        Assert.assertEquals(reset_after_account_id, "");
        Assert.assertEquals(reset_after_company_short, "");
        Assert.assertEquals(reset_after_affiliation_plate, "请选择板块");
        Assert.assertEquals(reset_after_min_trade_date, "");
        Assert.assertEquals(reset_after_max_trade_date, "");
        Assert.assertEquals(reset_after_error_status, "数据状态");
        Assert.assertEquals(reset_after_money_type, "资金类型");
        Reporter.log("重置正常。");
        Reporter.log("期初人民币余额合计：" + reset_after_first + "\n" +
                "期末人民币余额合计：" + reset_after_end + "\n");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}