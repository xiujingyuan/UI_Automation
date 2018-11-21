package testCases.Capital.flow.company;

import org.openqa.selenium.Dimension;
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
import web.pageObjects.Capital.flow.company.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/5
 * Time: 下午2:33
 * 资金管理->银行流水管理->公司管理列表
 * 按钮：导入科目余额、查看公司
 */
public class ViewCompanyListTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(description = "公司管理列表_导入科目余额", enabled = true, groups = {"view"})
    public void viewImport() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //前往 资金管理->银行流水管理->公司管理列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowCompanyIndex();

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        //点击【导入科目余额】
        ElementOperation.buttonClick(driver, Index.import_trial);
        //log中打印页面标题
        Reporter.log("当前页面：" + driver.getTitle());

        //焦点移至新打开的页面
        String title = "导入科目余额";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //核对 当前页面标题是否为 导入科目余额
        Assert.assertEquals(driver.getTitle(), "导入科目余额");
        Reporter.log("当前页面 = 期望页面");
        //关闭当前页面
        driver.close();
        driver.switchTo().window(parentWindowId);
        wait1s();
        //log中打印页面标题
        Reporter.log("页面校验完成后，取当前页面标题：" + driver.getTitle());
    }

    @Test(description = "公司管理列表_查看公司", enabled = true, groups = {"view"})
    public void viewCompany() {
        //前往 资金管理->银行流水管理->公司管理列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowCompanyIndex();

        //更改窗口大小，否则定位不到列表右侧的按钮
        driver.manage().window().setSize(new Dimension(1500,1080));

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();

        //点击【查看公司】
        ElementOperation.buttonClick(driver, Index.view);
        //log中打印页面标题
        Reporter.log("当前页面：" + driver.getTitle());

        //焦点移至新打开的页面
        String title = "查看公司";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //核对 当前页面标题是否为 查看公司
        Assert.assertEquals(driver.getTitle(), "查看公司");
        Reporter.log("当前页面 = 期望页面");

        //检查是否存在按钮 更新、删除，并检查是否可用
        ElementOperation.isElementPresent(driver, Index.update);
        driver.findElement(Index.update).isEnabled();
        ElementOperation.isElementPresent(driver, Index.delete);
        driver.findElement(Index.delete).isEnabled();

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
