package web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.IndexAcctCombinedPlate;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午3:59
 * 会计核算->月度报表->合并报表->口径列表
 * 进入页面 口径公司列表
 */
public class ViewCompanyList extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    public void view() {
        //前往 会计核算->月度报表->合并报表->口径列表
        Menu menuAction = new Menu();
        menuAction.gotoAcctCombinedPlateIndex();

        //点击 口径公司列表
        ElementOperation.buttonClick(driver, IndexAcctCombinedPlate.company_list);

        //焦点移至新打开的页面
        String title = "快牛板块+云智口径公司列表";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
    }
}
