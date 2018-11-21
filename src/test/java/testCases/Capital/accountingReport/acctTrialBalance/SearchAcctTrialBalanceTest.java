package testCases.Capital.accountingReport.acctTrialBalance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.acctTrialBalance.SearchAcctTrialBalance;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.acctTrialBalance.IndexPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午12:04
 * 会计核算->月度报表->科目余额
 * 页面打开、搜索、重置
 */
public class SearchAcctTrialBalanceTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "科目余额_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 会计核算->月度报表->科目余额
        Menu menuAction = new Menu();
        menuAction.gotoAcctTrialBalanceIndex();

        //检查标题是否正确
        driver.getTitle().contains("科目余额");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/acctTrialBalance/",
                "searchAcctTrialBalance.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param min_create_time
     * @param max_create_time
     * @param company_id
     * @param account_level
     * @param affiliation_plate
     * @param accounting_account_code
     * @param acct_trial_balance_name
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "科目余额_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String min_create_time, String max_create_time,
                            String company_id, String account_level, String affiliation_plate,
                            String accounting_account_code, String acct_trial_balance_name, String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchAcctTrialBalance searchAction = new SearchAcctTrialBalance();
        searchAction.search(case_number, min_create_time, max_create_time, company_id, account_level,
                affiliation_plate, accounting_account_code, acct_trial_balance_name, expect_result);

        boolean a = ElementOperation.isElementPresent(driver, IndexPage.sum);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, IndexPage.not_found);
        Reporter.log(String.valueOf(b));

        if (a) {
            //搜索结果非0时，检查搜索后结果 是否与期望结果一致
            int number1 = Integer.parseInt(driver.findElement(IndexPage.sum).getText());
            //Excel中result值为string，转为int再比较
            int r1 = Integer.parseInt(expect_result);
            Assert.assertEquals(number1, r1);

            Reporter.log(String.valueOf(expect_result));
            Reporter.log("数据总条数：" + driver.findElement(IndexPage.sum).getText() + "\n");
        } else if (b) {
            //搜索结果为0
            Assert.assertEquals(driver.findElement(IndexPage.not_found).getText(), "没有找到数据。");
            Reporter.log("没有找到数据");
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param min_create_time
     * @param max_create_time
     * @param company_id
     * @param account_level
     * @param affiliation_plate
     * @param accounting_account_code
     * @param acct_trial_balance_name
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "科目余额_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String min_create_time, String max_create_time,
                            String company_id, String account_level, String affiliation_plate,
                            String accounting_account_code, String acct_trial_balance_name, String expect_result) {
        //搜索
        SearchAcctTrialBalance searchAction = new SearchAcctTrialBalance();
        searchAction.search(case_number, min_create_time, max_create_time, company_id, account_level,
                affiliation_plate, accounting_account_code, acct_trial_balance_name, expect_result);

        boolean a = ElementOperation.isElementPresent(driver, IndexPage.sum);
        Reporter.log(String.valueOf(a));

        if (a) {
            //有搜索结果，记录重置前结果
            int reset_before_number = Integer.parseInt(driver.findElement(IndexPage.sum).getText());

            //点击重置搜索
            SearchAcctTrialBalance resetSearchAction = new SearchAcctTrialBalance();
            resetSearchAction.resetSearch(driver);

            //记录重置后结果
            int reset_after_number = Integer.parseInt(driver.findElement(IndexPage.sum).getText());

            Assert.assertEquals(reset_before_number, reset_after_number);
            Reporter.log("重置前后数据数一致，均为" + driver.findElement(IndexPage.sum).getText() + "\n");
        } else {
            //无搜索结果，记录重置前结果
            String reset_before_text = driver.findElement(IndexPage.not_found).getText();

            //点击重置搜索
            SearchAcctTrialBalance resetSearchAction = new SearchAcctTrialBalance();
            resetSearchAction.resetSearch(driver);

            //记录重置后结果
            String reset_after_text = driver.findElement(IndexPage.not_found).getText();
            //对比
            Assert.assertEquals(reset_before_text, reset_after_text);
            Reporter.log("没有找到数据。");
        }
        String text_min_create_time = driver.findElement(IndexPage.min_create_time).getText();
        String text_max_create_time = driver.findElement(IndexPage.max_create_time).getText();
        String text_company_id = ElementOperation.getSelectedValue(driver, IndexPage.company_id);
        String text_account_level = ElementOperation.getSelectedValue(driver, IndexPage.account_level);
        String text_affiliation_plate = ElementOperation.getSelectedValue(driver, IndexPage.affiliation_plate);
        String text_accounting_account_code = driver.findElement(IndexPage.accounting_account_code).getText();
        String text_acct_trial_balance_name = driver.findElement(IndexPage.acct_trial_balance_name).getText();

        Assert.assertEquals(text_min_create_time, "");
        Assert.assertEquals(text_max_create_time, "");
        Assert.assertEquals(text_company_id, "请选择公司");
        Assert.assertEquals(text_account_level, "请选择等级");
        Assert.assertEquals(text_affiliation_plate, "请选择板块");
        Assert.assertEquals(text_accounting_account_code, "");
        Assert.assertEquals(text_acct_trial_balance_name, "");
        Reporter.log("重置正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
