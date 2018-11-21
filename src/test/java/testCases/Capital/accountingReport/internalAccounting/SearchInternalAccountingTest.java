package testCases.Capital.accountingReport.internalAccounting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.internalAccounting.SearchInternalAccounting;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.internalAccounting.IndexInternalAccounting;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午3:45
 * 会计核算->月度报表->分析报表->往来账报表->集团内单位往来账
 * 页面打开、搜索、重置
 */
public class SearchInternalAccountingTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "集团内单位往来账_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 会计核算->月度报表->分析报表->往来账报表->集团内单位往来账
        Menu menuAction = new Menu();
        menuAction.gotoInternalAccounting();

        //检查标题是否正确
        driver.getTitle().contains("集团内单位往来账");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/internalAccounting/",
                "searchInternalAccounting.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param min_create_time
     * @param max_create_time
     * @param affiliation_plate
     * @param company_id
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "集团内单位往来账_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String min_create_time, String max_create_time, String affiliation_plate,
                            String company_id, String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchInternalAccounting searchAction = new SearchInternalAccounting();
        searchAction.search(case_number, min_create_time, max_create_time, affiliation_plate, company_id, expect_result);

        boolean a = ElementOperation.isElementPresent(driver, IndexInternalAccounting.sum);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, IndexInternalAccounting.not_found);
        Reporter.log(String.valueOf(b));

        if (a) {
            //搜索结果非0时，检查搜索后结果 是否与期望结果一致
            int number1 = Integer.parseInt(driver.findElement(IndexInternalAccounting.sum).getText());
            //Excel中result值为string，转为int再比较
            int r1 = Integer.parseInt(expect_result);
            Assert.assertEquals(number1, r1);

            Reporter.log(String.valueOf(expect_result));
            Reporter.log("数据总条数：" + driver.findElement(IndexInternalAccounting.sum).getText() + "\n");
        } else if (b) {
            //搜索结果为0
            Assert.assertEquals(driver.findElement(IndexInternalAccounting.not_found).getText(), "没有找到数据。");
            Reporter.log("没有找到数据。");
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param min_create_time
     * @param max_create_time
     * @param affiliation_plate
     * @param company_id
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "集团内单位往来账_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String min_create_time, String max_create_time, String affiliation_plate,
                            String company_id, String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchInternalAccounting searchAction = new SearchInternalAccounting();
        searchAction.search(case_number, min_create_time, max_create_time, affiliation_plate, company_id, expect_result);

        boolean a = ElementOperation.isElementPresent(driver, IndexInternalAccounting.sum);

        if (a) {
            //有搜索结果，记录重置前结果
            int reset_before_number = Integer.parseInt(driver.findElement(IndexInternalAccounting.sum).getText());

            //点击重置搜索
            SearchInternalAccounting resetSearchAction = new SearchInternalAccounting();
            resetSearchAction.resetSearch(driver);

            //记录重置后结果
            int reset_after_number = Integer.parseInt(driver.findElement(IndexInternalAccounting.sum).getText());
            //对比
            Assert.assertEquals(reset_before_number, reset_after_number);
            Reporter.log("重置前后数据数一致，均为" + driver.findElement(IndexInternalAccounting.sum).getText() + "\n");
        } else {
            //无搜索结果，记录重置前的文案显示
            String reset_before_text = driver.findElement(IndexInternalAccounting.not_found).getText();

            //点击重置搜索
            SearchInternalAccounting resetSearchAction = new SearchInternalAccounting();
            resetSearchAction.resetSearch(driver);

            //记录重置后结果
            String reset_after_text = driver.findElement(IndexInternalAccounting.not_found).getText();

            //对比
            Assert.assertEquals(reset_before_text, reset_after_text);
        }
        String after_min_create_time = driver.findElement(IndexInternalAccounting.min_create_time).getText();
        String after_max_create_time = driver.findElement(IndexInternalAccounting.max_create_time).getText();
        String after_affiliation_plate = ElementOperation.getSelectedValue(driver, IndexInternalAccounting.affiliation_plate);
        String after_company_id = ElementOperation.getSelectedValue(driver, IndexInternalAccounting.company_id);

        Assert.assertEquals(after_min_create_time, "");
        Assert.assertEquals(after_max_create_time, "");
        Assert.assertEquals(after_affiliation_plate, "请选择板块");
        Assert.assertEquals(after_company_id, "请选择公司");

        Reporter.log("重置正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
