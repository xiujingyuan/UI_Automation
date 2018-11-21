package testCases.Capital.accountingReport.accountingAccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.accountingAccount.SearchAccountingAccount;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.accountingAccount.IndexAccountingAccount;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/10
 * Time: 下午12:02
 * 会计核算->月度报表->配置管理->科目列表
 * 搜索、重置搜索
 */
public class SearchAccountingAccountTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "科目列表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 会计核算->月度报表->配置管理->科目列表
        Menu menuAction = new Menu();
        menuAction.gotoAccountingAccountIndex();

        //检查标题是否正确
        driver.getTitle().contains("科目列表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/accountingAccount/",
                "searchAccountingAccount.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param account_name
     * @param account_code
     * @param account_level
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "科目列表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String account_name, String account_code, String account_level,
                            String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchAccountingAccount searchAction = new SearchAccountingAccount();
        searchAction.search(case_number, account_name, account_code, account_level, expect_result);

        boolean a = ElementOperation.isElementPresent(driver, IndexAccountingAccount.sum);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, IndexAccountingAccount.not_found);
        Reporter.log(String.valueOf(b));

        if (a) {
            //搜索结果非0时，检查搜索后结果 是否与期望结果一致
            int number1 = Integer.parseInt(driver.findElement(IndexAccountingAccount.sum).getText());
            //Excel中result值为string，转为int再比较
            int r1 = Integer.parseInt(expect_result);
            Assert.assertEquals(number1, r1);

            Reporter.log(String.valueOf(expect_result));
            Reporter.log("数据总条数：" + driver.findElement(IndexAccountingAccount.sum).getText() + "\n");
        } else if (b) {
            //搜索结果为0
            Assert.assertEquals(driver.findElement(IndexAccountingAccount.not_found).getText(), "没有找到数据。");
            Reporter.log("没有找到数据。");
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param account_name
     * @param account_code
     * @param account_level
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "科目列表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String account_name, String account_code, String account_level,
                            String expect_result) {
        //搜索
        SearchAccountingAccount searchAction = new SearchAccountingAccount();
        searchAction.search(case_number, account_name, account_code, account_level, expect_result);

        boolean a = ElementOperation.isElementPresent(driver, IndexAccountingAccount.sum);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, IndexAccountingAccount.not_found);
        Reporter.log(String.valueOf(b));

        if (a) {
            //搜索结果非0时，记录重置前结果
            int reset_before_number = Integer.parseInt(driver.findElement(IndexAccountingAccount.sum).getText());

            //点击重置搜索
            SearchAccountingAccount resetSearchAction = new SearchAccountingAccount();
            resetSearchAction.resetSearch(driver);

            //记录重置后结果
            int reset_after_number = Integer.parseInt(driver.findElement(IndexAccountingAccount.sum).getText());

            //数据条数
            Assert.assertEquals(reset_before_number, reset_after_number);

            Reporter.log("数据总条数：" + driver.findElement(IndexAccountingAccount.sum).getText() + "\n");
        } else if (b) {
            //搜索结果为0，记录搜索结果
            String reset_before_text = driver.findElement(IndexAccountingAccount.not_found).getText();
            //点击重置搜索
            SearchAccountingAccount resetSearchAction = new SearchAccountingAccount();
            resetSearchAction.resetSearch(driver);
            //记录重置后结果
            String reset_after_text = driver.findElement(IndexAccountingAccount.not_found).getText();
            //对比
            Assert.assertEquals(reset_before_text, reset_after_text);
            Reporter.log("没有找到数据。");
        }
        String reset_after_account_name = driver.findElement(IndexAccountingAccount.account_name).getText();
        String reset_after_account_code = driver.findElement(IndexAccountingAccount.account_code).getText();
        String reset_after_account_level = ElementOperation.getSelectedValue(driver, IndexAccountingAccount.account_level);

        Assert.assertEquals(reset_after_account_name, "");
        Assert.assertEquals(reset_after_account_code, "");
        Assert.assertEquals(reset_after_account_level, "请选择等级");

        Reporter.log("重置正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
