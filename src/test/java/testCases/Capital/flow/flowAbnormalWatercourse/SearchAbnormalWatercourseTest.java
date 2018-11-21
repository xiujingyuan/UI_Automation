package testCases.Capital.flow.flowAbnormalWatercourse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.flowAbnormalWatercourse.SearchAbnormalWatercourse;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.flow.flowAbnormalWatercourse.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/4
 * Time: 下午5:57
 * 页面：资金管理->银行流水管理->异常流水列表
 * 检查页面正常打开
 * 按钮：搜索，重置搜索条件
 */
public class SearchAbnormalWatercourseTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "异常流水列表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 资金管理->银行流水管理->异常流水列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowAbnormalWatercourse();

        //检查是否成功进入页面
        driver.getTitle().contains("全部板块异常流水列表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/flowAbnormalWatercourse/",
                "searchAbnormalWatercourse.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param min_create_time
     * @param max_create_time
     * @param bank_account
     * @param min_trade_date
     * @param max_trade_date
     * @param company
     * @param side_account
     * @param side_account_name
     * @param summary
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "异常流水列表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String min_create_time, String max_create_time,
                            String bank_account, String min_trade_date, String max_trade_date, String company,
                            String side_account, String side_account_name, String summary, String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchAbnormalWatercourse searchAction = new SearchAbnormalWatercourse();
        searchAction.search(case_number, min_create_time, max_create_time, bank_account, min_trade_date, max_trade_date,
                company, side_account, side_account_name, summary, expect_result);

        boolean m = ElementOperation.isElementPresent(driver, Index.sum_count_text);
        Reporter.log(String.valueOf(m));
        boolean n = ElementOperation.isElementPresent(driver, Index.not_found);
        Reporter.log(String.valueOf(n));

        if (m) {
            //搜索结果非0时，检查搜索后结果 是否与期望结果一致
            int number1 = Integer.parseInt(driver.findElement(Index.sum_count_text).getText());
            //Excel中result值为string，转为int再比较
            int r1 = Integer.parseInt(expect_result);
            Assert.assertEquals(number1, r1);

            Reporter.log(String.valueOf(expect_result));
            Reporter.log("数据总条数：" + driver.findElement(Index.sum_count_text).getText() + "\n");
        } else if (n) {
            //搜索结果为0
            Assert.assertEquals(driver.findElement(Index.not_found).getText(), "没有找到数据。");
            Reporter.log("没有找到数据。");
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param min_create_time
     * @param max_create_time
     * @param bank_account
     * @param min_trade_date
     * @param max_trade_date
     * @param company
     * @param side_account
     * @param side_account_name
     * @param summary
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "异常流水列表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String min_create_time, String max_create_time,
                            String bank_account, String min_trade_date, String max_trade_date, String company,
                            String side_account, String side_account_name, String summary, String expect_result) {
        //搜索
        SearchAbnormalWatercourse searchAction = new SearchAbnormalWatercourse();
        searchAction.search(case_number, min_create_time, max_create_time, bank_account, min_trade_date, max_trade_date,
                company, side_account, side_account_name, summary, expect_result);

        boolean m = ElementOperation.isElementPresent(driver, Index.sum_count_text);
        Reporter.log(String.valueOf(m));
        boolean n = ElementOperation.isElementPresent(driver, Index.not_found);
        Reporter.log(String.valueOf(n));

        if (m) {
            //搜索结果非0时，记录重置前结果
            int reset_before_number = Integer.parseInt(driver.findElement(Index.sum_count_text).getText());

            //点击重置搜索
            SearchAbnormalWatercourse resetSearchAction = new SearchAbnormalWatercourse();
            resetSearchAction.resetSearch(driver);

            //记录重置后结果
            int reset_after_number = Integer.parseInt(driver.findElement(Index.sum_count_text).getText());

            //数据条数
            Assert.assertEquals(reset_before_number, reset_after_number);

            Reporter.log("数据总条数：" + driver.findElement(Index.sum_count_text).getText() + "\n");
        } else if (n) {
            //搜索结果为0，记录搜索结果
            String reset_after_text = driver.findElement(Index.not_found).getText();
            //点击重置搜索
            SearchAbnormalWatercourse resetSearchAction = new SearchAbnormalWatercourse();
            resetSearchAction.resetSearch(driver);
            //对比
            Assert.assertEquals(reset_after_text, "没有找到数据。");
            Reporter.log("没有找到数据。");
        }
        String reset_after_min_create_time = driver.findElement(Index.min_create_time).getText();
        String reset_after_max_create_time = driver.findElement(Index.max_create_time).getText();
        String reset_after_bank_account = driver.findElement(Index.bank_account).getText();
        String reset_after_min_trade_date = driver.findElement(Index.min_trade_date).getText();
        String reset_after_max_trade_date = driver.findElement(Index.max_trade_date).getText();
        String reset_after_company = ElementOperation.getSelectedValue(driver, Index.company);
        String reset_after_side_account = driver.findElement(Index.side_account).getText();
        String reset_after_side_account_name = driver.findElement(Index.side_account_name).getText();
        String reset_after_summary = driver.findElement(Index.summary).getText();

        //条件
        Assert.assertEquals(reset_after_min_create_time, "");
        Assert.assertEquals(reset_after_max_create_time, "");
        Assert.assertEquals(reset_after_bank_account, "");
        Assert.assertEquals(reset_after_min_trade_date, "");
        Assert.assertEquals(reset_after_max_trade_date, "");
        Assert.assertEquals(reset_after_company, "请选择公司");
        Assert.assertEquals(reset_after_side_account, "");
        Assert.assertEquals(reset_after_side_account_name, "");
        Assert.assertEquals(reset_after_summary, "");
        Reporter.log("重置正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
