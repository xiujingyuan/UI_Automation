package web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.ViewCompanyList;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.IndexCompanyList;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午3:51
 * 会计核算->月度报表->合并报表->口径列表->口径公司列表
 * 搜索、重置
 */
public class SearchCompanyList extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param start_date
     * @param end_date
     * @param expect_result
     */
    public void search(String case_number, String start_date, String end_date, String expect_result) {
        //进入 口径公司列表
        ViewCompanyList viewAction = new ViewCompanyList();
        viewAction.view();

        //选择 起止年月
        ElementOperation.InputClick(driver, IndexCompanyList.start_date, start_date);
        ElementOperation.InputClick(driver, IndexCompanyList.end_date, end_date);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexCompanyList.search);
        wait5s();
    }

    //重置搜索条件
    public static void resetSearch(WebDriver driver) {
        ElementOperation.buttonClick(driver, IndexCompanyList.reset);
    }
}
