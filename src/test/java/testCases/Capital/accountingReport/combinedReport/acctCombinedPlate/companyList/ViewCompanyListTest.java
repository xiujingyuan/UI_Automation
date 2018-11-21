package testCases.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.ViewCompanyList;
import web.common.BaseAction;
import web.common.CloseOtherWindows;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午3:45
 * 会计核算->月度报表->合并报表->口径列表
 * 进入页面 口径公司列表
 */
public class ViewCompanyListTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "口径公司列表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //进入页面口径公司列表
        ViewCompanyList viewAction = new ViewCompanyList();
        viewAction.view();

        //核对 当前页面标题
        driver.getTitle().contains("口径公司列表");
        Reporter.log("当前页面 = 期望页面");

        //log中打印页面标题
        Reporter.log("页面校验完成后，取当前页面标题：" + driver.getTitle());
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
