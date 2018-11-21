package testCases.Capital.flow.flowbankaccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.flowBankAccount.SearchAccount;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.flow.flowBankAccount.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/28
 * Time: 下午7:16
 * 页面：资金管理->银行流水管理->账户管理列表
 * 检查页面正常打开
 * 按钮：搜索，重置搜索条件
 */
public class SearchAccountTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "账户管理列表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 账户管理列表页面
        Menu menuAction = new Menu();
        menuAction.gotoFlowBankAccount();

        //检查标题是否正确
        driver.getTitle().contains("账户列表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/flowbankaccount/",
                "searchAccount.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param affiliation_plate
     * @param search_company_name
     * @param bank_name
     * @param account_name
     * @param account_id
     * @param account_type
     * @param money_pro
     * @param bank_short_name
     * @param account_full_name
     * @param result
     */
    @Test(dataProvider = "data", description = "账户管理列表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String affiliation_plate, String search_company_name,
                            String bank_name, String account_name, String account_id, String account_type,
                            String money_pro, String bank_short_name, String account_full_name, String result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchAccount searchAction = new SearchAccount();
        searchAction.search(case_number, affiliation_plate, search_company_name, bank_name, account_name,
                account_id, account_type, money_pro, bank_short_name, account_full_name, result);

        boolean a = ElementOperation.isElementPresent(driver, Index.sum_number);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, Index.empty_text);
        Reporter.log(String.valueOf(b));

        if (a) {
            //搜索结果非0时，检查搜索后结果 是否与期望结果一致
            int number1 = Integer.parseInt(driver.findElement(Index.sum_number).getText());
            //Excel中result值为string，转为int再比较
            int r1 = Integer.parseInt(result);
            Assert.assertEquals(number1, r1);

            Reporter.log(String.valueOf(result));
            Reporter.log("数据总条数：" + driver.findElement(Index.sum_number).getText() + "\n");
        } else if (b) {
            //搜索结果为0
            Assert.assertEquals(driver.findElement(Index.empty_text).getText(), "没有找到数据。");
            Reporter.log("没有找到数据。");
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param affiliation_plate
     * @param search_company_name
     * @param bank_name
     * @param account_name
     * @param account_id
     * @param account_type
     * @param money_pro
     * @param bank_short_name
     * @param account_full_name
     * @param result
     */
    @Test(dataProvider = "data", description = "账户管理列表_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String affiliation_plate, String search_company_name,
                            String bank_name, String account_name, String account_id, String account_type,
                            String money_pro, String bank_short_name, String account_full_name, String result) {
        //搜索
        SearchAccount searchAction = new SearchAccount();
        searchAction.search(case_number, affiliation_plate, search_company_name, bank_name, account_name,
                account_id, account_type, money_pro, bank_short_name, account_full_name, result);

        boolean a = ElementOperation.isElementPresent(driver, Index.sum_number);

        if (a) {
            //搜索结果非0，记录重置前结果
            int reset_before_number = Integer.parseInt(driver.findElement(Index.sum_number).getText());
            //点击重置搜索
            SearchAccount resetSearchAction = new SearchAccount();
            resetSearchAction.resetSearch(driver);
            //记录重置后结果
            int reset_after_number = Integer.parseInt(driver.findElement(Index.sum_number).getText());
            Assert.assertEquals(reset_before_number, reset_after_number);
            Reporter.log("搜索条数：" + "\n" + driver.findElement(Index.sum_number).getText());
        } else {
            //搜索结果为0，记录搜索结果
            String reset_after_text = driver.findElement(Index.empty_text).getText();
            //点击重置搜索
            SearchAccount resetSearchAction = new SearchAccount();
            resetSearchAction.resetSearch(driver);
            Assert.assertEquals(reset_after_text, "没有找到数据。");
            Reporter.log("没有找到数据");
        }
        String reset_after_affiliation_plate = ElementOperation.getSelectedValue(driver, Index.search_affiliation_plate);
        String reset_after_company_name = ElementOperation.getSelectedValue(driver, Index.search_company_name);
        String reset_after_bank_name = driver.findElement(Index.search_bank_name).getText();
        String reset_after_account_name = driver.findElement(Index.search_account_name).getText();
        String reset_after_account_id = driver.findElement(Index.search_account_id).getText();
        String reset_after_account_type = ElementOperation.getSelectedValue(driver, Index.search_account_type);
        String reset_after_money_pro = ElementOperation.getSelectedValue(driver, Index.search_money_pro);
        String reset_after_bank_short_name = ElementOperation.getSelectedValue(driver, Index.search_bank_short_name);
        String reset_after_account_full_name = driver.findElement(Index.search_account_full_name).getText();

        Assert.assertEquals(reset_after_affiliation_plate, "请选择板块");
        Assert.assertEquals(reset_after_company_name, "请选择公司");
        Assert.assertEquals(reset_after_bank_name, "");
        Assert.assertEquals(reset_after_account_name, "");
        Assert.assertEquals(reset_after_account_id, "");
        Assert.assertEquals(reset_after_account_type, "请选择账户类型");
        Assert.assertEquals(reset_after_money_pro, "请选择资金性质");
        Assert.assertEquals(reset_after_bank_short_name, "请选择银行");
        Assert.assertEquals(reset_after_account_full_name, "");
        Reporter.log("重置正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
