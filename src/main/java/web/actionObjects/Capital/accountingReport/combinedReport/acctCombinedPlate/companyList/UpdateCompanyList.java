package web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.ViewCompanyList;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.IndexCompanyList;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.UpdateCompanyListPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午4:27
 * 会计核算->月度报表->合并报表->口径列表->口径公司列表->更新口径公司信息
 */
public class UpdateCompanyList extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    public void update(String case_num, String end_date) {
        //进入 口径公司列表
        ViewCompanyList viewAction = new ViewCompanyList();
        viewAction.view();

        //获取当前窗口句柄
        String parentWindowId = driver.getWindowHandle();

        //点击 更新按钮
        ElementOperation.buttonClick(driver, IndexCompanyList.update_company);

        //焦点移至新打开的页面
        String title = "更新口径公司信息";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //输入 截止生效年月
        ElementOperation.InputClick(driver, UpdateCompanyListPage.end_date, end_date);
        //点击【提交】
        ElementOperation.buttonClick(driver, UpdateCompanyListPage.submit);
        wait5s();
        //关闭页面并切换至口径公司列表页面
        driver.close();
        driver.switchTo().window(parentWindowId);
    }
}
