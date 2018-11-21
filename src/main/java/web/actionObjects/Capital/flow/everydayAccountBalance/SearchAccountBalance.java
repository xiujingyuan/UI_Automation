package web.actionObjects.Capital.flow.everydayAccountBalance;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.flow.everydayAccountBalance.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/31
 * Time: 下午5:52
 * 资金管理->银行流水管理->账户余额列表->搜索
 */
public class SearchAccountBalance extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param account_id
     * @param company_short
     * @param affiliation_plate
     * @param min_trade_date
     * @param max_trade_date
     * @param error_status
     * @param money_type
     * @param expect_first_balance_result
     * @param expect_end_balance_result
     */
    public void search(String case_number, String account_id, String company_short, String affiliation_plate,
                       String min_trade_date, String max_trade_date, String error_status, String money_type,
                       String expect_first_balance_result, String expect_end_balance_result) {
        //前往 账户余额列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowEverydayAccountBalanceIndex();

        //输入 银行账号、公司名
        ElementOperation.editInput(driver, Index.account_balance_account_id, account_id);
        ElementOperation.editInput(driver, Index.account_balance_company_short, company_short);
        //选择 板块类型、交易开始日期、交易结束日期
        ElementOperation.selectDropdownValue(driver, Index.account_balance_affiliation_plate, affiliation_plate);
        ElementOperation.InputClick(driver, Index.min_trade_date, min_trade_date);
        ElementOperation.InputClick(driver, Index.max_trade_date, max_trade_date);
        //选择 数据状态、资金类型
        ElementOperation.selectDropdownValue(driver, Index.account_balance_error_status, error_status);
        ElementOperation.selectDropdownValue(driver, Index.account_balance_money_type, money_type);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, Index.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, Index.reset_search);
    }


}
