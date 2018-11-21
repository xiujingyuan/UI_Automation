package web.actionObjects.Capital.accountingReport.combinedReport.relatedPartyCancellation;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.combinedReport.relatedPartyCancellation.IndexRelatedPartyCancellation;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午4:53
 * 会计核算->月度报表->合并报表->关联方来往抵消报表
 * 搜索、重置
 */
public class SearchRelatedPartyCancellation extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param month
     */
    public void search(String case_number, String month) {
        //前往 会计核算->月度报表->合并报表->关联方来往抵消报表
        Menu menuAction = new Menu();
        menuAction.gotoRelatedPartyCancellation();

        //选择 月份
        ElementOperation.InputClick(driver, IndexRelatedPartyCancellation.month, month);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexRelatedPartyCancellation.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexRelatedPartyCancellation.reset);
    }
}
