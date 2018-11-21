package testCases.Capital.accountingReport.accountingBalanceVerify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.accountingBalanceVerify.SearchAccountingBalanceVerify;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.accountingReport.accountingBalanceVerify.IndexVerify;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午3:14
 * 会计核算->月度报表->分析报表->往来账报表->公司余额交叉验证表
 * 页面打开、搜索、重置
 */
public class SearchAccountingBalanceVerifyTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "公司余额交叉验证表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 会计核算->月度报表->分析报表->往来账报表->公司余额交叉验证表
        Menu menuAction = new Menu();
        menuAction.gotoAccountingBalanceVerify();

        //检查标题是否正确
        driver.getTitle().contains("账户余额交叉验证报表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/accountingBalanceVerify/",
                "searchAccountingBalanceVerify.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param create_time
     * @param company_id
     * @param is_hide
     * @param affiliation_plate
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "公司余额交叉验证表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String create_time, String company_id, String is_hide,
                            String affiliation_plate, String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //前往 会计核算->月度报表->分析报表->往来账报表->公司余额交叉验证表
        Menu menuAction = new Menu();
        menuAction.gotoAccountingBalanceVerify();

        //搜索
        SearchAccountingBalanceVerify searchAction = new SearchAccountingBalanceVerify();
        searchAction.search(case_number, create_time, company_id, is_hide, affiliation_plate, expect_result);

        boolean a = ElementOperation.isElementPresent(driver, IndexVerify.sum);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, IndexVerify.not_found);
        Reporter.log(String.valueOf(b));

        if (a) {
            //搜索结果非0时，检查搜索后结果 是否与期望结果一致
            int number1 = Integer.parseInt(driver.findElement(IndexVerify.sum).getText());
            //Excel中result值为string，转为int再比较
            int r1 = Integer.parseInt(expect_result);
            Assert.assertEquals(number1, r1);

            Reporter.log(String.valueOf(expect_result));
            Reporter.log("数据总条数：" + driver.findElement(IndexVerify.sum).getText() + "\n");
        } else if (b) {
            //搜索结果为0
            Assert.assertEquals(driver.findElement(IndexVerify.not_found).getText(), "没有找到数据。");
            Reporter.log("没有找到数据");
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param create_time
     * @param company_id
     * @param is_hide
     * @param affiliation_plate
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "公司余额交叉验证表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String create_time, String company_id, String is_hide,
                            String affiliation_plate, String expect_result) {
        //搜索
        SearchAccountingBalanceVerify searchAction = new SearchAccountingBalanceVerify();
        searchAction.search(case_number, create_time, company_id, is_hide, affiliation_plate, expect_result);

        boolean a = ElementOperation.isElementPresent(driver, IndexVerify.sum);
        Reporter.log(String.valueOf(a));

        if (a) {
            //有搜索结果，记录重置前结果
            int reset_before_number = Integer.parseInt(driver.findElement(IndexVerify.sum).getText());

            //点击重置搜索
            SearchAccountingBalanceVerify resetSearchAction = new SearchAccountingBalanceVerify();
            resetSearchAction.resetSearch(driver);

            //记录重置后结果
            int reset_after_number = Integer.parseInt(driver.findElement(IndexVerify.sum).getText());

            Assert.assertEquals(reset_before_number, reset_after_number);
            Reporter.log("重置前后数据数一致，均为" + driver.findElement(IndexVerify.sum).getText() + "\n");
        } else {
            //无搜索结果，记录重置前结果
            String reset_before_text = driver.findElement(IndexVerify.not_found).getText();
            //点击重置搜索
            SearchAccountingBalanceVerify resetSearchAction = new SearchAccountingBalanceVerify();
            resetSearchAction.resetSearch(driver);

            //记录重置后结果
            String reset_after_text = driver.findElement(IndexVerify.not_found).getText();
            //对比
            Assert.assertEquals(reset_before_text, reset_after_text);
            Reporter.log("没有找到数据。");
        }
        String text_create_time = driver.findElement(IndexVerify.create_time).getText();
        String text_company_id = ElementOperation.getSelectedValue(driver, IndexVerify.company_id);
        String text_is_hide = ElementOperation.getSelectedValue(driver, IndexVerify.is_hide);
        String text_affiliation_plate = ElementOperation.getSelectedValue(driver, IndexVerify.affiliation_plate);

        Assert.assertEquals(text_create_time, "");
        Assert.assertEquals(text_company_id, "请选择公司");
        Assert.assertEquals(text_is_hide, "显示差异类型");
        Assert.assertEquals(text_affiliation_plate, "请选择板块");
        Reporter.log("重置正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
