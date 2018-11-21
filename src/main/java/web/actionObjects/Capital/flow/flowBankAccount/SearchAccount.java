package web.actionObjects.Capital.flow.flowBankAccount;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.flow.flowBankAccount.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/22
 * Time: 下午5:56
 * 资金管理->银行流水管理->账户管理列表->搜索
 */
public class SearchAccount extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {

    }

    /**
     * @param case_number
     * @param affiliation_plate
     * @param search_company_name
     * @param bank_name
     * @param account_name
     * @param account_id
     * @param account_type
     * @param money_pro
     * @param bank_short_name
     * @param account_full_name
     * @param result
     */
    public void search(String case_number, String affiliation_plate, String search_company_name,
                       String bank_name, String account_name, String account_id, String account_type,
                       String money_pro, String bank_short_name, String account_full_name, String result) {
        //前往 账户管理列表页面
        Menu menuAction = new Menu();
        menuAction.gotoFlowBankAccount();

        //选择 板块、公司
        ElementOperation.selectDropdownValue(driver, Index.search_affiliation_plate, affiliation_plate);
        ElementOperation.selectDropdownValue(driver, Index.search_company_name, search_company_name);
        //输入 开户行、账户简称、银行账户
        ElementOperation.editInput(driver, Index.search_bank_name, bank_name);
        ElementOperation.editInput(driver, Index.search_account_name, account_name);
        ElementOperation.editInput(driver, Index.search_account_id, account_id);
        //选择 账户类型、资金性质、银行
        ElementOperation.selectDropdownValue(driver, Index.search_account_type, account_type);
        ElementOperation.selectDropdownValue(driver, Index.search_money_pro, money_pro);
        ElementOperation.selectDropdownValue(driver, Index.search_bank_short_name, bank_short_name);
        //输入 账户名
        ElementOperation.editInput(driver, Index.search_account_full_name, account_full_name);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, Index.button_search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, Index.button_reset);
    }

}
