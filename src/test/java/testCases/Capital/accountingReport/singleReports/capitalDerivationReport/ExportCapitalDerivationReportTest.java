package testCases.Capital.accountingReport.singleReports.capitalDerivationReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.singleReports.capitalDerivationReport.SearchCapitalDerivationReport;
import web.common.*;
import web.pageObjects.Capital.accountingReport.singleReports.capitalDerivationReport.IndexCapitalDerivationReport;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午2:50
 * 会计核算->月度报表->单体报表->单体资金推导表
 * 下载
 */
public class ExportCapitalDerivationReportTest extends BaseAction {
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
                "accountingReport/singleReports/capitalDerivationReport/",
                "searchCapitalDerivationReport.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param month
     * @param company_id
     */
    @Test(dataProvider = "data", description = "单体资金推导表_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String month, String company_id) {
        //搜索
        SearchCapitalDerivationReport searchAction = new SearchCapitalDerivationReport();
        searchAction.search(case_number, month, company_id);

        //点击【下载】
        ElementOperation.buttonClick(driver, IndexCapitalDerivationReport.download);
        wait5s();

        // 获取今天的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        Reporter.log("today:" + today);

        //检查下载目录下是否存在文件
        //示例：公司萍乡市云智网络科技有限公司(2018-12)单体资金推导报表20180928145208.csv
        String file_name = "公司" + company_id + "(" + month + ")" + "单体资金推导报表" + today;

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
