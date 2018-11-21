package web.actionObjects.Capital.flow.bankReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.flow.bankReport.PrepareList;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/7
 * Time: 下午12:00
 * 资金管理->银行流水管理->银行流水相关报表->拨备金核对报表
 * 搜索
 */
public class SearchPrepare extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param trade_date_start
     * @param trade_date_end
     * @param expect_count
     */
    public void search(String case_number, String trade_date_start, String trade_date_end, String expect_count) {
        //前往 资金管理->银行流水管理->银行流水相关报表->拨备金核对报表
        Menu menuAction = new Menu();
        menuAction.gotoFlowPrepareTheoryMoneyList();

        //输入 开始/结束日期
        ElementOperation.InputClick(driver, PrepareList.trade_date_start, trade_date_start);
        ElementOperation.InputClick(driver, PrepareList.trade_date_end, trade_date_end);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, PrepareList.search);
        wait5s();
    }
}
