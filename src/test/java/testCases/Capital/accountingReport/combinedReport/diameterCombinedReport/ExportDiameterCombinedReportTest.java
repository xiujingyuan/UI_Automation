package testCases.Capital.accountingReport.combinedReport.diameterCombinedReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.SearchDiameterCombinedReport;
import web.common.*;
import web.pageObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.IndexDiameterCombinedReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 上午10:46
 * 会计核算->月度报表->合并报表->管报税报公司合并报表
 * 下载
 */
public class ExportDiameterCombinedReportTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/combinedReport/diameterCombinedReport/",
                "searchDiameterCombinedReport.xlsx"
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
    @Test(dataProvider = "data", description = "管报税报公司合并报表_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String month_start, String month_end, String diameter,
                             String version) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchDiameterCombinedReport searchAction = new SearchDiameterCombinedReport();
        searchAction.search(case_number, month_start, month_end, diameter, version);

        //点击【下载】
        ElementOperation.buttonClick(driver, IndexDiameterCombinedReport.download);
        wait5s();

        //检查下载目录下是否存在文件
        //如：管报税报公司合并报表-快牛板块+云智
        String file_name = "管报税报公司合并报表-" + diameter + ".xls";

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
