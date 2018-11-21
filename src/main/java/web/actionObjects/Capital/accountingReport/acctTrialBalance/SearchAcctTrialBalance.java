package web.actionObjects.Capital.accountingReport.acctTrialBalance;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.acctTrialBalance.IndexPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 上午11:58
 * 会计核算->月度报表->全部科目余额
 * 搜索
 */
public class SearchAcctTrialBalance extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param min_create_time
     * @param max_create_time
     * @param affiliation_plate
     * @param company_id
     * @param account_level
     * @param accounting_account_code
     * @param acct_trial_balance_name
     * @param expect_result
     */
    public void search(String case_number, String min_create_time, String max_create_time, String affiliation_plate,
                       String company_id, String account_level, String accounting_account_code,
                       String acct_trial_balance_name, String expect_result) {
        //前往 会计核算->月度报表->科目余额
        Menu menuAction = new Menu();
        menuAction.gotoAcctTrialBalanceIndex();

        //选择 开始/结束日期、公司、等级、板块
        ElementOperation.InputClick(driver, IndexPage.min_create_time, min_create_time);
        ElementOperation.InputClick(driver, IndexPage.max_create_time, max_create_time);
        ElementOperation.selectDropdownValue(driver, IndexPage.affiliation_plate, affiliation_plate);
        ElementOperation.selectDropdownValue(driver, IndexPage.company_id, company_id);
        ElementOperation.selectDropdownValue(driver, IndexPage.account_level, account_level);

        //输入 科目代码、科目名称
        ElementOperation.editInput(driver, IndexPage.accounting_account_code, accounting_account_code);
        ElementOperation.editInput(driver, IndexPage.acct_trial_balance_name, acct_trial_balance_name);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexPage.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexPage.reset);
    }
}
