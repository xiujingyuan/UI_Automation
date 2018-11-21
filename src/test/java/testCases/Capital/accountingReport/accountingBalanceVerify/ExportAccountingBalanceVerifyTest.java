package testCases.Capital.accountingReport.accountingBalanceVerify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.accountingBalanceVerify.SearchAccountingBalanceVerify;
import web.common.*;
import web.pageObjects.Capital.accountingReport.accountingBalanceVerify.IndexVerify;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午3:22
 * 会计核算->月度报表->分析报表->往来账报表->公司余额交叉验证表
 * 下载
 */
public class ExportAccountingBalanceVerifyTest extends BaseAction {
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
    @Test(dataProvider = "data", description = "公司余额交叉验证表_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String create_time, String company_id, String is_hide,
                             String affiliation_plate, String expect_result) {
        //搜索
        SearchAccountingBalanceVerify searchAction = new SearchAccountingBalanceVerify();
        searchAction.search(case_number, create_time, company_id, is_hide, affiliation_plate, expect_result);

        //点击【下载】
        ElementOperation.buttonClick(driver, IndexVerify.download);
        wait5s();

        // 获取今天的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        Reporter.log("today:" + today);

        //检查下载目录下是否存在文件，示例：20180925账户余额交叉验证报表.csv
        String file_name = today + "账户余额交叉验证报表.csv";

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
