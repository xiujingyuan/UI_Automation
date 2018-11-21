package web.actionObjects.Capital.accountingReport.internalAccounting;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.internalAccounting.IndexInternalAccounting;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午3:41
 * 会计核算->月度报表->分析报表->往来账报表->集团内单位往来账
 * 搜索、重置
 */
public class SearchInternalAccounting extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param min_create_time
     * @param max_create_time
     * @param affiliation_plate
     * @param company_id
     * @param expect_result
     */
    public void search(String case_number, String min_create_time, String max_create_time,
                       String affiliation_plate, String company_id, String expect_result) {
        //前往 会计核算->月度报表->分析报表->往来账报表->集团内单位往来账
        Menu menuAction = new Menu();
        menuAction.gotoInternalAccounting();

        //选择 开始/结束日期、板块、公司
        ElementOperation.InputClick(driver, IndexInternalAccounting.min_create_time, min_create_time);
        ElementOperation.InputClick(driver, IndexInternalAccounting.max_create_time, max_create_time);
        ElementOperation.selectDropdownValue(driver, IndexInternalAccounting.affiliation_plate, affiliation_plate);
        ElementOperation.selectDropdownValue(driver, IndexInternalAccounting.company_id, company_id);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexInternalAccounting.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexInternalAccounting.reset);
    }
}
