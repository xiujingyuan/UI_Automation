package testCases.Capital.flow.bankflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.bankFlow.SearchDiffPlateReport;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.flow.bankFlow.DiffPlateReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/4
 * Time: 下午5:05
 * 页面：资金管理->银行流水管理->板块流水列表
 * 检查页面正常打开
 * 按钮：搜索，重置搜索条件
 */
public class SearchDiffPlateReportTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "板块流水列表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 资金管理->银行流水管理->板块流水列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowDiffPlateReport();

        //检查是否成功进入页面
        driver.getTitle().contains("全部板块间资金转账汇总报表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
        logger.info("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/bankflow/",
                "searchDiffPlateReport.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param date_start
     * @param date_end
     * @param plate
     * @param result
     */
    @Test(dataProvider = "data", description = "板块流水列表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String date_start, String date_end, String plate, String result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchDiffPlateReport searchAction = new SearchDiffPlateReport();
        searchAction.search(case_number, date_start, date_end, plate, result);

        boolean a = ElementOperation.isElementPresent(driver, DiffPlateReport.count);
        Reporter.log(String.valueOf(a));
        logger.info(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, DiffPlateReport.unfound);
        Reporter.log(String.valueOf(b));
        logger.info(String.valueOf(b));

        if (a) {
            //搜索结果非0时，检查搜索后结果 是否与期望结果一致
            int number1 = Integer.parseInt(driver.findElement(DiffPlateReport.count).getText());
            //Excel中result值为string，转为int再比较
            int r1 = Integer.parseInt(result);
            Assert.assertEquals(number1, r1);

            Reporter.log(String.valueOf(result));
            Reporter.log("数据总条数：" + driver.findElement(DiffPlateReport.count).getText() + "\n");

            logger.info(String.valueOf(result));
            logger.info("数据总条数：" + driver.findElement(DiffPlateReport.count).getText() + "\n");
        } else if (b) {
            //搜索结果为0
            Assert.assertEquals(driver.findElement(DiffPlateReport.unfound).getText(), "没有找到数据。");
            Reporter.log("没有找到数据。");
            logger.info("没有找到数据。");
        }
        Reporter.log("搜索正常。");
        logger.info("没有找到数据。");
    }

    /**
     * @param case_number
     * @param date_start
     * @param date_end
     * @param plate
     * @param result
     */
    @Test(dataProvider = "data", description = "板块流水列表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String date_start, String date_end, String plate, String result) {
        //搜索
        SearchDiffPlateReport searchAction = new SearchDiffPlateReport();
        searchAction.search(case_number, date_start, date_end, plate, result);

        boolean a = ElementOperation.isElementPresent(driver, DiffPlateReport.count);
        Reporter.log(String.valueOf(a));
        logger.info(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, DiffPlateReport.unfound);
        Reporter.log(String.valueOf(b));
        logger.info(String.valueOf(b));

        if (a) {
            //搜索结果非0时，记录重置前结果
            int reset_before_number = Integer.parseInt(driver.findElement(DiffPlateReport.count).getText());
            //点击重置搜索
            SearchDiffPlateReport resetSearchAction = new SearchDiffPlateReport();
            resetSearchAction.resetSearch(driver);
            //记录重置后结果
            int reset_after_number = Integer.parseInt(driver.findElement(DiffPlateReport.count).getText());
            //对比 重置前后
            Assert.assertEquals(reset_before_number, reset_after_number);

            Reporter.log("重置前后数据数一致，均为：" + driver.findElement(DiffPlateReport.count).getText() + "\n");
            logger.info("重置前后数据数一致，均为：" + driver.findElement(DiffPlateReport.count).getText() + "\n");
        } else if (b) {
            //搜索结果为0，记录重置前文案
            String reset_before_text = driver.findElement(DiffPlateReport.unfound).getText();
            //点击重置搜索
            SearchDiffPlateReport resetSearchAction = new SearchDiffPlateReport();
            resetSearchAction.resetSearch(driver);
            //记录重置后结果
            String reset_after_text = driver.findElement(DiffPlateReport.unfound).getText();
            //对比 重置前后
            Assert.assertEquals(reset_before_text, reset_after_text);

            Reporter.log("重置前后文案一致，均为：" + reset_after_text + "\n");
            logger.info("重置前后文案一致，均为：" + reset_after_text + "\n");
        }
        String reset_after_date_start = driver.findElement(DiffPlateReport.date_start).getText();
        String reset_after_date_end = driver.findElement(DiffPlateReport.date_end).getText();
        String reset_after_plate = ElementOperation.getSelectedValue(driver, DiffPlateReport.plate);

        Assert.assertEquals(reset_after_date_start, "");
        Assert.assertEquals(reset_after_date_end, "");
        Assert.assertEquals(reset_after_plate, "选择板块");
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
