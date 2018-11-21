package web.actionObjects.Capital.accountingReport.accountingAccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.accountingReport.accountingAccount.CreateAccountingAccountPage;
import web.pageObjects.Capital.accountingReport.accountingAccount.IndexAccountingAccount;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/10
 * Time: 下午2:18
 * 会计核算->月度报表->配置管理->科目列表
 * 新增科目
 */
public class CreateAccountingAccount extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param accounting_account_pid
     * @param accounting_account_name
     * @param accounting_account_code
     * @param accounting_account_memo
     */
    public void addAccountingAccount(String case_number, String accounting_account_pid,
                                     String accounting_account_name, String accounting_account_code,
                                     String accounting_account_memo) {
        //点击【新增科目】
        ElementOperation.buttonClick(driver, IndexAccountingAccount.create);
        //焦点移至新打开的页面
        String title = "新增科目";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
        //核对 当前页面标题是否为 新增科目
        Assert.assertEquals(driver.getTitle(), "新增科目");
        Reporter.log("当前页面 = 期望页面");
        //选择 父级分类
        ElementOperation.linkClick(driver, CreateAccountingAccountPage.accounting_account_pid);
        ElementOperation.editOnly(driver, CreateAccountingAccountPage.accounting_account_pid_edit, accounting_account_pid);
        ElementOperation.linkClick(driver, CreateAccountingAccountPage.accounting_account_pid);

        //输入 分类名称、编码、说明
        ElementOperation.editInput(driver, CreateAccountingAccountPage.accounting_account_name, accounting_account_name);
        ElementOperation.editInput(driver, CreateAccountingAccountPage.accounting_account_code, accounting_account_code);
        ElementOperation.editInput(driver, CreateAccountingAccountPage.accounting_account_memo, accounting_account_memo);

        //点击【新增】
        ElementOperation.buttonClick(driver, CreateAccountingAccountPage.button_create);
        wait3s();
        Reporter.log("新增操作完成");

        String title02 = "科目列表";
        switchAction.switchToWindow(title02);
    }
}
