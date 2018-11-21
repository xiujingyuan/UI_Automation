package web.pageObjects.Capital.flow.flowBankAccount;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/24
 * Time: 下午7:05
 * 资金管理->银行流水管理->账户管理列表->新增账户 页面元素定位
 */
public class AccountCreatePage {
    public static By company_name = By.id("flowbankaccount-company_name");//公司名称
    public static By bank_short_name = By.id("flowbankaccount-bank_short_name");//所属银行
    public static By account_casher = By.id("flowbankaccount-account_casher");//出纳
    public static By account_accountant = By.id("flowbankaccount-account_accountant");//会计
    public static By bank_account = By.id("flowbankaccount-account_id");//银行账户
    public static By account_name = By.id("flowbankaccount-account_full_name");//账户名
    public static By account_short_name = By.id("flowbankaccount-account_name");//账户简称

    //选填
    public static By credit_code = By.id("flowbankaccount-credit_code");//社会统一信用代码
    public static By organization_code = By.id("flowbankaccount-organization_code");//机构信用代码

    public static By bank_name = By.id("flowbankaccount-bank_name");//开户行
    public static By open_account_date = By.id("flowbankaccount-open_account_date");//开户时间
    //资金性质
    public static By money_pro_1 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='*'])[10]/following::label[1]");//运营资金
    public static By money_pro_2 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='运营资金'])[1]/following::label[1]");//业务资金
    public static By money_pro_3 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='业务资金'])[1]/following::label[1]");//拨备资金

    public static By account_type = By.id("flowbankaccount-account_type");//账户类型

    //账户状态
    public static By account_status_1 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='正常'])[1]/input[1]");//正常
    public static By account_status_2 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='已销户'])[1]/input[1]");//已销户
    public static By account_status_3 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='暂无用途'])[1]/input[1]");//暂无用途

    //是否做为OA代付账户
    public static By is_withdraw_1 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='是否做为OA代付账户'])[1]/following::label[1]");//否
    public static By is_withdraw_2 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='否'])[1]/following::label[1]");//是

    public static By withdraw_account = By.id("flowbankaccount-withdraw_account");//代付账户名
    public static By remark = By.id("flowbankaccount-remark");//备注
    public static By button_submit = By.xpath("//*[@id=\"w0\"]/div[19]/button");//新增

    public static By button_update = By.xpath("//*[@id=\"w0\"]/div[19]/button");//更新页面->更新按钮
}
