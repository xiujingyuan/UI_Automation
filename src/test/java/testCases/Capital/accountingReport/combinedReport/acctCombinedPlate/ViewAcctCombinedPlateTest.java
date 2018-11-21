package testCases.Capital.accountingReport.combinedReport.acctCombinedPlate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午3:06
 * 会计核算->月度报表->合并报表->口径列表
 * 页面打开
 */
public class ViewAcctCombinedPlateTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeTest
    public void beforeTest() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
    }

    public void test() {
    }

    @Test(enabled = true, description = "口径列表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 会计核算->月度报表->合并报表->口径列表
        Menu menuAction = new Menu();
        menuAction.gotoAcctCombinedPlateIndex();

        //检查标题是否正确
        driver.getTitle().contains("口径列表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
