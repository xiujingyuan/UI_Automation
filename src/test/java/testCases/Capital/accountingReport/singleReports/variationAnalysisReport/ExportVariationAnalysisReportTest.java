package testCases.Capital.accountingReport.singleReports.variationAnalysisReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.singleReports.variationAnalysisReport.SearchVariationAnalysisReport;
import web.common.*;
import web.pageObjects.Capital.accountingReport.singleReports.variationAnalysisReport.IndexVariationAnalysisReport;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午2:21
 * 会计核算->月度报表->单体报表->单体变动分析表
 * 下载
 */
public class ExportVariationAnalysisReportTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/singleReports/variationAnalysisReport/",
                "searchVariationAnalysisReport.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param month
     * @param company_id
     */
    @Test(dataProvider = "data", description = "单体变动分析表_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String month, String company_id) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchVariationAnalysisReport searchAction = new SearchVariationAnalysisReport();
        searchAction.search(case_number, month, company_id);

        //点击【下载】
        ElementOperation.buttonClick(driver, IndexVariationAnalysisReport.download);
        wait5s();

        //检查下载目录下是否存在文件
        //示例：公司北京智博信诚科技有限公司(2018-08)变动分析表报表
        String file_name = "公司" + company_id + "(" + month + ")" + "变动分析表报表";

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
