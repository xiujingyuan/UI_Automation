package testCases.Capital.flow.everydayaccountbalance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.everydayAccountBalance.SearchAccountBalance;
import web.common.*;
import web.pageObjects.Capital.flow.everydayAccountBalance.Index;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/3
 * Time: 上午11:52
 * 页面：资金管理->银行流水管理->账户余额列表
 * 按钮：导出
 */
public class ExportAccountBalanceTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeTest
    public void beforeTest() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
    }

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/everydayaccountbalance/",
                "searchAccountBalance.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param account_id
     * @param company_short
     * @param affiliation_plate
     * @param min_trade_date
     * @param max_trade_date
     * @param error_status
     * @param money_type
     * @param expect_first_balance_result
     * @param expect_end_balance_result
     */
    @Test(dataProvider = "data", description = "账户余额列表_导出", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String account_id, String company_short,
                             String affiliation_plate, String min_trade_date, String max_trade_date, String error_status,
                             String money_type, String expect_first_balance_result, String expect_end_balance_result) {
        //搜索
        SearchAccountBalance searchAction = new SearchAccountBalance();
        searchAction.search(case_number, account_id, company_short, affiliation_plate, min_trade_date,
                max_trade_date, error_status, money_type, expect_first_balance_result, expect_end_balance_result);

        //点击【导出】
        ElementOperation.buttonClick(driver, Index.export);
        wait5s();

        // 获取今天的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        Reporter.log("today:" + today);

        //检查下载目录下是否存在文件
        String file_name = today + "账户列表.csv";

        Export downloadAction = new Export();
        boolean m = downloadAction.download(file_name);
        // 比较下载结果与预期结果，如果下载成功，返回true
        Assert.assertEquals(m, true);
        Reporter.log("下载功能正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}