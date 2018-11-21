package testCases.Capital.accountingReport.reportErrorList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.reportErrorList.SearchErrorList;
import web.common.*;
import web.pageObjects.Capital.accountingReport.reportErrorList.Index;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/7
 * Time: 下午6:38
 * 会计核算->月度报表->分析报表->异常统计页
 * 下载
 */
public class ExportErrorListTest extends BaseAction {
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
                "accountingReport/reportErrorList/",
                "searchReportErrorList.xlsx");
        return (retObjArr);
    }

    /**
     *
     * @param case_number
     * @param cate
     * @param version
     * @param expect_count
     */
    @Test(dataProvider = "data", description = "异常统计页_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String cate,String version, String expect_count) {
        //选择搜索条件
        SearchErrorList searchAction = new SearchErrorList();
        searchAction.search(case_number, cate, version,expect_count);

        //点击【下载】
        ElementOperation.buttonClick(driver, Index.download);
        wait5s();

        // 获取今天的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        Reporter.log("today:" + today);

        //检查下载目录下是否存在文件，示例：报表异常校验-合并报表关联方往来抵消20180928143738.xls
        String file_name = "报表异常校验-" + cate + today;

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
