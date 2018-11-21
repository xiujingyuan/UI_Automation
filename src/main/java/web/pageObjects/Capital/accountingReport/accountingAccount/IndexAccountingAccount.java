package web.pageObjects.Capital.accountingReport.accountingAccount;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/10
 * Time: 上午10:44
 * 会计核算->月度报表->配置管理->科目列表
 * 列表
 */
public class IndexAccountingAccount {
    //搜索条件
    public static By account_name = By.id("accountingaccountsearch-accounting_account_name");//科目名称
    public static By account_code = By.id("accountingaccountsearch-accounting_account_code");//科目代码
    public static By account_level = By.id("accountingaccountsearch-accounting_account_level");//请选择等级

    //按钮
    public static By search = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='科目列表'])[4]/following::button[1]");//搜索
    public static By reset = By.linkText("重置");//重置
    public static By create = By.linkText("新增科目");//新增科目

    //小字
    public static By sum = By.xpath("//*[@id=\"w0\"]/div/b[2]");//共XX条数据
    public static By not_found = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td/div");//没有找到数据。

    //列表中按钮、字段
    public static By update = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='更新时间'])[1]/following::a[1]");//更新
    public static By view = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='更新时间'])[1]/following::a[2]");//查看
    public static By delete = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='更新时间'])[1]/following::span[1]");//删除

    public static By text_account_pid = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td[2]");//父级
    public static By text_account_name = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td[5]");//科目名称
    public static By text_account_code = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td[4]");//科目代码
    public static By text_account_memo = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td[6]");//科目说明
}
