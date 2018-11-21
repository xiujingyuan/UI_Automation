package testCases.Capital.flow.flowbankaccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.flowBankAccount.SearchAccount;
import web.common.*;
import web.pageObjects.Capital.flow.flowBankAccount.Index;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/22
 * Time: 下午7:13
 * 页面：资金管理->银行流水管理->账户管理列表
 * 按钮：导出
 */
public class ExportAccountTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/flowbankaccount/",
                "searchAccount.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param affiliation_plate
     * @param search_company_name
     * @param bank_name
     * @param account_name
     * @param account_id
     * @param account_type
     * @param money_pro
     * @param bank_short_name
     * @param account_full_name
     * @param result
     */
    @Test(dataProvider = "data", description = "账户管理列表_导出", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String affiliation_plate, String search_company_name,
                             String bank_name, String account_name, String account_id, String account_type,
                             String money_pro, String bank_short_name, String account_full_name, String result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchAccount searchAction = new SearchAccount();
        searchAction.search(case_number, affiliation_plate, search_company_name, bank_name, account_name,
                account_id, account_type, money_pro, bank_short_name, account_full_name, result);

        //点击【导出】
        ElementOperation.buttonClick(driver, Index.button_export);
        wait5s();

        // 获取今天的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        Reporter.log("today:" + today);
        //检查下载目录下是否存在文件
        String file_name = today + "账户列表.csv";

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