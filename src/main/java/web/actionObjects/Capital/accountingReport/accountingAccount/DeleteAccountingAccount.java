package web.actionObjects.Capital.accountingReport.accountingAccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.accountingAccount.IndexAccountingAccount;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/10
 * Time: 下午3:28
 * 会计核算->月度报表->配置管理->科目列表
 * 删除科目
 */
public class DeleteAccountingAccount extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param account_name
     * @param account_code
     */
    public void delete(String case_number, String account_name, String account_code) {
        //搜索 需要删除的科目
        ElementOperation.editInput(driver, IndexAccountingAccount.account_name, account_name);
        ElementOperation.editInput(driver, IndexAccountingAccount.account_code, account_code);
        ElementOperation.buttonClick(driver, IndexAccountingAccount.search);
        wait3s();

        //log中打印页面标题
        Reporter.log("当前页面：" + driver.getTitle());

        //确认删除
        ElementOperation.buttonClick(driver, IndexAccountingAccount.delete);
        String alt_text = "您确定要删除此项吗？";
        ElementOperation.closeAlertAndGetItsText(driver, alt_text);
    }
}
