package testCases.Capital.accountingReport.accountingAccount;

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
import web.pageObjects.Capital.accountingReport.accountingAccount.IndexAccountingAccount;
import web.pageObjects.Capital.accountingReport.accountingAccount.ViewAccountingAccount;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/10
 * Time: 下午2:47
 * 会计核算->月度报表->配置管理->科目列表
 * 查看
 */
public class ViewAccountingAccountTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeTest
    public void beforeTest() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
    }

    public void test() {
    }

    @Test(description = "科目列表_查看科目", enabled = true, groups = {"view"})
    public void viewClassify() {
        //前往 会计核算->月度报表->配置管理->科目列表
        Menu menuAction = new Menu();
        menuAction.gotoAccountingAccountIndex();

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();

        //点击【查看】
        ElementOperation.buttonClick(driver, IndexAccountingAccount.view);

        //log中打印页面标题
        Reporter.log("当前页面：" + driver.getTitle());

        //焦点移至新打开的页面
        String title = "查看科目信息";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //核对 当前页面标题是否为 查看科目信息
        Assert.assertEquals(driver.getTitle(), "查看科目信息");
        Reporter.log("当前页面 = 期望页面");

        //检查是否存在按钮 更新、删除，并检查是否可用
        ElementOperation.isElementPresent(driver, ViewAccountingAccount.update);
        driver.findElement(ViewAccountingAccount.update).isEnabled();
        ElementOperation.isElementPresent(driver, ViewAccountingAccount.delete);
        driver.findElement(ViewAccountingAccount.delete).isEnabled();

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
