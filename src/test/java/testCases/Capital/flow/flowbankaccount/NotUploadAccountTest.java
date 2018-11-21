package testCases.Capital.flow.flowbankaccount;

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
import web.pageObjects.Capital.flow.flowBankAccount.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/22
 * Time: 下午7:13
 * 页面：资金管理->银行流水管理->账户管理列表
 * 按钮：昨日未上传账户
 */

public class NotUploadAccountTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeTest
    public void beforeTest() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
    }

    public void test() {
    }

    @Test(description = "账户管理列表_昨日未上传账户", enabled = true, groups = {"view"})
    public void viewNotUploadAccount() {
        //前往 账户管理列表页面
        Menu menuAction = new Menu();
        menuAction.gotoFlowBankAccount();

        Reporter.log("当前页面：" + driver.getTitle());

        //点击【昨日未上传账户】
        ElementOperation.buttonClick(driver, Index.button_not_uplode);
        //log中打印页面标题
        Reporter.log("新打开页面：" + driver.getTitle());
        //焦点移至新打开的页面
        String title = "昨日未上传流水账户列表";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
        //核对 当前页面标题是否为 昨日未上传流水账户列表
        Assert.assertEquals(driver.getTitle(), "昨日未上传流水账户列表");
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