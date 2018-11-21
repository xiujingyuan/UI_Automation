package web.actionObjects.Capital.accountingReport.combinedReport.relatedPartyCancellation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.accountingReport.combinedReport.relatedPartyCancellation.IndexRelatedPartyCancellation;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/28
 * Time: 上午11:45
 * 会计核算->月度报表->合并报表->关联方来往抵消报表->关联方往来明细
 * 进入页面：关联方往来明细
 */
public class ViewDetail extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param month
     */
    public void view(String case_number, String month) {
        //搜索
        SearchRelatedPartyCancellation searchAction = new SearchRelatedPartyCancellation();
        searchAction.search(case_number, month);

        //log中打印页面标题
        Reporter.log("当前页面：" + driver.getTitle());

        //点击[查看明细]
        ElementOperation.buttonClick(driver, IndexRelatedPartyCancellation.view);

        //焦点移至新打开的页面
        String title = "关联方往来明细";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
    }
}
