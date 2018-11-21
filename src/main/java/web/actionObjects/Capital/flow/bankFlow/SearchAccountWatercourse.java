package web.actionObjects.Capital.flow.bankFlow;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.flow.bankFlow.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/3
 * Time: 下午5:05
 * 资金管理->银行流水管理->流水列表->搜索
 */
public class SearchAccountWatercourse extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param affiliation_type
     * @param company_name
     * @param bank_name
     * @param bank_account
     * @param min_roll_in_money
     * @param max_roll_in_money
     * @param min_roll_out_money
     * @param max_roll_out_money
     * @param min_balance
     * @param max_balance
     * @param min_trade_date
     * @param max_trade_date
     * @param side_account
     * @param side_account_name
     * @param loan_sign
     * @param summary
     * @param stair_classify
     * @param second_classify
     * @param three_classify
     * @param money_pro
     * @param expect_sum_roll_in
     * @param expect_sum_roll_out
     */
    public void search(String case_number, String affiliation_type, String company_name,
                       String bank_name, String bank_account, String min_roll_in_money, String max_roll_in_money,
                       String min_roll_out_money, String max_roll_out_money, String min_balance, String max_balance,
                       String min_trade_date, String max_trade_date, String side_account, String side_account_name,
                       String loan_sign, String summary, String stair_classify, String second_classify, String three_classify,
                       String money_pro, String expect_sum_roll_in, String expect_sum_roll_out) {
        //前往 资金管理->银行流水管理->流水列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowBankFlow();

        //选择 板块类型、公司、银行
        ElementOperation.selectDropdownValue(driver, Index.affiliation_type, affiliation_type);
        ElementOperation.selectDropdownValue(driver, Index.company_name, company_name);
        ElementOperation.selectDropdownValue(driver, Index.bank_name, bank_name);

        //输入 银行账号、最小/最大转入金额、最小/最大转出金额、最大/最小余额
        ElementOperation.editInput(driver, Index.bank_account, bank_account);
        ElementOperation.editInput(driver, Index.min_roll_in_money, min_roll_in_money);
        ElementOperation.editInput(driver, Index.max_roll_in_money, max_roll_in_money);
        ElementOperation.editInput(driver, Index.min_roll_out_money, min_roll_out_money);
        ElementOperation.editInput(driver, Index.max_roll_out_money, max_roll_out_money);
        ElementOperation.editInput(driver, Index.min_balance, min_balance);
        ElementOperation.editInput(driver, Index.max_balance, max_balance);

        //选择 交易开始/结束日期、借贷标识
        ElementOperation.InputClick(driver, Index.min_trade_date, min_trade_date);
        ElementOperation.InputClick(driver, Index.max_trade_date, max_trade_date);
        ElementOperation.selectDropdownValue(driver, Index.loan_sign, loan_sign);

        //输入 对方账号、对方账号名、摘要
        ElementOperation.editInput(driver, Index.side_account, side_account);
        ElementOperation.editInput(driver, Index.side_account_name, side_account_name);
        ElementOperation.editInput(driver, Index.summary, summary);

        //选择 一/二/三级分类、资金性质
        ElementOperation.selectDropdownValue(driver, Index.stair_classify, stair_classify);
        ElementOperation.selectDropdownValue(driver, Index.second_classify, second_classify);
        ElementOperation.selectDropdownValue(driver, Index.three_classify, three_classify);
        ElementOperation.selectDropdownValue(driver, Index.money_pro, money_pro);

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