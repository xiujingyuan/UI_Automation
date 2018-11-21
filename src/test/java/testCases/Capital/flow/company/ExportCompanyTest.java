package testCases.Capital.flow.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.company.SearchCompanyList;
import web.common.*;
import web.pageObjects.Capital.flow.company.Index;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/5
 * Time: 下午5:43
 * 资金管理->银行流水管理->公司管理列表
 * 导出
 */
public class ExportCompanyTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/company/",
                "searchCompanyList.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param affiliation_plate
     * @param status
     * @param is_create_account
     * @param company_name
     * @param result
     */
    @Test(dataProvider = "data", description = "公司管理列表_导出", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String affiliation_plate, String status,
                             String is_create_account, String company_name, String result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchCompanyList searchAction = new SearchCompanyList();
        searchAction.search(case_number, affiliation_plate, status, is_create_account, company_name, result);

        //点击【导出】
        ElementOperation.buttonClick(driver, Index.download);
        wait5s();

        // 获取今天的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        Reporter.log("today:" + today);

        //检查下载目录下是否存在文件
        String file_name = today + "公司列表.csv";

        Export downloadAction = new Export();
        boolean m = downloadAction.download(file_name);
        // 比较下载结果与预期结果，如果下载成功，返回true
        Assert.assertEquals(m, true);
        Reporter.log("下载功能正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
