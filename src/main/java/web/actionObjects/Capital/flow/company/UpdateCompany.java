package web.actionObjects.Capital.flow.company;

import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.flow.company.AddCompanyPage;
import web.pageObjects.Capital.flow.company.Index;

import static web.common.ElementOperation.radioButtonClick;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/5
 * Time: 下午5:52
 * 资金管理->银行流水管理->公司管理列表->更新公司
 */
public class UpdateCompany extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
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
    public void updateCompany(String case_number, String search_company_name, String company_name, String affiliation_plate,
                              String status, String is_create_account, String company_short, String tax_province,
                              String tax_city, String remark) {
        //选择 更新的公司，点击 搜索
        ElementOperation.editInput(driver, Index.company_name, search_company_name);
        ElementOperation.buttonClick(driver, Index.search);
        wait5s();

        //更改窗口大小，否则定位不到列表右侧的按钮
        driver.manage().window().setSize(new Dimension(1500,1080));

        //点击【更新公司】
        ElementOperation.buttonClick(driver, Index.edit);

        //获取上一个窗口的句柄
        String parentWindowId = driver.getWindowHandle();

        //焦点移至新打开的页面
        String title = "更新公司数据";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //核对 当前页面标题是否包含 更新公司
        driver.getTitle().contains("更新公司");
        Reporter.log("当前页面 = 期望页面");

        //输入 公司名称
        ElementOperation.editInput(driver, AddCompanyPage.company_name, company_name);

        //选择 所属板块
        ElementOperation.selectDropdownValue(driver, AddCompanyPage.affiliation_plate, affiliation_plate);

        //选择 公司状态
        if (status.equals("正常运营")) {
            ElementOperation.radioButtonClick(driver, AddCompanyPage.status_1);
        } else if (status.equals("注销")) {
            ElementOperation.radioButtonClick(driver, AddCompanyPage.status_2);
        }

        //选择 是否建账
        if (is_create_account.equals("已建账")) {
            radioButtonClick(driver, AddCompanyPage.is_create_account_1);
        } else if (is_create_account.equals("暂时无账")) {
            radioButtonClick(driver, AddCompanyPage.is_create_account_2);
        } else if (is_create_account.equals("准备注销")) {
            radioButtonClick(driver, AddCompanyPage.is_create_account_3);
        } else if (is_create_account.equals("暂时不放")) {
            radioButtonClick(driver, AddCompanyPage.is_create_account_4);
        }

        //输入 公司简称
        ElementOperation.editInput(driver, AddCompanyPage.company_short, company_short);

        //选择 所属省
        ElementOperation.selectDropdownValue(driver, AddCompanyPage.tax_province, tax_province);

        //输入 描述
        ElementOperation.editInput(driver, AddCompanyPage.remark, remark);

        //选择 所属市
        ElementOperation.selectDropdownValue(driver, AddCompanyPage.tax_city, tax_city);

        //点击【更新】
        ElementOperation.buttonClick(driver, AddCompanyPage.button_update);
        wait5s();
        Reporter.log("更新操作完成");

        driver.close();
        //切回原来的窗口
        driver.switchTo().window(parentWindowId);
    }
}
