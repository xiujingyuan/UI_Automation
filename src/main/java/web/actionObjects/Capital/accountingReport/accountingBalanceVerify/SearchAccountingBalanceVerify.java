package web.actionObjects.Capital.accountingReport.accountingBalanceVerify;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.accountingBalanceVerify.IndexVerify;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午2:59
 * 会计核算->月度报表->分析报表->往来账报表->公司余额交叉验证表
 * 搜索、重置
 */
public class SearchAccountingBalanceVerify extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param create_time
     * @param company_id
     * @param is_hide
     * @param affiliation_plate
     * @param expect_result
     */
    public void search(String case_number, String create_time, String company_id,
                       String is_hide, String affiliation_plate, String expect_result) {
        //前往 会计核算->月度报表->分析报表->往来账报表->公司余额交叉验证表
        Menu menuAction = new Menu();
        menuAction.gotoAccountingBalanceVerify();

        //选择 开始/结束日期、公司、等级、板块
        ElementOperation.InputClick(driver, IndexVerify.create_time, create_time);
        ElementOperation.selectDropdownValue(driver, IndexVerify.company_id, company_id);
        ElementOperation.selectDropdownValue(driver, IndexVerify.is_hide, is_hide);
        ElementOperation.selectDropdownValue(driver, IndexVerify.affiliation_plate, affiliation_plate);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexVerify.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexVerify.reset);
    }
}
