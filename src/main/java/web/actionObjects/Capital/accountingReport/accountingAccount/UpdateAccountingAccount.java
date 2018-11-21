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
import web.pageObjects.Capital.accountingReport.accountingAccount.UpdateAccountingAccountPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/10
 * Time: 下午2:57
 * 会计核算->月度报表->配置管理->科目列表
 * 更新科目
 */
public class UpdateAccountingAccount extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param search_account_name
     * @param search_account_code
     * @param accounting_account_pid
     * @param accounting_account_name
     * @param accounting_account_code
     * @param accounting_account_memo
     */
    public void updateAccountingAccount(String case_number, String search_account_name, String search_account_code,
                                        String accounting_account_pid, String accounting_account_name, String accounting_account_code,
                                        String accounting_account_memo) {
        //搜索待更新科目
        ElementOperation.editInput(driver, IndexAccountingAccount.account_name, search_account_name);
        ElementOperation.editInput(driver, IndexAccountingAccount.account_code, search_account_code);
        ElementOperation.buttonClick(driver, IndexAccountingAccount.search);
        wait3s();

        //点击【更新】
        ElementOperation.buttonClick(driver, IndexAccountingAccount.update);

        //焦点移至新打开的页面
        String title = "更新科目";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //核对 当前页面标题是否为 更新科目
        Assert.assertEquals(driver.getTitle(), "更新科目");
        Reporter.log("当前页面 = 期望页面");
        //选择 父级分类
        ElementOperation.linkClick(driver, CreateAccountingAccountPage.accounting_account_pid);
        ElementOperation.editOnly(driver, CreateAccountingAccountPage.accounting_account_pid_edit, accounting_account_pid);
        ElementOperation.linkClick(driver, CreateAccountingAccountPage.accounting_account_pid);

        //输入 分类名称、编码、说明
        ElementOperation.editInput(driver, CreateAccountingAccountPage.accounting_account_name, accounting_account_name);
        ElementOperation.editInput(driver, CreateAccountingAccountPage.accounting_account_code, accounting_account_code);
        ElementOperation.editInput(driver, CreateAccountingAccountPage.accounting_account_memo, accounting_account_memo);

        //点击【更新】
        ElementOperation.buttonClick(driver, UpdateAccountingAccountPage.button_update);
        wait3s();
        Reporter.log("更新操作完成");

        //焦点移至新打开的页面
        String title02 = "科目列表";
        switchAction.switchToWindow(title02);
    }
}
