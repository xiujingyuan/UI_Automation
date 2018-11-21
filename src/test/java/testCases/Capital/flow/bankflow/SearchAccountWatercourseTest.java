package testCases.Capital.flow.bankflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.bankFlow.SearchAccountWatercourse;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ExcelOperation;
import web.pageObjects.Capital.flow.bankFlow.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/3
 * Time: 下午6:42
 * 页面：资金管理->银行流水管理->流水列表
 * 检查页面正常打开
 * 按钮：搜索，重置搜索条件
 */
public class SearchAccountWatercourseTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "流水列表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 资金管理->银行流水管理->流水列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowBankFlow();

        //检查是否成功进入页面
        driver.getTitle().contains("流水列表");

        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
        logger.info("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/bankflow/",
                "searchAccountWatercourse.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param affiliation_type
     * @param company_name
     * @param bank_name
     * @param bank_account
     * @param min_roll_in_money
     * @param max_roll_in_money
     * @param min_roll_out_money
     * @param max_roll_out_money
     * @param min_balance
     * @param max_balance
     * @param min_trade_date
     * @param max_trade_date
     * @param side_account
     * @param side_account_name
     * @param loan_sign
     * @param summary
     * @param stair_classify
     * @param second_classify
     * @param three_classify
     * @param money_pro
     * @param expect_sum_roll_in
     * @param expect_sum_roll_out
     */
    @Test(dataProvider = "data", description = "流水列表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String affiliation_type, String company_name,
                            String bank_name, String bank_account, String min_roll_in_money, String max_roll_in_money,
                            String min_roll_out_money, String max_roll_out_money, String min_balance, String max_balance,
                            String min_trade_date, String max_trade_date, String side_account, String side_account_name,
                            String loan_sign, String summary, String stair_classify, String second_classify, String three_classify,
                            String money_pro, String expect_sum_roll_in, String expect_sum_roll_out) {
        //搜索
        SearchAccountWatercourse searchAction = new SearchAccountWatercourse();
        searchAction.search(case_number, affiliation_type, company_name, bank_name, bank_account,
                min_roll_in_money, max_roll_in_money, min_roll_out_money, max_roll_out_money, min_balance,
                max_balance, min_trade_date, max_trade_date, side_account, side_account_name, loan_sign, summary,
                stair_classify, second_classify, three_classify, money_pro, expect_sum_roll_in, expect_sum_roll_out);

        //检查搜索后结果 是否与期望结果一致
        String cnyString = driver.findElement(Index.sum_roll_in).getText();
        String usdString = driver.findElement(Index.sum_roll_out).getText();
        //Excel中result值与实际搜索对比
        Assert.assertEquals(cnyString, expect_sum_roll_in);
        Assert.assertEquals(usdString, expect_sum_roll_out);

        Reporter.log("搜索正常。");
        Reporter.log("转入总额：" + cnyString + "\n" + "转出总额：" + usdString + "\n");

        logger.info("搜索正常。");
        logger.info("转入总额：" + cnyString + "\n" + "转出总额：" + usdString + "\n");
    }

    /**
     * @param case_number
     * @param affiliation_type
     * @param company_name
     * @param bank_name
     * @param bank_account
     * @param min_roll_in_money
     * @param max_roll_in_money
     * @param min_roll_out_money
     * @param max_roll_out_money
     * @param min_balance
     * @param max_balance
     * @param min_trade_date
     * @param max_trade_date
     * @param side_account
     * @param side_account_name
     * @param loan_sign
     * @param summary
     * @param stair_classify
     * @param second_classify
     * @param three_classify
     * @param money_pro
     * @param expect_sum_roll_in
     * @param expect_sum_roll_out
     */
    @Test(dataProvider = "data", description = "流水列表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String affiliation_type, String company_name,
                            String bank_name, String bank_account, String min_roll_in_money, String max_roll_in_money,
                            String min_roll_out_money, String max_roll_out_money, String min_balance, String max_balance,
                            String min_trade_date, String max_trade_date, String side_account, String side_account_name,
                            String loan_sign, String summary, String stair_classify, String second_classify, String three_classify,
                            String money_pro, String expect_sum_roll_in, String expect_sum_roll_out) {
        //搜索
        SearchAccountWatercourse searchAction = new SearchAccountWatercourse();
        searchAction.search(case_number, affiliation_type, company_name, bank_name, bank_account,
                min_roll_in_money, max_roll_in_money, min_roll_out_money, max_roll_out_money, min_balance,
                max_balance, min_trade_date, max_trade_date, side_account, side_account_name, loan_sign, summary,
                stair_classify, second_classify, three_classify, money_pro, expect_sum_roll_in, expect_sum_roll_out);

        //记录重置前结果
        String reset_before_roll_in = driver.findElement(Index.sum_roll_in).getText();
        String reset_before_roll_out = driver.findElement(Index.sum_roll_out).getText();

        //点击重置搜索
        SearchAccountWatercourse resetSearchAction = new SearchAccountWatercourse();
        resetSearchAction.resetSearch(driver);

        //记录重置后结果
        String reset_after_roll_in = driver.findElement(Index.sum_roll_in).getText();
        String reset_after_roll_out = driver.findElement(Index.sum_roll_out).getText();

        Assert.assertEquals(reset_before_roll_in, reset_after_roll_in);
        Assert.assertEquals(reset_before_roll_out, reset_after_roll_out);
        Reporter.log("重置正常");
        Reporter.log("重置后：\n转入总额：" + reset_after_roll_in + "\n"
                + "转出总额：" + reset_after_roll_out);
    }

    @AfterClass
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
        logger.info("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
