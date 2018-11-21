package testCases.Capital.flow.bankReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.bankReport.SearchPrepare;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.flow.bankReport.PrepareList;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/7
 * Time: 下午12:04
 * 资金管理->银行流水管理->银行流水相关报表->拨备金核对报表
 * 搜索
 */
public class SearchPrepareTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "拨备金核对报表_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 资金管理->银行流水管理->银行流水相关报表->拨备金核对报表
        Menu menuAction = new Menu();
        menuAction.gotoFlowPrepareTheoryMoneyList();

        //检查标题是否正确
        driver.getTitle().contains("拨备金实际&理论值核对报表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");

//        //测试环境未每日刷新，以下校验不做
//        // 1.获取昨天的日期
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, -1);
//        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
//        String text_first_date = driver.findElement(PrepareList.first_date).getText();
//        // 2.比较首行数据的日期是否为昨日，如果是，说明脚本正常刷新过
//        Assert.assertEquals(text_first_date, yesterday);
//        Reporter.log("脚本刷新正常，当前最新数据日期：" + "\n" + driver.findElement(PrepareList.first_date).getText() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/bankReport/",
                "searchPrepareList.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param trade_date_start
     * @param trade_date_end
     * @param expect_count
     */
    @Test(dataProvider = "data", description = "拨备金核对报表_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String trade_date_start, String trade_date_end, String expect_count) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //选择搜索条件
        SearchPrepare searchAction = new SearchPrepare();
        searchAction.search(case_number, trade_date_start, trade_date_end, expect_count);

        boolean a = ElementOperation.isElementPresent(driver, PrepareList.count);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, PrepareList.unfound);
        Reporter.log(String.valueOf(b));

        if (a) {
            //搜索结果非0时，检查搜索后结果 是否与期望结果一致
            int number1 = Integer.parseInt(driver.findElement(PrepareList.count).getText());
            //Excel中result值为string，转为int再比较
            int r1 = Integer.parseInt(expect_count);
            Assert.assertEquals(number1, r1);

            Reporter.log(String.valueOf(expect_count));
            Reporter.log("数据总条数：" + driver.findElement(PrepareList.count).getText() + "\n");
        } else if (b) {
            //搜索结果为0
            Assert.assertEquals(driver.findElement(PrepareList.unfound).getText(), "没有找到数据。");
            Reporter.log("没有找到数据");
        }
        Reporter.log("搜索正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
