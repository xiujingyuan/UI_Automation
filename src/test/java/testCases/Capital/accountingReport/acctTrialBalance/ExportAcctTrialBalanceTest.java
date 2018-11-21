package testCases.Capital.accountingReport.acctTrialBalance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.acctTrialBalance.SearchAcctTrialBalance;
import web.common.*;
import web.pageObjects.Capital.accountingReport.acctTrialBalance.IndexPage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午2:28
 * 会计核算->月度报表->全部科目余额
 * 下载
 */
public class ExportAcctTrialBalanceTest extends BaseAction {
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
                "accountingReport/acctTrialBalance/",
                "searchAcctTrialBalance.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param min_create_time
     * @param max_create_time
     * @param company_id
     * @param account_level
     * @param affiliation_plate
     * @param accounting_account_code
     * @param acct_trial_balance_name
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "全部科目余额_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String min_create_time, String max_create_time,
                             String company_id, String account_level, String affiliation_plate,
                             String accounting_account_code, String acct_trial_balance_name, String expect_result) {
        //搜索
        SearchAcctTrialBalance searchAction = new SearchAcctTrialBalance();
        searchAction.search(case_number, min_create_time, max_create_time, company_id, account_level,
                affiliation_plate, accounting_account_code, acct_trial_balance_name, expect_result);

        //点击【下载】
        ElementOperation.buttonClick(driver, IndexPage.download);
        wait5s();

        // 获取今天的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        Reporter.log("today:" + today);

        //检查下载目录下是否存在文件，示例：20180925科目余额报表.csv
        String file_name = today + "科目余额报表.csv";

        Export downloadAction = new Export();
        boolean m = downloadAction.download(file_name);
        Assert.assertEquals(m, true);
        Reporter.log("下载功能正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
