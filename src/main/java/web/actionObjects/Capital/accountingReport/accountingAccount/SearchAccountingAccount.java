package web.actionObjects.Capital.accountingReport.accountingAccount;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.accountingAccount.IndexAccountingAccount;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/10
 * Time: 上午11:57
 * 会计核算->月度报表->配置管理->科目列表
 * 搜索、重置
 */
public class SearchAccountingAccount extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param account_name
     * @param account_code
     * @param account_level
     * @param expect_result
     */
    public void search(String case_number, String account_name, String account_code,
                       String account_level, String expect_result) {
        //前往 会计核算->月度报表->配置管理->科目列表
        Menu menuAction = new Menu();
        menuAction.gotoAccountingAccountIndex();

        //输入 科目名称、科目代码，选择等级
        ElementOperation.editInput(driver, IndexAccountingAccount.account_name, account_name);
        ElementOperation.editInput(driver, IndexAccountingAccount.account_code, account_code);
        ElementOperation.selectDropdownValue(driver, IndexAccountingAccount.account_level, account_level);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexAccountingAccount.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexAccountingAccount.reset);
    }
}
