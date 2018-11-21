package web.actionObjects.Capital.accountingReport.A1A2A3Report;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.A1A2A3Report.IndexA1A2A3Report;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午3:01
 * 会计核算->月度报表->单体报表->单体A1A2A3报表
 * 搜索、重置
 */
public class SearchA1A2A3Report extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param month
     * @param company_id
     */
    public void search(String case_number, String month, String company_id) {
        //前往 会计核算->月度报表->单体报表->单体A1A2A3报表
        Menu menuAction = new Menu();
        menuAction.gotoA1A2A3Report();

        //选择 月份、公司
        ElementOperation.InputClick(driver, IndexA1A2A3Report.month, month);
        ElementOperation.selectDropdownValue(driver, IndexA1A2A3Report.company_id, company_id);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexA1A2A3Report.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexA1A2A3Report.reset);
    }
}
