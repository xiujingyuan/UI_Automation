package web.actionObjects.Capital.flow.flowAbnormalWatercourse;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.flow.flowAbnormalWatercourse.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/4
 * Time: 下午5:49
 * 资金管理->银行流水管理->异常流水列表->搜索
 */
public class SearchAbnormalWatercourse extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param min_create_time
     * @param max_create_time
     * @param bank_account
     * @param min_trade_date
     * @param max_trade_date
     * @param company
     * @param side_account
     * @param side_account_name
     * @param summary
     * @param expect_result
     */
    public void search(String case_number, String min_create_time, String max_create_time,
                       String bank_account, String min_trade_date, String max_trade_date, String company,
                       String side_account, String side_account_name, String summary, String expect_result) {
        //前往 资金管理->银行流水管理->异常流水列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowAbnormalWatercourse();

        //选择创建开始/结束日期
        ElementOperation.InputClick(driver, Index.min_create_time, min_create_time);
        ElementOperation.InputClick(driver, Index.max_create_time, max_create_time);

        //输入 银行账户
        ElementOperation.editInput(driver, Index.bank_account, bank_account);

        //选择交易开始/结束日期、公司
        ElementOperation.InputClick(driver, Index.min_trade_date, min_trade_date);
        ElementOperation.InputClick(driver, Index.max_trade_date, max_trade_date);
        ElementOperation.selectDropdownValue(driver, Index.company, company);

        //输入 对方账号、对方账户名称、流水摘要
        ElementOperation.editInput(driver, Index.side_account, side_account);
        ElementOperation.editInput(driver, Index.side_account_name, side_account_name);
        ElementOperation.editInput(driver, Index.summary, summary);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, Index.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, Index.reset);
    }


}
