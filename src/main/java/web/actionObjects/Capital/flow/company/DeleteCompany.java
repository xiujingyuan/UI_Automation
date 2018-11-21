package web.actionObjects.Capital.flow.company;

import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.flow.company.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/5
 * Time: 下午7:05
 * 资金管理->银行流水管理->公司管理列表->删除公司
 */
public class DeleteCompany extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param search_company_name
     * @param deleted_company_name
     * @param deleted_affiliation_plate
     * @param deleted_status
     * @param deleted_is_create_account
     */
    public void delete(String case_number, String search_company_name, String deleted_company_name, String deleted_affiliation_plate,
                       String deleted_status, String deleted_is_create_account) {
        //选择 更新的公司，点击 搜索
        ElementOperation.editInput(driver, Index.company_name, deleted_company_name);
        ElementOperation.buttonClick(driver, Index.search);
        wait5s();

        //最大化窗口，否则定位不到列表右侧的按钮
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        //获取上一个窗口的句柄
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
        //确认删除
        ElementOperation.buttonClick(driver, Index.delete);

        String alt_text = "Are you sure you want to delete this item?";
        ElementOperation.closeAlertAndGetItsText(driver, alt_text);

        driver.close();
        //切回原来的窗口
        driver.switchTo().window(parentWindowId);
    }
}
