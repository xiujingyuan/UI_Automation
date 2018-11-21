package testCases.Capital.accountingReport.combinedReport.diameterCombinedReport.diameterCombinedCompanyReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.diameterCombinedCompanyReport.SearchCompanyReport;
import web.common.*;
import web.pageObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.diameterCombinedCompanyReport.IndexCompanyReport;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 上午11:25
 * 会计核算->月度报表->合并报表->管报税报公司合并报表->查看明细数据
 * 下载
 */
public class ExportCompanyReportTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/combinedReport/diameterCombinedReport/diameterCombinedCompanyReport/",
                "searchCompanyReport.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param month_start
     * @param month_end
     * @param diameter
     * @param version
     */
    @Test(dataProvider = "data", description = "公司原始数据明细_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String month_start, String month_end, String diameter,
                             String version) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchCompanyReport searchAction = new SearchCompanyReport();
        searchAction.search(case_number, month_start, month_end, diameter, version);

        Reporter.log("当前页面：" + driver.getTitle());

        //点击【下载】
        ElementOperation.buttonClick(driver, IndexCompanyReport.download);
        wait5s();

        // 获取今天的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        Reporter.log("today:" + today);

        //检查下载目录下是否存在文件
        String file_name = "公司原始数据明细报表" + today;

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
