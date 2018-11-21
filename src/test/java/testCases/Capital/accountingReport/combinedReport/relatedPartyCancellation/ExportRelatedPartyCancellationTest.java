package testCases.Capital.accountingReport.combinedReport.relatedPartyCancellation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.relatedPartyCancellation.SearchRelatedPartyCancellation;
import web.common.*;
import web.pageObjects.Capital.accountingReport.combinedReport.relatedPartyCancellation.IndexRelatedPartyCancellation;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午4:59
 * 会计核算->月度报表->合并报表->关联方来往抵消报表
 * 下载
 */
public class ExportRelatedPartyCancellationTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/combinedReport/relatedPartyCancellation/",
                "searchRelatedPartyCancellation.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param month
     */
    @Test(dataProvider = "data", description = "关联方来往抵消报表_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String month) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchRelatedPartyCancellation searchAction = new SearchRelatedPartyCancellation();
        searchAction.search(case_number, month);

        //点击【下载】
        ElementOperation.buttonClick(driver, IndexRelatedPartyCancellation.download);
        wait5s();

        //检查下载目录下是否存在文件
        String file_name = "关联方往来报表.xls";

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