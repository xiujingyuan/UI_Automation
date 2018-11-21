package web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.CreateCompanyListPage;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.IndexCompanyList;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午4:58
 * 会计核算->月度报表->合并报表->口径列表->口径公司列表->新增口径公司
 * 新增
 */
public class CreateCompanyList extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param company_id
     * @param check_status
     * @param start_date
     * @param end_date
     */
    public void create(String case_number, String company_id, String check_status, String start_date, String end_date) {
        //进入页面 新增口径公司
//        ViewCompanyCreate viewAction = new ViewCompanyCreate();
//        viewAction.view();
        ElementOperation.buttonClick(driver, IndexCompanyList.create_company);
        //选择 口径公司
        ElementOperation.editInput(driver, CreateCompanyListPage.company_id, company_id);

        if (check_status.equals("全选")) {
            ElementOperation.buttonClick(driver, CreateCompanyListPage.check_all);
        } else if (check_status.equals("反选")) {
            ElementOperation.buttonClick(driver, CreateCompanyListPage.check_reverse);
        }

        //输入 起止年月
        ElementOperation.InputClick(driver, CreateCompanyListPage.start_date, start_date);
        ElementOperation.InputClick(driver, CreateCompanyListPage.end_date, end_date);

        //点击 提交
        ElementOperation.buttonClick(driver, CreateCompanyListPage.submit);
        wait5s();

        //焦点移至新打开的页面
        String title = "快牛板块+云智口径公司列表";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
        Reporter.log("新增完成。");
    }
}
