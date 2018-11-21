package testCases.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.ViewCompanyList;
import web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.UpdateCompanyList;
import web.common.*;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList.IndexCompanyList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午4:39
 * 会计核算->月度报表->合并报表->口径列表->口径公司列表
 * 更新
 */
public class UpdateCompanyListTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "accountingReport/combinedReport/acctCombinedPlate/companyList/",
                "updateCompanyList.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_num
     * @param end_date
     */
    @Test(dataProvider = "data", description = "口径公司列表_更新并对比信息", enabled = true, groups = {"update"})
    public void updateTest(String case_num, String end_date) throws SQLException {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //进入口径公司列表
        ViewCompanyList viewAction = new ViewCompanyList();
        viewAction.view();

        //更新前读取对应数据，首行
        String[] before_update = new String[]{
                driver.findElement(IndexCompanyList.text_company_name).getText(),
                driver.findElement(IndexCompanyList.text_company_plate).getText(),
                driver.findElement(IndexCompanyList.text_start_date).getText(),
        };

        //更新
        UpdateCompanyList updateAction = new UpdateCompanyList();
        updateAction.update(case_num, end_date);

        //点击 搜索
        ElementOperation.buttonClick(driver, IndexCompanyList.search);
        wait5s();

        //更新后读取对应数据
        String[] after_update = new String[]{
                driver.findElement(IndexCompanyList.text_company_name).getText(),
                driver.findElement(IndexCompanyList.text_company_plate).getText(),
                driver.findElement(IndexCompanyList.text_start_date).getText(),
        };

        String after_end_date = driver.findElement(IndexCompanyList.text_end_date).getText();

        //对比
        Assert.assertEquals(before_update, after_update);
        Assert.assertEquals(after_end_date, end_date);
        Reporter.log("更新数据正常。");
        //恢复更新前的数据
        recoverAfterTest();
    }

    /**
     * 恢复测试前的数据
     *
     * @throws SQLException
     */
    public void recoverAfterTest() throws SQLException {
        //获取数据库连接
        Connection con = JdbcUtil.getconnection();
        //通过拼接构建SQL语句
        //update  acct_diameter_company_relation数据
        String sql = "update acct_diameter_company_relation \n" +
                "set acct_diameter_company_relation_end_date = ?\n" +
                "where left(acct_diameter_company_relation_update_at,10) = left(current_date,10)";
        PreparedStatement psql;
        //预处理
        psql = con.prepareStatement(sql);
        psql.setString(1, "2019-01");
        //执行SQL
        psql.executeUpdate();
        Reporter.log("acct_diameter_company_relation表数据恢复成功。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
