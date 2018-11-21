package testCases.Capital.accountingReport.combinedReport.acctCombinedPlate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.CreateAcctCombinedPlate;
import web.actionObjects.Capital.menu.Menu;
import web.common.*;
import web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.IndexAcctCombinedPlate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午3:21
 * 会计核算->月度报表->合并报表->口径列表->创建口径
 */
public class CreateAcctCombinedPlateTest extends BaseAction {
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
                "accountingReport/combinedReport/acctCombinedPlate/",
                "createAcctCombinedPlate.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param acct_combined_plate_name
     * @param plate_included
     * @param short_plate_name
     */
    @Test(dataProvider = "data", description = "口径列表_新增口径", enabled = true, groups = {"add"})
    public void addTest(String case_number, String acct_combined_plate_name, String plate_included, String diameter_cate,
                        String short_plate_name) throws SQLException {
        //测试前清库
        CreateAcctCombinedPlateTest beforeAction = new CreateAcctCombinedPlateTest();
        beforeAction.clearBeforeTest();
        Reporter.log("测试前清除数据成功。");

        //前往 会计核算->月度报表->合并报表->口径列表
        Menu menuAction = new Menu();
        menuAction.gotoAcctCombinedPlateIndex();

        boolean m = ElementOperation.isElementPresent(driver, IndexAcctCombinedPlate.not_found);
        boolean n = ElementOperation.isElementPresent(driver, IndexAcctCombinedPlate.sum_count);

        int number_before = 0;
        if (m) {
            //新增前没数据
            Reporter.log("操作新增口径前，列表无数据");
        } else if (n) {
            //新增前有数据，记录数据数
            number_before = Integer.parseInt(driver.findElement(IndexAcctCombinedPlate.sum_count).getText());
        }

        //新增，输入各项值
        CreateAcctCombinedPlate addAction = new CreateAcctCombinedPlate();
        addAction.create(case_number, acct_combined_plate_name, plate_included, diameter_cate, short_plate_name);

        //新增操作后，重新统计数据数
        int number_after = Integer.parseInt(driver.findElement(IndexAcctCombinedPlate.sum_count).getText());
        //比较数据前后差异数，是否与测试用例中新增成功条数相符
        int number_diff = number_after - number_before;

        Assert.assertEquals(number_diff, 1);
        Reporter.log("新增数据条数校验正常。");
    }

    /**
     * @param acct_combined_plate_name
     * @param plate_included
     * @param short_plate_name
     */
    @Test(dataProvider = "data", description = "口径列表_对比新增信息", enabled = true, groups = {"addConfirm"})
    public void confirmTest(String case_number, String acct_combined_plate_name, String plate_included, String diameter_cate,
                            String short_plate_name) {
        //对比
        //新增后读取对应数据
        String[] excel_a = new String[]{
                acct_combined_plate_name,
                plate_included,
                short_plate_name,
                diameter_cate
        };

        String[] b = new String[]{
                driver.findElement(IndexAcctCombinedPlate.text_plate_name).getText(),
                driver.findElement(IndexAcctCombinedPlate.text_plate_included).getText(),
                driver.findElement(IndexAcctCombinedPlate.text_short_plate_name).getText(),
                driver.findElement(IndexAcctCombinedPlate.text_diameter_cate).getText(),
        };
        Reporter.log(driver.findElement(IndexAcctCombinedPlate.text_plate_name).getText());
        Reporter.log(driver.findElement(IndexAcctCombinedPlate.text_plate_included).getText());
        Reporter.log(driver.findElement(IndexAcctCombinedPlate.text_short_plate_name).getText());
        Reporter.log(driver.findElement(IndexAcctCombinedPlate.text_diameter_cate).getText());

        Assert.assertEquals(excel_a, b);

        Reporter.log("新增数据正常。");
    }

    @Test(dataProvider = "data", description = "口径列表_清除新增的口径数据", enabled = true, groups = {"SqlClear"})
    public void clearAddData(String case_number, String acct_combined_plate_name, String plate_included, String diameter_cate,
                             String short_plate_name) throws SQLException {
        //获取数据库连接
        Connection con = JdbcUtil.getconnection();
        //通过拼接构建SQL语句
        //delete  acct_combined_plate数据
        String sql = "delete from acct_combined_plate " +
                "where acct_combined_plate_name = ? " +
                "and acct_combined_plate_name_flag = ? " +
                "and left(acct_combined_plate_create_at,10) = left(CURRENT_DATE,10)";
        PreparedStatement psql;
        //预处理
        psql = con.prepareStatement(sql);
        psql.setString(1, acct_combined_plate_name);
        psql.setString(2, short_plate_name);
        //执行SQL
        psql.executeUpdate();
        Reporter.log("acct_combined_plate表数据清除成功。");

        //delete  acct_diameter_cate数据
        String sql02 = "delete from acct_diameter_cate where left(acct_diameter_cate_create_at,10) = left(current_date,10)";
        //预处理
        psql = con.prepareStatement(sql02);

        //执行SQL
        psql.executeUpdate();
        Reporter.log("acct_diameter_cate表数据清除成功。");

        //关闭数据库连接
        JdbcUtil.close(con);
        Reporter.log("新增测试数据清除成功。");

        //测试后恢复数据
        CreateAcctCombinedPlateTest afterAction = new CreateAcctCombinedPlateTest();
        afterAction.recoverAfterTest();
        Reporter.log("测试后恢复数据成功。");
    }

    /**
     * 新增测试前，清库，保证后续页面取数对比
     *
     * @throws SQLException
     */
    public void clearBeforeTest() throws SQLException {
        //获取数据库连接
        Connection con = JdbcUtil.getconnection();
        //通过拼接构建SQL语句
        //delete  acct_combined_plate数据
        String sql = "delete from acct_combined_plate ";
        PreparedStatement psql;
        //预处理
        psql = con.prepareStatement(sql);
        //执行SQL
        psql.executeUpdate();
        Reporter.log("acct_combined_plate表数据清除成功。");

        //删除acct_diameter_cate表数据
        String cate_sql = "DELETE FROM `acct_diameter_cate`";
        psql = con.prepareStatement(cate_sql);
        psql.executeUpdate();
        Reporter.log("acct_diameter_cate表数据清除成功");
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
        //delete  acct_combined_plate数据
        String sql = "INSERT INTO " +
                "`acct_combined_plate` (`acct_combined_plate_id`, `acct_combined_plate_name`, " +
                "`acct_combined_plate_plates`, `acct_combined_plate_name_flag`, `acct_combined_plate_create_at`, " +
                "`acct_combined_plate_update_at`, `acct_combined_plate_update_user_id`, " +
                "`acct_combined_plate_update_username`)\n" +
                "VALUES\n" +
                "\t(1, '快牛（现金贷）', '1', 'A31', '2018-06-20 16:06:25', '2018-06-20 16:06:41', 0, 'sys'),\n" +
                "\t(2, '快牛板块+云智', '1;2', 'A32', '2018-06-20 16:06:25', '2018-06-21 00:23:32', 0, 'sys'),\n" +
                "\t(3, '快牛板块+钱牛牛板块', '1;15', 'AB1', '2018-06-20 16:06:25', '2018-06-21 00:23:32', 0, 'sys'),\n" +
                "\t(4, '快牛板块+钱牛牛板块+云智', '1;2;15', 'AB2', '2018-06-20 16:06:25', '2018-06-21 00:23:32', 0, 'sys'),\n" +
                "\t(5, '钱牛牛板块', '15', 'B11', '2018-06-20 16:06:25', '2018-06-21 00:23:32', 0, 'sys');";
        PreparedStatement psql;
        //预处理
        psql = con.prepareStatement(sql);
        //执行SQL
        psql.executeUpdate();
        Reporter.log("acct_combined_plate表数据恢复成功。");

        String cate_sql = "INSERT INTO `acct_diameter_cate` (`acct_diameter_cate_id`, `acct_diameter_cate_diameter_id`, `acct_diameter_cate_cate_key`, `acct_diameter_cate_create_at`, `acct_diameter_cate_update_at`)\n" +
                "VALUES\n" +
                "\t(31, 1, 'combined', '2018-11-09 01:21:55', '2018-11-09 01:21:55'),\n" +
                "\t(32, 1, 'combined_capital_derivation', '2018-11-09 01:21:55', '2018-11-09 01:21:55'),\n" +
                "\t(33, 1, 'combined_a1a2a3', '2018-11-09 01:21:55', '2018-11-09 01:21:55'),\n" +
                "\t(34, 1, 'combined_variation_analysis', '2018-11-09 01:21:55', '2018-11-09 01:21:55'),\n" +
                "\t(35, 2, 'combined', '2018-11-09 01:31:24', '2018-11-09 01:31:24'),\n" +
                "\t(36, 2, 'combined_capital_derivation', '2018-11-09 01:31:24', '2018-11-09 01:31:24'),\n" +
                "\t(37, 2, 'combined_a1a2a3', '2018-11-09 01:31:24', '2018-11-09 01:31:24'),\n" +
                "\t(38, 2, 'combined_variation_analysis', '2018-11-09 01:31:24', '2018-11-09 01:31:24'),\n" +
                "\t(39, 3, 'combined', '2018-11-09 01:31:31', '2018-11-09 01:31:31'),\n" +
                "\t(40, 3, 'combined_capital_derivation', '2018-11-09 01:31:31', '2018-11-09 01:31:31'),\n" +
                "\t(41, 3, 'combined_a1a2a3', '2018-11-09 01:31:31', '2018-11-09 01:31:31'),\n" +
                "\t(42, 3, 'combined_variation_analysis', '2018-11-09 01:31:31', '2018-11-09 01:31:31'),\n" +
                "\t(43, 4, 'combined', '2018-11-09 01:31:38', '2018-11-09 01:31:38'),\n" +
                "\t(44, 4, 'combined_capital_derivation', '2018-11-09 01:31:38', '2018-11-09 01:31:38'),\n" +
                "\t(45, 4, 'combined_a1a2a3', '2018-11-09 01:31:38', '2018-11-09 01:31:38'),\n" +
                "\t(46, 4, 'combined_variation_analysis', '2018-11-09 01:31:38', '2018-11-09 01:31:38'),\n" +
                "\t(47, 5, 'combined', '2018-11-09 01:31:45', '2018-11-09 01:31:45'),\n" +
                "\t(48, 5, 'combined_capital_derivation', '2018-11-09 01:31:45', '2018-11-09 01:31:45'),\n" +
                "\t(49, 5, 'combined_a1a2a3', '2018-11-09 01:31:45', '2018-11-09 01:31:45'),\n" +
                "\t(50, 5, 'combined_variation_analysis', '2018-11-09 01:31:45', '2018-11-09 01:31:45');\n";
        psql = con.prepareStatement(cate_sql);
        psql.executeUpdate();
        Reporter.log("acct_diameter_cate表数据恢复成功");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
