package testCases.Capital.accountingReport.singleReports.singleCompanyReport.entryBatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.singleReports.singleCompanyReport.entryBatch.SearchEntryBatch;
import web.common.*;
import web.pageObjects.Capital.accountingReport.singleReports.singleCompanyReport.entryBatch.IndexEntryBatch;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 上午10:18
 * 会计核算->月度报表->单体报表->管报税报公司单体报表->添加调整分录
 * 下载
 */
public class ExportEntryBatchTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/singleReports/singleCompanyReport/entryBatch/",
                "searchEntryBatch.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param month_start
     * @param month_end
     * @param company_id
     * @param version
     * @param elimination
     * @param project_name
     * @param project_code
     * @param project_comment
     */
    @Test(dataProvider = "data", description = "添加调整分录_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String month_start, String month_end, String company_id, String version,
                             String elimination, String project_name, String project_code, String project_comment) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //调整分录下搜索
        SearchEntryBatch searchAction = new SearchEntryBatch();
        searchAction.search(case_number, month_start, month_end, company_id, version, elimination, project_name,
                project_code, project_comment);

        //点击【下载】
        ElementOperation.buttonClick(driver, IndexEntryBatch.download);
        wait5s();

        //检查下载目录下是否存在文件
        String file_name = "分录报表.xls";

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
