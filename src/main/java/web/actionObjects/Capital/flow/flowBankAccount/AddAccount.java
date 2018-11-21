package web.actionObjects.Capital.flow.flowBankAccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.flow.flowBankAccount.AccountCreatePage;
import web.pageObjects.Capital.flow.flowBankAccount.Index;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/27
 * Time: 下午7:47
 * 资金管理->银行流水管理->账户管理列表->新增账户
 */

public class AddAccount extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
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
    public void addAccount(String case_number, String company_name, String bank_short_name, String account_casher,
                           String account_accountant, String bank_account, String account_name, String account_short_name,
                           String credit_code, String organization_code, String bank_name, String open_account_date,
                           String money_pro, String account_type, String account_status, String is_withdraw,
                           String withdraw_account, String remark) {
        //点击【新增账户】
        ElementOperation.buttonClick(driver, Index.button_create_account);
        //焦点移至新打开的页面
        String title = "添加银行账户";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
        //核对 当前页面标题是否为 添加银行账户
        Assert.assertEquals(driver.getTitle(), "添加银行账户");
        Reporter.log("当前页面 = 期望页面");
        //选择 公司、所属银行
        ElementOperation.selectDropdownValue(driver, AccountCreatePage.company_name, company_name);
        ElementOperation.selectDropdownValue(driver, AccountCreatePage.bank_short_name, bank_short_name);
        //出纳
        ElementOperation.selectDropdownValue(driver, AccountCreatePage.account_casher, account_casher);
        //会计
        ElementOperation.selectDropdownValue(driver, AccountCreatePage.account_accountant, account_accountant);
        //输入 银行账号、账户名、账户简称、社会统一信用代码、机构信用代码、开户行、开户时间
        ElementOperation.editInput(driver, AccountCreatePage.bank_account, bank_account);
        ElementOperation.editInput(driver, AccountCreatePage.account_name, account_name);
        ElementOperation.editInput(driver, AccountCreatePage.account_short_name, account_short_name);
        ElementOperation.editInput(driver, AccountCreatePage.credit_code, credit_code);
        ElementOperation.editInput(driver, AccountCreatePage.organization_code, organization_code);
        ElementOperation.editInput(driver, AccountCreatePage.bank_name, bank_name);
        ElementOperation.InputClick(driver, AccountCreatePage.open_account_date, open_account_date);

        //选择资金性质
        if (money_pro.equals("运营资金")) {
            ElementOperation.radioButtonClick(driver, AccountCreatePage.money_pro_1);
        } else if (money_pro.equals("业务资金")) {
            ElementOperation.radioButtonClick(driver, AccountCreatePage.money_pro_2);
        } else if (money_pro.equals("拨备资金")) {
            ElementOperation.radioButtonClick(driver, AccountCreatePage.money_pro_3);
        }
        //选择账户类型
        ElementOperation.selectDropdownValue(driver, AccountCreatePage.account_type, account_type);
        //选择账户状态、是否作为OA代付账户
        if (account_status.equals("正常")) {
            ElementOperation.radioButtonClick(driver, AccountCreatePage.account_status_1);
        } else if (account_status.equals("已销户")) {
            ElementOperation.radioButtonClick(driver, AccountCreatePage.account_status_2);
        } else if (account_status.equals("暂无用途")) {
            ElementOperation.radioButtonClick(driver, AccountCreatePage.account_status_3);
        }
        //选择 是否作为OA代付账户
        if (is_withdraw.equals("否")) {
            ElementOperation.radioButtonClick(driver, AccountCreatePage.is_withdraw_1);
        } else if (is_withdraw.equals("是")) {
            ElementOperation.radioButtonClick(driver, AccountCreatePage.is_withdraw_2);
        }
        //输入 代付账户名、备注
        ElementOperation.editInput(driver, AccountCreatePage.withdraw_account, withdraw_account);
        ElementOperation.editInput(driver, AccountCreatePage.remark, remark);

        //点击【新增】
        ElementOperation.buttonClick(driver, AccountCreatePage.button_submit);
        Reporter.log("新增操作完成");

        //焦点移至新打开的页面
        Set<String> presentWindowsId = driver.getWindowHandles();
        Iterator<String> present = presentWindowsId.iterator();
        while (present.hasNext()) {
            String wind = present.next();
            wait1s();
            if (driver.switchTo().window(wind).getTitle().contains("账户列表")) {
                driver.switchTo().window(wind);
                Reporter.log("账户新增成功");
            } else if (driver.switchTo().window(wind).getTitle().contains("添加银行账户")) {
                driver.switchTo().window(wind);
                Reporter.log("账户新增失败");
            }
        }
    }
}
