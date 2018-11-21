package testCases.Capital.accountingReport.acctTrialBalance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.accountingReport.acctTrialBalance.IndexPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午2:33
 * 会计核算->月度报表->全部科目余额
 * 导入科目余额 页面进入
 */
public class ViewImportAcctTrialBalanceTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeTest
    public void beforeTest() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
    }

    public void test() {
    }

    @Test(description = "全部科目余额_昨日未上传账户", enabled = true, groups = {"view"})
    public void viewImport() {
        //前往 会计核算->月度报表->全部科目余额
        Menu menuAction = new Menu();
        menuAction.gotoAcctTrialBalanceIndex();

        Reporter.log("当前页面：" + driver.getTitle());

        //点击【导入科目余额】
        ElementOperation.buttonClick(driver, IndexPage.import_button);
        //log中打印页面标题
        Reporter.log("新打开页面：" + driver.getTitle());

        //焦点移至新打开的页面
        String title = "导入科目余额";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //核对 当前页面标题
        Assert.assertEquals(driver.getTitle(), "导入科目余额");
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
