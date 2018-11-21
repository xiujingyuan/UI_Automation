package web.pageObjects.Capital.accountingReport.acctTrialBalance;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 上午11:48
 * 会计核算->月度报表->全部科目余额
 * 列表 元素定位
 */
public class IndexPage {
    public static By min_create_time = By.name("min_create_time");//开始日期
    public static By max_create_time = By.name("max_create_time");//结束日期
    public static By company_id = By.name("company_id");//请选择公司
    public static By account_level = By.id("accttrialbalancesearch-accounting_account_level");//请选择等级
    public static By affiliation_plate = By.name("plate");//请选择板块
    public static By accounting_account_code = By.name("accounting_account_code");//科目代码
    public static By acct_trial_balance_name = By.name("acct_trial_balance_name");//科目名称

    public static By search = By.name("explode");//搜索
    public static By reset = By.linkText("重置搜索条件");//重置
    public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='重置搜索条件'])[1]/following::button[1]");//下载
    public static By import_button = By.linkText("导入科目余额");//导入

    public static By sum = By.xpath("//*[@id=\"w0\"]/div/b[2]");//共XX条数据
    public static By not_found = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td/div");//没有找到数据。
}
