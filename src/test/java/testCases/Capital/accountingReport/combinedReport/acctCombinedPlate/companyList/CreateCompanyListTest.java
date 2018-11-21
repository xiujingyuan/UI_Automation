package testCases.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.ViewCompanyList;
import web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.CreateCompanyList;
import web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.SearchCompanyList;
import web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.ViewCompanyCreate;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ExcelOperation;
import web.common.JdbcUtil;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.IndexCompanyList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午5:09
 * 会计核算->月度报表->合并报表->口径列表->口径公司列表->新增口径公司
 * 进入、新增
 */
public class CreateCompanyListTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "新增口径公司_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //进入页面 新增口径公司
        ViewCompanyCreate viewAction = new ViewCompanyCreate();
        viewAction.view();

        //核对 当前页面标题
        Assert.assertEquals(driver.getTitle(), "新增口径公司");
        Reporter.log("当前页面 = 期望页面");
        //log中打印页面标题
        Reporter.log("页面校验完成后，取当前页面标题：" + driver.getTitle());
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/combinedReport/acctCombinedPlate/companyList/",
                "createCompanyList.xlsx"
        );
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param company_id
     * @param start_date
     * @param end_date
     * @param expect_result
     */
    //确保口径公司列表下不存在Excel中的起始年月
    @Test(dataProvider = "data", description = "新增口径公司_新增", enabled = true, groups = {"add"})
    public void addTest(String case_number, String company_id, String check_status, String start_date, String end_date,
                        String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //进入 口径公司列表
        ViewCompanyList view = new ViewCompanyList();
        view.view();

        //新增口径公司
        CreateCompanyList createAction = new CreateCompanyList();
        createAction.create(case_number, company_id, check_status, start_date, end_date);
    }

    /**
     * @param case_number
     * @param company_id
     * @param check_status
     * @param start_date
     * @param end_date
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "新增口径公司_对比新增信息", enabled = true, groups = {"addConfirm"})
    public void confirmTest(String case_number, String company_id, String check_status, String start_date, String end_date,
                            String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchCompanyList searchAction = new SearchCompanyList();
        searchAction.search(case_number, start_date, end_date, expect_result);

        //从Excel读取对应数据
        String[] excel_data = new String[]{
                company_id,
                start_date,
                end_date,
        };

        //新增后读取对应数据
        String[] text_data = new String[]{
                driver.findElement(IndexCompanyList.text_company_name).getText(),
                driver.findElement(IndexCompanyList.text_start_date).getText(),
                driver.findElement(IndexCompanyList.text_end_date).getText(),
        };

        //对比
        Assert.assertEquals(excel_data, text_data);
        Reporter.log("新增数据正常。");
    }

    /**
     * @throws SQLException
     */
    @Test(description = "新增口径公司_清除新增的测试数据", enabled = true, groups = {"SqlClear"})
    public void clearAddData() throws SQLException {
        //获取数据库连接
        Connection con = JdbcUtil.getconnection();
        //通过拼接构建SQL语句
        //delete acct_diameter_company_relation数据
        String sql = "delete FROM `acct_diameter_company_relation` \n" +
                "WHERE left(acct_diameter_company_relation_create_at,10) = left(CURRENT_DATE,10)";
        PreparedStatement psql;
        //预处理
        psql = con.prepareStatement(sql);
        //执行SQL
        psql.executeUpdate();
        //关闭数据库连接
        JdbcUtil.close(con);
        Reporter.log("新增数据已清除。");
        Reporter.log("测试新增数据已清除。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
