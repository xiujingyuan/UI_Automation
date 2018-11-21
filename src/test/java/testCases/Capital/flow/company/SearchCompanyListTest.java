package testCases.Capital.flow.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.company.SearchCompanyList;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.flow.company.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/5
 * Time: 下午2:20
 * 资金管理->银行流水管理->公司管理列表
 * 检查页面打开正常、搜索
 */
public class SearchCompanyListTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "公司管理列表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 资金管理->银行流水管理->公司管理列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowCompanyIndex();

        //检查是否成功进入页面
        driver.getTitle().contains("全部板块公司列表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/company/",
                "searchCompanyList.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param affiliation_plate
     * @param status
     * @param is_create_account
     * @param company_name
     * @param result
     */
    @Test(dataProvider = "data", description = "公司管理列表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String affiliation_plate, String status,
                            String is_create_account, String company_name, String result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchCompanyList searchAction = new SearchCompanyList();
        searchAction.search(case_number, affiliation_plate, status, is_create_account, company_name, result);

        boolean a = ElementOperation.isElementPresent(driver, Index.sum);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, Index.mark);
        Reporter.log(String.valueOf(b));

        if (a) {
            //搜索结果非0时，检查搜索后结果 是否与期望结果一致
            int number1 = Integer.parseInt(driver.findElement(Index.sum).getText());
            //Excel中result值为string，转为int再比较
            int r1 = Integer.parseInt(result);
            Assert.assertEquals(number1, r1);

            Reporter.log(String.valueOf(result));
            Reporter.log("数据总条数：" + driver.findElement(Index.sum).getText() + "\n");
        } else if (b) {
            //搜索结果为0
            Assert.assertEquals(driver.findElement(Index.mark).getText(), "没有找到数据。");
            Reporter.log("没有找到数据。");
        }
        Reporter.log("搜索正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
