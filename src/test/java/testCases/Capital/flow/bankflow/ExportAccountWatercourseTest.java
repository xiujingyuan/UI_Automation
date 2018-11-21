package testCases.Capital.flow.bankflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.bankFlow.SearchAccountWatercourse;
import web.common.*;
import web.pageObjects.Capital.flow.bankFlow.Index;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/4
 * Time: 上午11:42
 * 页面：资金管理->银行流水管理->流水列表
 * 按钮：下载
 */
public class ExportAccountWatercourseTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/bankflow/",
                "searchAccountWatercourse.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param affiliation_type
     * @param company_name
     * @param bank_name
     * @param bank_account
     * @param min_roll_in_money
     * @param max_roll_in_money
     * @param min_roll_out_money
     * @param max_roll_out_money
     * @param min_balance
     * @param max_balance
     * @param min_trade_date
     * @param max_trade_date
     * @param side_account
     * @param side_account_name
     * @param loan_sign
     * @param summary
     * @param stair_classify
     * @param second_classify
     * @param three_classify
     * @param money_pro
     * @param expect_sum_roll_in
     * @param expect_sum_roll_out
     */
    @Test(dataProvider = "data", description = "流水列表_下载", enabled = true, groups = {"download"})
    public void downloadTest(String case_number, String affiliation_type, String company_name,
                             String bank_name, String bank_account, String min_roll_in_money, String max_roll_in_money,
                             String min_roll_out_money, String max_roll_out_money, String min_balance, String max_balance,
                             String min_trade_date, String max_trade_date, String side_account, String side_account_name,
                             String loan_sign, String summary, String stair_classify, String second_classify, String three_classify,
                             String money_pro, String expect_sum_roll_in, String expect_sum_roll_out) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //选择搜索条件
        SearchAccountWatercourse searchAction = new SearchAccountWatercourse();
        searchAction.search(case_number, affiliation_type, company_name, bank_name, bank_account, min_roll_in_money,
                max_roll_in_money, min_roll_out_money, max_roll_out_money, min_balance, max_balance, min_trade_date,
                max_trade_date, side_account, side_account_name, loan_sign, summary, stair_classify, second_classify,
                three_classify, money_pro, expect_sum_roll_in, expect_sum_roll_out);

        //点击【下载】
        ElementOperation.buttonClick(driver, Index.download);
        wait5s();

        //检查下载目录下是否存在文件
        // 1.获取今天的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());

        // 2.文件名拼接，如：流水下载报表20180918
        String file_name = "流水下载报表" + today;

        // 3.判断是否存在下载文件
        Export downloadAction = new Export();
        boolean m = downloadAction.download(file_name);
        // 4.比较下载结果与预期结果，如果下载成功，返回true
        Assert.assertEquals(m, true);
        Reporter.log(String.valueOf("下载判断结果：" + m));
        logger.info(String.valueOf("下载判断结果：" + m));
    }

    @AfterClass
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
        logger.info("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
