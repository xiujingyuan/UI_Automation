package web.actionObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.diameterCombinedCompanyReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.SearchDiameterCombinedReport;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.IndexDiameterCombinedReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 上午11:09
 * 会计核算->月度报表->合并报表->管报税报公司合并报表
 * 进入 查看明细数据
 */
public class ViewCompanyReport extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param month_start
     * @param month_end
     * @param diameter
     * @param version
     */
    public void view(String case_number, String month_start, String month_end, String diameter, String version) {
        //搜索
        SearchDiameterCombinedReport searchAction = new SearchDiameterCombinedReport();
        searchAction.search(case_number, month_start, month_end, diameter, version);

        // 点击【查看明细数据】
        ElementOperation.buttonClick(driver, IndexDiameterCombinedReport.view);

        //焦点移至新打开的页面
        String title = "公司原始数据明细";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
    }
}
