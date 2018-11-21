package testCases.Capital.flow.bankflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.bankFlow.SearchDiffPlateReport;
import web.common.*;
import web.pageObjects.Capital.flow.bankFlow.DiffPlateReport;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/4
 * Time: 下午5:17
 * 资金管理->银行流水管理->板块流水列表
 * 按钮【下载】
 */
public class ExportDiffPlateReportTest extends BaseAction {
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
    @Test(dataProvider = "data", description = "板块流水列表_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String date_start, String date_end, String plate, String result) {
        //搜索
        SearchDiffPlateReport searchAction = new SearchDiffPlateReport();
        searchAction.search(case_number, date_start, date_end, plate, result);

        //点击【下载】
        ElementOperation.buttonClick(driver, DiffPlateReport.download);
        wait10s();

        //检查下载目录下是否存在文件
        // 1.获取今天的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        Reporter.log("today:" + today);
        // 2.拼接文件名，如：板块间资金转账汇总报表20180918
        String file_name = "板块间资金转账汇总报表" + today;
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
        logger.info("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}

