package testCases.Capital.flow.flowbankaccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.flowBankAccount.AddAccount;
import web.actionObjects.Capital.menu.Menu;
import web.common.*;
import web.pageObjects.Capital.flow.flowBankAccount.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/27
 * Time: 下午7:47
 * 资金管理->银行流水管理->账户管理列表
 * 增加银行账户
 */
public class AddAccountTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/flowbankaccount/",
                "addAccount.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param company_name
     * @param bank_short_name
     * @param account_casher
     * @param account_accountant
     * @param bank_account
     * @param account_name
     * @param account_short_name
     * @param credit_code
     * @param organization_code
     * @param bank_name
     * @param open_account_date
     * @param money_pro
     * @param account_type
     * @param account_status
     * @param is_withdraw
     * @param withdraw_account
     * @param remark
     */
    @Test(dataProvider = "data", description = "账户管理列表_新增银行账户", enabled = true, groups = {"add"})
    public void addTest(String case_number, String company_name, String bank_short_name, String account_casher, String account_accountant,
                        String bank_account, String account_name, String account_short_name, String credit_code,
                        String organization_code, String bank_name, String open_account_date, String money_pro,
                        String account_type, String account_status, String is_withdraw, String withdraw_account,
                        String remark) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //前往 账户管理列表页面
        Menu menuAction = new Menu();
        menuAction.gotoFlowBankAccount();

        //新增账户，输入各项值
        AddAccount addAction = new AddAccount();
        addAction.addAccount(case_number, company_name, bank_short_name, account_casher, account_accountant,
                bank_account, account_name, account_short_name, credit_code, organization_code, bank_name,
                open_account_date, money_pro, account_type, account_status, is_withdraw, withdraw_account, remark);

        Reporter.log("新增成功。");
    }

    @Test(dataProvider = "data", description = "账户管理列表_对比新增信息", enabled = true, groups = {"addConfirm"})
    public void confirmTest(String case_number, String company_name, String bank_short_name, String account_casher, String account_accountant,
                            String search_bank_account, String account_name, String account_short_name, String credit_code,
                            String organization_code, String bank_name, String open_account_date, String money_pro,
                            String account_type, String account_status, String is_withdraw, String withdraw_account,
                            String remark) {
        //前往 账户管理列表页面
        Menu menuAction = new Menu();
        menuAction.gotoFlowBankAccount();

        //选择 前面新增的账户
        ElementOperation.editInput(driver, Index.search_account_id, search_bank_account);
        //点击 搜索
        ElementOperation.buttonClick(driver, Index.button_search);

        //焦点移至新打开的页面
        String title = "账户列表";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //对比
        //新增后读取对应数据
        String[] excel_a = new String[]{
                account_status,
                bank_short_name,
                search_bank_account,
                account_name,
                account_short_name,
                money_pro,
                account_type,
                bank_name,
                open_account_date,
                account_casher,
                account_accountant,
                remark
        };

        //取页面数据
        String[] b = new String[]{
                driver.findElement(Index.text_account_status).getText(),
                driver.findElement(Index.text_bank_short_name).getText(),
                driver.findElement(Index.text_account_id).getText(),
                driver.findElement(Index.text_account_full_name).getText(),
                driver.findElement(Index.text_account_name).getText(),
                driver.findElement(Index.text_money_pro).getText(),
                driver.findElement(Index.text_account_type).getText(),
                driver.findElement(Index.text_bank_name).getText(),
                driver.findElement(Index.text_open_account_date).getText().substring(0, 10),
                driver.findElement(Index.text_account_casher).getText(),
                driver.findElement(Index.text_account_accountant).getText(),
                driver.findElement(Index.text_remark).getText()
        };

        Reporter.log(driver.findElement(Index.text_account_status).getText());
        Reporter.log(driver.findElement(Index.text_bank_short_name).getText());
        Reporter.log(driver.findElement(Index.text_account_id).getText());
        Reporter.log(driver.findElement(Index.text_account_full_name).getText());
        Reporter.log(driver.findElement(Index.text_account_name).getText());
        Reporter.log(driver.findElement(Index.text_money_pro).getText());
        Reporter.log(driver.findElement(Index.text_account_type).getText());
        Reporter.log(driver.findElement(Index.text_bank_name).getText());
        Reporter.log(driver.findElement(Index.text_open_account_date).getText().substring(0, 10));
        Reporter.log(driver.findElement(Index.text_account_casher).getText());
        Reporter.log(driver.findElement(Index.text_account_accountant).getText());
        Reporter.log(driver.findElement(Index.text_remark).getText());

        Assert.assertEquals(excel_a, b);

        Reporter.log("新增数据校验正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
