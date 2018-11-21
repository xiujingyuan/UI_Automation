package testCases.Capital.accountingReport.internalAccounting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.internalAccounting.SearchInternalAccounting;
import web.common.*;
import web.pageObjects.Capital.accountingReport.internalAccounting.IndexInternalAccounting;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午3:53
 * 会计核算->月度报表->分析报表->往来账报表->集团内单位往来账
 * 下载
 */
public class ExportInternalAccountingTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
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
    @Test(dataProvider = "data", description = "集团内单位往来账_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String min_create_time, String max_create_time, String affiliation_plate,
                             String company_id, String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchInternalAccounting searchAction = new SearchInternalAccounting();
        searchAction.search(case_number, min_create_time, max_create_time, affiliation_plate, company_id, expect_result);

        //点击【下载】
        ElementOperation.buttonClick(driver, IndexInternalAccounting.download);
        wait5s();

        // 获取今天的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        Reporter.log("today:" + today);

        //检查下载目录下是否存在文件
        String file_name = today + "集团内单位往来账报表.csv";

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
