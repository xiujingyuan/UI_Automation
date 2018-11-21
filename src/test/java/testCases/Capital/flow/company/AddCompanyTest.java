package testCases.Capital.flow.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.company.AddCompany;
import web.actionObjects.Capital.menu.Menu;
import web.common.*;
import web.pageObjects.Capital.flow.company.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/5
 * Time: 下午5:18
 * 资金管理->银行流水管理->公司管理列表->新增公司
 */
public class AddCompanyTest extends BaseAction {
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
                "addCompany.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param company_name
     * @param affiliation_plate
     * @param status
     * @param is_create_account
     * @param company_short
     * @param company_tag
     * @param tax_province
     * @param tax_city
     * @param remark
     * @param result
     */
    @Test(dataProvider = "data", description = "公司管理列表_新增公司", enabled = true, groups = {"add"})
    public void addTest(String case_number, String company_name, String affiliation_plate, String status,
                        String is_create_account, String company_short, String company_tag, String tax_province,
                        String tax_city, String remark, String result) {
        //前往 资金管理->银行流水管理->公司管理列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowCompanyIndex();

        //新增操作前，记录数据数
        int number_before = Integer.parseInt(driver.findElement(Index.sum).getText());

        //新增账户，输入各项值
        AddCompany addAction = new AddCompany();
        addAction.addCompany(case_number, company_name, affiliation_plate, status, is_create_account, company_short,
                company_tag, tax_province, tax_city, remark, result);
        //新增操作后，重新统计数据数
        int number_after = Integer.parseInt(driver.findElement(Index.sum).getText());
        int number_diff = number_after - number_before;
        //Excel中result值为string，转为int再比较
        int r = Integer.parseInt(result);
        // 1.比较数据前后差异数
        Assert.assertEquals(number_diff, 1);
        // 2.新增后总条数是否与测试用例中条数相符
        Assert.assertEquals(number_after, r);
        Reporter.log("新增数据条数校验正常。");
    }

    /**
     * @param case_number
     * @param company_name
     * @param affiliation_plate
     * @param status
     * @param is_create_account
     * @param company_short
     * @param company_tag
     * @param tax_province
     * @param tax_city
     * @param remark
     * @param result
     */
    @Test(dataProvider = "data", description = "公司管理列表_对比新增信息", enabled = true, groups = {"addConfirm"})
    public void confirmTest(String case_number, String company_name, String affiliation_plate, String status,
                            String is_create_account, String company_short, String company_tag, String tax_province,
                            String tax_city, String remark, String result) {
        //前往 资金管理->银行流水管理->公司管理列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowCompanyIndex();

        //选择 前面新增的公司名称
        ElementOperation.editInput(driver, Index.company_name, company_name);
        //点击 搜索
        ElementOperation.buttonClick(driver, Index.search);

        //焦点移至新打开的页面
        String title = "公司列表";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //对比
        //新增后读取对应数据
        String[] excel_a = new String[]{
                company_name,
                company_tag,
                affiliation_plate,
                status,
                is_create_account,
                company_short,
                tax_city,
                remark
        };

        String[] b = new String[]{
                driver.findElement(Index.text_company_name).getText(),
                driver.findElement(Index.text_company_tag).getText(),
                driver.findElement(Index.text_affiliation_plate).getText(),
                driver.findElement(Index.text_status).getText(),
                driver.findElement(Index.text_is_create_account).getText(),
                driver.findElement(Index.text_company_short).getText(),
                driver.findElement(Index.text_tax_city).getText(),
                driver.findElement(Index.text_remark).getText(),
        };

        Reporter.log(driver.findElement(Index.text_company_name).getText());
        Reporter.log(driver.findElement(Index.text_company_tag).getText());
        Reporter.log(driver.findElement(Index.text_affiliation_plate).getText());
        Reporter.log(driver.findElement(Index.text_status).getText());
        Reporter.log(driver.findElement(Index.text_is_create_account).getText());
        Reporter.log(driver.findElement(Index.text_company_short).getText());
        Reporter.log(driver.findElement(Index.text_tax_city).getText());
        Reporter.log(driver.findElement(Index.text_remark).getText());

        Assert.assertEquals(excel_a, b);

        Reporter.log("新增数据正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
