package testCases.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.SearchCompanyList;
import web.common.*;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.IndexCompanyList;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/27
 * Time: 下午12:11
 * 会计核算->月度报表->合并报表->口径列表->口径公司列表
 * 下载
 */
public class ExportCompanyListTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/combinedReport/acctCombinedPlate/companyList/",
                "searchCompanyList.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param start_date
     * @param end_date
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "口径公司列表_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String start_date, String end_date, String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchCompanyList searchAction = new SearchCompanyList();
        searchAction.search(case_number, start_date, end_date, expect_result);

        //点击【下载】
        ElementOperation.buttonClick(driver, IndexCompanyList.download);
        wait5s();

        // 获取今天的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        Reporter.log("today:" + today);

        //检查下载目录下是否存在文件
        //示例：20180927口径公司列表.csv
        String file_name = today + "口径公司列表.csv";
        Reporter.log("file_name:" + file_name);

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
