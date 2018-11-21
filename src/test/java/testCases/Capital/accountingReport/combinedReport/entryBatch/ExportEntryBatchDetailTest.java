package testCases.Capital.accountingReport.combinedReport.entryBatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.entryBatch.SearchEntryBatchDetail;
import web.common.*;
import web.pageObjects.Capital.accountingReport.combinedReport.entryBatch.ViewEntryBatch;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午3:45
 * 会计核算->月度报表->合并报表->调整分录抵消明细
 * 下载
 */
public class ExportEntryBatchDetailTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/combinedReport/entryBatch/",
                "searchEntryBatchDetail.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param company_id
     * @param project_name
     * @param project_code
     * @param affiliation_plate
     * @param version
     * @param project_comment
     */
    @Test(dataProvider = "data", description = "调整分录抵消明细_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String company_id, String project_name,
                             String project_code, String affiliation_plate, String version, String project_comment) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchEntryBatchDetail searchAction = new SearchEntryBatchDetail();
        searchAction.search(case_number, company_id, project_name, project_code, affiliation_plate, version, project_comment);

        //点击【下载】
        ElementOperation.buttonClick(driver, ViewEntryBatch.download);
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
