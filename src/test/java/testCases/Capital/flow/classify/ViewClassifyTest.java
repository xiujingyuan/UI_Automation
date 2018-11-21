package testCases.Capital.flow.classify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.flow.classify.IndexPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/6
 * Time: 下午2:52
 * 资金管理->银行流水管理->银行流水科目管理->分类管理
 * 查看
 */
public class ViewClassifyTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeTest
    public void beforeTest() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
    }

    public void test() {
    }

    @Test(description = "分类管理_查看分类", enabled = true, groups = {"view"})
    public void viewClassify() {
        //前往 资金管理->银行流水管理->银行流水科目管理->分类管理
        Menu menuAction = new Menu();
        menuAction.gotoFlowClassifyIndex();

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();

        //点击【查看】
        ElementOperation.buttonClick(driver, IndexPage.view);

        //log中打印页面标题
        Reporter.log("当前页面：" + driver.getTitle());

        //焦点移至新打开的页面
        String title = driver.getCurrentUrl().substring(40);
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
        Reporter.log("当前页面 = 期望页面");

        //检查是否存在按钮 更新、删除，并检查是否可用
        ElementOperation.isElementPresent(driver, IndexPage.button_update);
        driver.findElement(IndexPage.button_update).isEnabled();
        ElementOperation.isElementPresent(driver, IndexPage.button_delete);
        driver.findElement(IndexPage.button_delete).isEnabled();

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
