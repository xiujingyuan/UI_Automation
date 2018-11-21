package testCases.Capital.accountingReport.accountingAccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.accountingAccount.CreateAccountingAccount;
import web.actionObjects.Capital.menu.Menu;
import web.common.*;
import web.pageObjects.Capital.accountingReport.accountingAccount.IndexAccountingAccount;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/10
 * Time: 下午2:24
 * 会计核算->月度报表->配置管理->科目列表
 * 新增科目
 */
public class CreateAccountingAccountTest extends BaseAction {
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
                "addAccountingAccount.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param accounting_account_pid
     * @param accounting_account_name
     * @param accounting_account_code
     * @param accounting_account_memo
     */
    @Test(dataProvider = "data", description = "科目列表_新增科目", enabled = true, groups = {"add"})
    public void addTest(String case_number, String accounting_account_pid, String accounting_account_name,
                        String accounting_account_code, String accounting_account_memo) {
        //前往 会计核算->月度报表->配置管理->科目列表
        Menu menuAction = new Menu();
        menuAction.gotoAccountingAccountIndex();

        //新增操作前，记录数据数
        int number_before = Integer.parseInt(driver.findElement(IndexAccountingAccount.sum).getText());

        //新增，输入各项值
        CreateAccountingAccount addAction = new CreateAccountingAccount();
        addAction.addAccountingAccount(case_number, accounting_account_pid, accounting_account_name,
                accounting_account_code, accounting_account_memo);

        //新增操作后，重新统计数据数
        int number_after = Integer.parseInt(driver.findElement(IndexAccountingAccount.sum).getText());
        //比较数据前后差异数，是否与测试用例中新增成功条数相符
        int number_diff = number_after - number_before;

        Assert.assertEquals(number_diff, 1);
        Reporter.log("新增数据条数校验正常");
    }

    /**
     * @param case_number
     * @param accounting_account_pid
     * @param accounting_account_name
     * @param accounting_account_code
     * @param accounting_account_memo
     */
    @Test(dataProvider = "data", description = "科目列表_对比新增信息", enabled = true, groups = {"addConfirm"})
    public void confirmTest(String case_number, String accounting_account_pid, String accounting_account_name,
                            String accounting_account_code, String accounting_account_memo) {
        //前往 会计核算->月度报表->配置管理->科目列表
        Menu menuAction = new Menu();
        menuAction.gotoAccountingAccountIndex();

        //选择 前面新增的科目名称、科目代码
        ElementOperation.editInput(driver, IndexAccountingAccount.account_name, accounting_account_name);
        ElementOperation.editInput(driver, IndexAccountingAccount.account_code, accounting_account_code);
        //点击 搜索
        ElementOperation.buttonClick(driver, IndexAccountingAccount.search);

        //焦点移至新打开的页面
        String title = "科目列表";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //对比
        //新增后读取对应数据
        String[] excel_a = new String[]{
                accounting_account_pid,
                accounting_account_name,
                accounting_account_code,
                accounting_account_memo
        };

        String[] b = new String[]{
                driver.findElement(IndexAccountingAccount.text_account_pid).getText(),
                driver.findElement(IndexAccountingAccount.text_account_name).getText(),
                driver.findElement(IndexAccountingAccount.text_account_code).getText(),
                driver.findElement(IndexAccountingAccount.text_account_memo).getText(),
        };
        Reporter.log(driver.findElement(IndexAccountingAccount.text_account_pid).getText());
        Reporter.log(driver.findElement(IndexAccountingAccount.text_account_name).getText());
        Reporter.log(driver.findElement(IndexAccountingAccount.text_account_code).getText());
        Reporter.log(driver.findElement(IndexAccountingAccount.text_account_memo).getText());

        Assert.assertEquals(excel_a, b);

        Reporter.log("新增数据正常");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
