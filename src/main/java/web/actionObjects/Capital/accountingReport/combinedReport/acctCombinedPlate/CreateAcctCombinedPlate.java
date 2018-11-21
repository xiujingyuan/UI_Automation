package web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.CreateAcctCombinedPlatePage;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.IndexAcctCombinedPlate;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午3:09
 * 会计核算->月度报表->合并报表->口径列表->创建口径
 */
public class CreateAcctCombinedPlate extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    public void create(String case_number, String acct_combined_plate_name, String plate_included, String diameter_cate,
                       String short_plate_name) {
        //点击 创建口径
        ElementOperation.buttonClick(driver, IndexAcctCombinedPlate.create_acct_combined_plate);

        //焦点移至新打开的页面
        String title = "创建口径";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        ElementOperation.editInput(driver, CreateAcctCombinedPlatePage.acct_combined_plate_name, acct_combined_plate_name);
        ElementOperation.selectDropdownValue(driver, CreateAcctCombinedPlatePage.plate_included, plate_included);
        ElementOperation.editInput(driver, CreateAcctCombinedPlatePage.short_plate_name, short_plate_name);
        ElementOperation.selectDropdownValue(driver, CreateAcctCombinedPlatePage.diameter_cate, diameter_cate);

        //点击 save
        ElementOperation.buttonClick(driver, CreateAcctCombinedPlatePage.save);
        wait5s();

        String title02 = "口径列表";
        switchAction.switchToWindow(title02);
    }
}
