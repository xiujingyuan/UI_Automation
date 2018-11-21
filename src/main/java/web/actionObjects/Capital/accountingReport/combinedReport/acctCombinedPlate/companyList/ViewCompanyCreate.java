package web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.ViewCompanyList;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.IndexCompanyList;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午5:05
 * 会计核算->月度报表->合并报表->口径列表->口径公司列表
 * 进入页面 新增口径公司
 */
public class ViewCompanyCreate extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    public void view() {
        //进入 口径公司列表
        ViewCompanyList viewAction = new ViewCompanyList();
        viewAction.view();

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);
        // 点击 新增口径公司
        ElementOperation.buttonClick(driver, IndexCompanyList.create_company);

        //焦点移至新打开的页面
        String title = "新增口径公司";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
    }
}
