package testCases.Capital.accountingReport.combinedReport.diameterCombinedReport.diameterCombinedCompanyReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.diameterCombinedCompanyReport.ViewCompanyReport;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ExcelOperation;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 上午10:52
 * 会计核算->月度报表->合并报表->管报税报公司合并报表
 * 查看明细数据
 */
public class ViewCompanyReportTest extends BaseAction {
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

    @Test(dataProvider = "data", enabled = true, description = "公司原始数据明细_检查页面正常打开", groups = {"view"})
    public void view(String case_number, String month_start, String month_end, String diameter,
                     String version) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //进入 会计核算->月度报表->合并报表->管报税报公司合并报表->查看明细数据
        ViewCompanyReport viewAction = new ViewCompanyReport();
        viewAction.view(case_number, month_start, month_end, diameter, version);

        //核对 当前页面标题
        Assert.assertEquals(driver.getTitle(), "公司原始数据明细");
        Reporter.log("当前页面 = 期望页面");
        //log中打印页面标题
        Reporter.log("页面校验完成后，取当前页面标题：" + driver.getTitle());
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}