package web.actionObjects.Capital.flow.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.flow.company.AddCompanyPage;
import web.pageObjects.Capital.flow.company.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/5
 * Time: 下午5:07
 * 资金管理->银行流水管理->公司管理列表->新增公司
 */
public class AddCompany extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param company_name
     * @param affiliation_plate
     * @param status
     * @param is_create_account
     * @param company_short
     * @param remark
     * @param result
     */
    public void addCompany(String case_number, String company_name, String affiliation_plate, String status,
                           String is_create_account, String company_short, String company_tag, String tax_province,
                           String tax_city, String remark, String result) {
        //点击【新增公司】
        ElementOperation.buttonClick(driver, Index.create_company);

        //焦点移至新打开的页面
        String title = "新增公司";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //核对 当前页面标题是否为 新增公司
        Assert.assertEquals(driver.getTitle(), "新增公司");
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
            ElementOperation.radioButtonClick(driver, AddCompanyPage.is_create_account_1);
        } else if (is_create_account.equals("暂时无账")) {
            ElementOperation.radioButtonClick(driver, AddCompanyPage.is_create_account_2);
        } else if (is_create_account.equals("准备注销")) {
            ElementOperation.radioButtonClick(driver, AddCompanyPage.is_create_account_3);
        } else if (is_create_account.equals("暂时不放")) {
            ElementOperation.radioButtonClick(driver, AddCompanyPage.is_create_account_4);
        }

        //输入 公司简称、客商代码
        ElementOperation.editInput(driver, AddCompanyPage.company_short, company_short);
        ElementOperation.editInput(driver, AddCompanyPage.company_tag, company_tag);

        //选择 所属省
        ElementOperation.selectDropdownValue(driver, AddCompanyPage.tax_province, tax_province);

        //输入 描述
        ElementOperation.editInput(driver, AddCompanyPage.remark, remark);

        //选择 所属市
        ElementOperation.selectDropdownValue(driver, AddCompanyPage.tax_city, tax_city);

        //点击【新增】
        ElementOperation.buttonClick(driver, AddCompanyPage.button_create);
        Reporter.log("新增操作完成");

        wait3s();

        //焦点移至新打开的页面
        String title_2 = "公司列表";
        switchAction.switchToWindow(title_2);

        //点击 搜索
        ElementOperation.buttonClick(driver, Index.search);
        wait3s();

        Reporter.log("新增成功。");
    }
}
