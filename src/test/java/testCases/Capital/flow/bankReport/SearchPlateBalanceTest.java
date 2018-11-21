package testCases.Capital.flow.bankReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.bankReport.SearchPlateBalance;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.flow.bankReport.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/7
 * Time: 下午2:26
 * 资金管理->银行流水管理->银行流水相关报表->资金日报报表
 * 搜索、重置
 */
public class SearchPlateBalanceTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "资金日报报表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 资金管理->银行流水管理->银行流水相关报表->资金日报报表
        Menu menuAction = new Menu();
        menuAction.gotoFlowPlateBalanceIndex();

        //检查标题是否正确
        driver.getTitle().contains("全部每日运营资金日报报表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
        logger.info("成功进入页面：" + "\n" + driver.getTitle() + "\n");

//        //测试环境未每日刷新，以下校验不做
//        // 1.获取前天的日期
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, -2);
//        String day_before_yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
//        String text_first_date = driver.findElement(Index.first_date).getText();
//        //2.比较首行数据的日期是否为前日，如果是，说明脚本正常刷新过
//        Assert.assertEquals(text_first_date, day_before_yesterday);
//        Reporter.log("脚本刷新正常，当前最新日期为" + "\n" + text_first_date + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/bankReport/",
                "searchPlateBalance.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param affiliation_plate
     * @param min_trade_date
     * @param max_trade_date
     * @param expect_count
     */
    @Test(dataProvider = "data", description = "资金日报报表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String affiliation_plate, String min_trade_date,
                            String max_trade_date, String expect_count) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchPlateBalance searchAction = new SearchPlateBalance();
        searchAction.search(case_number, affiliation_plate, min_trade_date, max_trade_date, expect_count);

        boolean a = ElementOperation.isElementPresent(driver, Index.sum_count);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, Index.unfound);
        Reporter.log(String.valueOf(b));

        if (a) {
            //搜索结果非0时，检查搜索后结果 是否与期望结果一致
            int m = Integer.parseInt(driver.findElement(Index.sum_count).getText());
            //Excel中result值为string，转为int再比较
            int n = Integer.parseInt(expect_count);
            Assert.assertEquals(m, n);

            Reporter.log(String.valueOf(expect_count));
            Reporter.log("数据总条数：" + driver.findElement(Index.sum_count).getText() + "\n");
        } else if (b) {
            //搜索结果为0
            Assert.assertEquals(driver.findElement(Index.unfound).getText(), "没有找到数据。");
            Reporter.log("没有找到数据。");
        }
        Reporter.log("搜索正常。");
        logger.info("搜索正常。");
    }

    /**
     * @param case_number
     * @param affiliation_plate
     * @param min_trade_date
     * @param max_trade_date
     * @param expect_count
     */
    @Test(dataProvider = "data", description = "资金日报报表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String affiliation_plate, String min_trade_date,
                            String max_trade_date, String expect_count) {
        //搜索
        SearchPlateBalance searchAction = new SearchPlateBalance();
        searchAction.search(case_number, affiliation_plate, min_trade_date, max_trade_date, expect_count);

        boolean a = ElementOperation.isElementPresent(driver, Index.sum_count);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, Index.unfound);
        Reporter.log(String.valueOf(b));

        if (a) {
            //搜索结果非0时，记录重置前结果
            int reset_before_number = Integer.parseInt(driver.findElement(Index.sum_count).getText());
            //点击重置搜索
            SearchPlateBalance resetSearchAction = new SearchPlateBalance();
            resetSearchAction.resetSearch(driver);
            //记录重置后结果
            int reset_after_number = Integer.parseInt(driver.findElement(Index.sum_count).getText());
            //对比 重置前后
            Assert.assertEquals(reset_before_number, reset_after_number);

            Reporter.log("重置前后数据数一致，均为：" + driver.findElement(Index.sum_count).getText() + "\n");
        } else if (b) {
            //搜索结果为0，记录重置前文案
            String reset_before_text = driver.findElement(Index.unfound).getText();
            //点击重置搜索
            SearchPlateBalance resetSearchAction = new SearchPlateBalance();
            resetSearchAction.resetSearch(driver);
            //记录重置后结果
            String reset_after_text = driver.findElement(Index.unfound).getText();
            //对比 重置前后
            Assert.assertEquals(reset_before_text, reset_after_text);

            Reporter.log("重置前后文案一致，均为：" + reset_after_text + "\n");
        }
        String reset_after_affiliation_plate = ElementOperation.getSelectedValue(driver, Index.affiliation_plate);
        String reset_after_min_trade_date = driver.findElement(Index.min_trade_date).getText();
        String reset_after_max_trade_date = driver.findElement(Index.max_trade_date).getText();

        Assert.assertEquals(reset_after_affiliation_plate, "请选择板块");
        Assert.assertEquals(reset_after_min_trade_date, "");
        Assert.assertEquals(reset_after_max_trade_date, "");
        Reporter.log("重置正常。");
        logger.info("重置正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
        logger.info("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
