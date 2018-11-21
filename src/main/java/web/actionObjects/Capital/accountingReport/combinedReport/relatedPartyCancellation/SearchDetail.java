package web.actionObjects.Capital.accountingReport.combinedReport.relatedPartyCancellation;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.combinedReport.relatedPartyCancellation.DetailRelatedPartyCancellation;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午5:33
 * 会计核算->月度报表->合并报表->关联方来往抵消报表->查看明细
 * 搜索、重置
 */
public class SearchDetail extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param month
     */
    public void search(String case_number, String month, String diameter) {
        //搜索并进入指定的往来明细页面
        ViewDetail view = new ViewDetail();
        view.view(case_number, month);

        //选择 月份
        ElementOperation.InputClick(driver, DetailRelatedPartyCancellation.month, month);
        ElementOperation.selectDropdownValue(driver, DetailRelatedPartyCancellation.diameter, diameter);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, DetailRelatedPartyCancellation.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, DetailRelatedPartyCancellation.reset);
    }
}
