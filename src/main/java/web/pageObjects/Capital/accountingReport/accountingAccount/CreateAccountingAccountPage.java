package web.pageObjects.Capital.accountingReport.accountingAccount;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/10
 * Time: 上午10:52
 * 会计核算->月度报表->配置管理->科目列表
 * 新增科目页面
 */
public class CreateAccountingAccountPage {
    //父级分类
    public static By accounting_account_pid = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='父级分类（默认顶级）'])[1]/following::span[1]");
    public static By accounting_account_pid_edit = By.id("accountingaccount-accounting_account_pid");//输入框
    //科目名称
    public static By accounting_account_name = By.id("accountingaccount-accounting_account_name");
    //科目代码
    public static By accounting_account_code = By.id("accountingaccount-accounting_account_code");
    //科目说明
    public static By accounting_account_memo = By.id("accountingaccount-accounting_account_memo");

    //按钮：新增
    public static By button_create = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='科目说明'])[1]/following::button[1]");
}
