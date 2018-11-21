package web.actionObjects.Capital.flow.bankFlow;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.flow.bankFlow.DiffPlateReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/4
 * Time: 下午4:35
 * 资金管理->银行流水管理->板块流水列表->搜索
 */
public class SearchDiffPlateReport extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param date_start
     * @param date_end
     * @param plate
     * @param result
     */
    public void search(String case_number, String date_start, String date_end, String plate, String result) {
        //前往 资金管理->银行流水管理->板块流水列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowDiffPlateReport();

        //选择 入账开始/结束日期、板块
        ElementOperation.InputClick(driver, DiffPlateReport.date_start, date_start);
        ElementOperation.InputClick(driver, DiffPlateReport.date_end, date_end);
        ElementOperation.selectDropdownValue(driver, DiffPlateReport.plate, plate);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, DiffPlateReport.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, DiffPlateReport.reset);
    }


}
