package web.pageObjects.Capital.flow.company;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/5
 * Time: 下午4:57
 * 资金管理->银行流水管理->公司管理列表->新增公司
 * 页面元素定位
 */
public class AddCompanyPage {
    public static By company_name = By.id("companylist-company_name");//公司名称
    public static By affiliation_plate = By.id("companylist-affiliation_plate");//所属板块

    //公司状态
    public static By status_1 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='正常运营'])[1]/input[1]");//正常运营
    public static By status_2 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='注销'])[1]/input[1]");//注销

    //是否建账
    public static By is_create_account_1 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='已建账'])[1]/input[1]");//已建账
    public static By is_create_account_2 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='暂时无账'])[1]/input[1]");//暂时无账
    public static By is_create_account_3 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='准备注销'])[1]/input[1]");//准备注销
    public static By is_create_account_4 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='暂时不放'])[1]/input[1]");//暂时不放

    public static By company_short = By.id("companylist-company_short");//公司简称

    public static By company_tag = By.id("companylist-acct_tag_to_company_tag");//客商代码
    public static By tax_province = By.id("companylist-tax_province");//请选择所在省
    public static By tax_city = By.id("companylist-tax_city");//请选择所在市

    public static By remark = By.id("companylist-remark");//描述

    public static By button_create = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='描述'])[1]/following::button[1]");//新增

    public static By button_update = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='描述'])[1]/following::button[1]");//更新页面->更新
}
