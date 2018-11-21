package testCases.Capital.accountingReport.accountingAccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.accountingAccount.DeleteAccountingAccount;
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
 * Time: 下午4:59
 * 会计核算->月度报表->配置管理->科目列表
 * 删除科目
 */
public class DeleteAccountingAccountTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeTest
    public void beforeTest() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
    }

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/accountingAccount/",
                "deleteAccountingAccount.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param account_name
     * @param account_code
     */
    @Test(dataProvider = "data", description = "科目列表_删除", enabled = true, groups = {"delete"})
    public void deleteAccountingAccount(String case_number, String account_name, String account_code) {
        //前往 会计核算->月度报表->配置管理->科目列表
        Menu menuAction = new Menu();
        menuAction.gotoAccountingAccountIndex();

        //删除操作前，记录数据数
        int number_before = Integer.parseInt(driver.findElement(IndexAccountingAccount.sum).getText());

        //删除
        DeleteAccountingAccount deleteAction = new DeleteAccountingAccount();
        deleteAction.delete(case_number, account_name, account_code);
        //删除操作后，记录数据数
        int number_after = Integer.parseInt(driver.findElement(IndexAccountingAccount.sum).getText());
        //比较数据前后差异数
        int number_diff = number_before - number_after;
        //Excel中result值为string，转为int再比较
        Assert.assertEquals(number_diff, 1);
        Reporter.log("删除数据条数校验正常。");
    }

    /**
     * @param case_number
     * @param account_name
     * @param account_code
     */
    @Test(dataProvider = "data", description = "科目列表_校验是否删除信息", enabled = true, groups = {"deleteConfirm"})
    public void confirmTest(String case_number, String account_name, String account_code) {
        //搜索 已删除的科目
        ElementOperation.editInput(driver, IndexAccountingAccount.account_name, account_name);
        ElementOperation.editInput(driver, IndexAccountingAccount.account_code, account_code);
        ElementOperation.buttonClick(driver, IndexAccountingAccount.search);
        wait3s();
        //验证是否无数据
        ElementOperation.waitForElementPresent(driver, IndexAccountingAccount.not_found);
        Reporter.log("科目删除成功。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
