package web.actionObjects.Capital.flow.bankReport;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.flow.bankReport.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/7
 * Time: 下午2:23
 * 资金管理->银行流水管理->银行流水相关报表->资金日报报表
 * 搜索
 */
public class SearchPlateBalance extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param affiliation_plate
     * @param min_trade_date
     * @param max_trade_date
     * @param expect_count
     */
    public void search(String case_number, String affiliation_plate, String min_trade_date, String max_trade_date,
                       String expect_count) {
        //前往 资金管理->银行流水管理->银行流水相关报表->资金日报报表
        Menu menuAction = new Menu();
        menuAction.gotoFlowPlateBalanceIndex();

        //选择 板块类型、交易开始/结束日期
        ElementOperation.selectDropdownValue(driver, Index.affiliation_plate, affiliation_plate);
        ElementOperation.InputClick(driver, Index.min_trade_date, min_trade_date);
        ElementOperation.InputClick(driver, Index.max_trade_date, max_trade_date);

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
