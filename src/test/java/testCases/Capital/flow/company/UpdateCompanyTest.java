package testCases.Capital.flow.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.company.UpdateCompany;
import web.actionObjects.Capital.menu.Menu;
import web.common.*;
import web.pageObjects.Capital.flow.company.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/5
 * Time: 下午6:13
 * 资金管理->银行流水管理->公司管理列表->更新公司
 */
public class UpdateCompanyTest extends BaseAction {
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
     * @param company_name
     * @param affiliation_plate
     * @param status
     * @param is_create_account
     * @param company_short
     * @param tax_province
     * @param tax_city
     * @param remark
     */
    @Test(dataProvider = "data", description = "公司管理列表_更新公司", enabled = true, groups = {"update"})
    public void updateTest(String case_number, String search_company_name, String company_name, String affiliation_plate,
                           String status, String is_create_account, String company_short, String tax_province,
                           String tax_city, String remark) {
        //前往 资金管理->银行流水管理->公司管理列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowCompanyIndex();

        //更新，输入各项值
        UpdateCompany updateAction = new UpdateCompany();
        updateAction.updateCompany(case_number, search_company_name, company_name, affiliation_plate, status,
                is_create_account, company_short, tax_province, tax_city, remark);
    }

    /**
     * @param case_number
     * @param search_company_name
     * @param company_name
     * @param affiliation_plate
     * @param status
     * @param is_create_account
     * @param company_short
     * @param tax_province
     * @param tax_city
     * @param remark
     */
    @Test(dataProvider = "data", description = "公司管理列表_对比更新信息", enabled = true, groups = {"updateConfirm"})
    public void confirmTest(String case_number, String search_company_name, String company_name, String affiliation_plate,
                            String status, String is_create_account, String company_short, String tax_province,
                            String tax_city, String remark) {
        //前往 资金管理->银行流水管理->公司管理列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowCompanyIndex();

        //选择 前面更新的公司名称
        ElementOperation.editInput(driver, Index.company_name, company_name);
        //点击 搜索
        ElementOperation.buttonClick(driver, Index.search);

        //焦点移至新打开的页面
        String title = "公司列表";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //对比
        //更新后读取对应数据
        String[] excel_a = new String[]{
                company_name,
                affiliation_plate,
                status,
                is_create_account,
                company_short,
                tax_city,
                remark
        };

        String[] b = new String[]{
                driver.findElement(Index.text_company_name).getText(),
                driver.findElement(Index.text_affiliation_plate).getText(),
                driver.findElement(Index.text_status).getText(),
                driver.findElement(Index.text_is_create_account).getText(),
                driver.findElement(Index.text_company_short).getText(),
                driver.findElement(Index.text_tax_city).getText(),
                driver.findElement(Index.text_remark).getText(),
        };

        Reporter.log(driver.findElement(Index.text_company_name).getText());
        Reporter.log(driver.findElement(Index.text_affiliation_plate).getText());
        Reporter.log(driver.findElement(Index.text_status).getText());
        Reporter.log(driver.findElement(Index.text_is_create_account).getText());
        Reporter.log(driver.findElement(Index.text_company_short).getText());
        Reporter.log(driver.findElement(Index.text_tax_city).getText());
        Reporter.log(driver.findElement(Index.text_remark).getText());

        Assert.assertEquals(excel_a, b);

        Reporter.log("更新数据正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
