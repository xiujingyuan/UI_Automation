package testCases.Capital.flow.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.company.DeleteCompany;
import web.actionObjects.Capital.menu.Menu;
import web.common.*;
import web.pageObjects.Capital.flow.company.Index;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/5
 * Time: 下午7:04
 * 资金管理->银行流水管理->公司管理列表->删除公司
 */
public class DeleteCompanyTest extends BaseAction {
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
                "flow/company/",
                "updateCompany.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param search_company_name
     * @param deleted_company_name
     * @param deleted_affiliation_plate
     * @param deleted_status
     * @param deleted_is_create_account
     * @param company_short
     * @param tax_province
     * @param tax_city
     * @param remark
     */
    @Test(dataProvider = "data", description = "公司管理列表_删除公司", enabled = true, groups = {"delete"})
    public void deleteCompany(String case_number, String search_company_name, String deleted_company_name, String deleted_affiliation_plate,
                              String deleted_status, String deleted_is_create_account, String company_short, String tax_province,
                              String tax_city, String remark) {
        //前往 资金管理->银行流水管理->公司管理列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowCompanyIndex();

        //删除操作前，记录数据数
        int number_before = Integer.parseInt(driver.findElement(Index.sum).getText());

        //删除
        DeleteCompany deleteAction = new DeleteCompany();
        deleteAction.delete(case_number, search_company_name, deleted_company_name, deleted_affiliation_plate,
                deleted_status, deleted_is_create_account);

        //前往 资金管理->银行流水管理->公司管理列表
        menuAction.gotoFlowCompanyIndex();

        int number_after = Integer.parseInt(driver.findElement(Index.sum).getText());
        //比较数据前后差异数，是否与测试用例中新增成功条数相符
        int number_diff = number_before - number_after;

        Assert.assertEquals(number_diff, 1);
        Reporter.log("删除数据条数校验正常。");
    }

    /**
     * @param case_number
     * @param search_company_name
     * @param deleted_company_name
     * @param deleted_affiliation_plate
     * @param deleted_status
     * @param deleted_is_create_account
     * @param company_short
     * @param tax_province
     * @param tax_city
     * @param remark
     */
    @Test(dataProvider = "data", description = "公司管理列表_校验是否删除信息", enabled = true, groups = {"deleteConfirm"})
    public void confirmTest(String case_number, String search_company_name, String deleted_company_name, String deleted_affiliation_plate,
                            String deleted_status, String deleted_is_create_account, String company_short, String tax_province,
                            String tax_city, String remark) {
        //前往 资金管理->银行流水管理->公司管理列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowCompanyIndex();

        //选择 前面删除的公司名称
        ElementOperation.editInput(driver, Index.company_name, deleted_company_name);
        //点击 搜索，验证是否无数据
        ElementOperation.buttonClick(driver, Index.search);
        ElementOperation.waitForElementPresent(driver, Index.mark);
        Reporter.log("公司删除成功。");
    }

    /**
     * 页面删除数据后，数据库中数据状态只是标记为1，记录仍然存在，需要SQL清除
     *
     * @param case_number
     * @param search_company_name
     * @param deleted_company_name
     * @param deleted_affiliation_plate
     * @param deleted_status
     * @param deleted_is_create_account
     * @param company_short
     * @param tax_province
     * @param tax_city
     * @param remark
     * @throws SQLException
     */
    @Test(dataProvider = "data", description = "公司管理列表_清除测试数据", enabled = true, groups = {"SqlClear"})
    public void clearData(String case_number, String search_company_name, String deleted_company_name, String deleted_affiliation_plate,
                          String deleted_status, String deleted_is_create_account, String company_short, String tax_province,
                          String tax_city, String remark)
            throws SQLException {
        //获取数据库连接
        Connection con = JdbcUtil.getconnection();
        //通过拼接构建SQL语句
        //delete 公司
        String sql01 = "delete from company_list\n" +
                "WHERE status=?\n" +
                "AND company_name = ?\n" +
                "and left(create_at,10) = left(current_date,10)";
        PreparedStatement psql;
        //预处理
        psql = con.prepareStatement(sql01);
        //sql 内参数替换
        psql.setString(1, "0");
        psql.setString(2, deleted_company_name);
        //执行SQL
        psql.executeUpdate();

        //delete 客商代码
        String sql02 = "delete from acct_tag_to_company\n" +
                "where acct_tag_to_company_company_name = ?\n" +
                "and left(acct_tag_to_company_create_at,10) = left(current_date,10)";
        //预处理
        psql = con.prepareStatement(sql02);
        //sql 内参数替换
        psql.setString(1, deleted_company_name);
        //执行SQL
        psql.executeUpdate();

        //关闭数据库连接
        JdbcUtil.close(con);
        Reporter.log("数据已清除。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
