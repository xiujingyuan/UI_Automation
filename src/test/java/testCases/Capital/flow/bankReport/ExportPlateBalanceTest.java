package testCases.Capital.flow.bankReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.bankReport.SearchPlateBalance;
import web.common.*;
import web.pageObjects.Capital.flow.bankReport.Index;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/7
 * Time: 下午2:51
 * 资金管理->银行流水管理->银行流水相关报表->资金日报报表
 * 导出
 */
public class ExportPlateBalanceTest extends BaseAction {
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
    @Test(dataProvider = "data", description = "资金日报报表_导出", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String affiliation_plate, String min_trade_date,
                             String max_trade_date, String expect_count) {
        //搜索
        SearchPlateBalance searchAction = new SearchPlateBalance();
        searchAction.search(case_number, affiliation_plate, min_trade_date, max_trade_date, expect_count);

        //点击【导出】
        ElementOperation.buttonClick(driver, Index.export);
        wait5s();

        //检查下载目录下是否存在文件
        // 1.获取今天的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        Reporter.log("today:" + today);
        // 2.拼接文件名，如：20180919每日运营资金日报报表
        String file_name = today + "每日运营资金日报报表";
        // 3.判断是否存在下载文件
        Export downloadAction = new Export();
        boolean m = downloadAction.download(file_name);
        // 4.比较下载结果与预期结果，如果下载成功，返回true
        Assert.assertEquals(m, true);
        Reporter.log(String.valueOf("下载判断结果：" + m));
        logger.info(String.valueOf("下载判断结果：" + m));
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
