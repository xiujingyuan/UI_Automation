package testCases.Capital.flow.flowbankaccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
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
 * Time: 下午5:56
 * 页面：资金管理->银行流水管理->账户管理列表
 * 按钮：导入流水，查看流水；操作：查看账户
 */
public class ViewAccountTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(description = "账户管理列表_导入流水", enabled = true, groups = {"view"})
    public void viewImport() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //前往 账户管理列表页面
        Menu menuAction = new Menu();
        menuAction.gotoFlowBankAccount();

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        //点击【查看流水】
        ElementOperation.buttonClick(driver, Index.button_import);
        //log中打印页面标题
        Reporter.log("当前页面：" + driver.getTitle());
        //焦点移至新打开的页面
        String title = "导入银行流水";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
        //核对 当前页面标题是否为 全部板块流水列表
        Assert.assertEquals(driver.getTitle(), "导入银行流水：");
        Reporter.log("当前页面 = 期望页面");
        //关闭当前页面
        driver.close();
        driver.switchTo().window(parentWindowId);
        wait1s();
        //log中打印页面标题
        Reporter.log("页面校验完成后，取当前页面标题：" + driver.getTitle());
    }

    @Test(description = "账户管理列表_查看流水", enabled = true, groups = {"view"})
    public void viewBankFlow() {
        //前往 账户管理列表页面
        Menu menuAction = new Menu();
        menuAction.gotoFlowBankAccount();

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        //点击【查看流水】
        ElementOperation.buttonClick(driver, Index.button_view_flow);
        //log中打印页面标题
        Reporter.log("当前页面：" + driver.getTitle());
        //焦点移至新打开的页面
        String title = "银行流水列表";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
        //核对 当前页面标题是否为 全部板块流水列表
        Assert.assertEquals(driver.getTitle(), "银行流水列表");
        Reporter.log("当前页面 = 期望页面");
        //关闭当前页面
        driver.close();
        driver.switchTo().window(parentWindowId);
        wait1s();
        //log中打印页面标题
        Reporter.log("页面校验完成后，取当前页面标题：" + driver.getTitle());
    }

    @Test(description = "账户管理列表_查看账户", enabled = true, groups = {"view"})
    public void viewAccount() {
        //前往 账户管理列表页面
        Menu menuAction = new Menu();
        menuAction.gotoFlowBankAccount();

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        //点击【查看账户】
        ElementOperation.buttonClick(driver, Index.button_view_bank);
        //log中打印页面标题
        Reporter.log("当前页面：" + driver.getTitle());
        //焦点移至新打开的页面
        String title = "查看账户";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
        //核对 当前页面标题是否为 全部板块流水列表
        Assert.assertEquals(driver.getTitle(), "查看账户");
        Reporter.log("当前页面 = 期望页面");
        //关闭当前页面
        driver.close();
        driver.switchTo().window(parentWindowId);
        wait1s();
        //log中打印页面标题
        Reporter.log("页面校验完成后，取当前页面标题：" + driver.getTitle());
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        System.out.println('\n' + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + '\n' + "###### 测试执行完成 ######");
    }
}
