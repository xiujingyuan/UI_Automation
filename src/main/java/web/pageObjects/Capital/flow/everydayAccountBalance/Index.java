package web.pageObjects.Capital.flow.everydayAccountBalance;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/30
 * Time: 上午11:08
 * 资金管理->银行流水管理->账户余额列表 页面元素定位
 */
public class Index {
    //搜索条件
    public static By account_balance_account_id = By.id("floweverydayaccountbalancesearch-flow_everyday_account_balance_account_id");//银行账号
    public static By account_balance_company_short = By.id("floweverydayaccountbalancesearch-flow_everyday_account_balance_company_short");//公司名
    public static By account_balance_affiliation_plate = By.name("plate");//请选择板块类型
    public static By min_trade_date = By.name("min_trade_date");//交易开始日期
    public static By max_trade_date = By.name("max_trade_date");//交易结束日期
    public static By account_balance_error_status = By.id("floweverydayaccountbalancesearch-flow_everyday_account_balance_error_status");//数据状态
    public static By account_balance_money_type = By.name("flow_everyday_account_balance_money_type");//资金类型
    //按钮
    public static By search = By.name("export");//搜索
    public static By reset_search = By.linkText("重置搜索条件");//重置搜索条件
    public static By export = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='重置搜索条件'])[1]/following::button[1]");//导出
    //表头小字
    public static By first_balance = By.xpath("//*[@id=\"w0\"]/strong[1]");//期初人民币余额合计
    public static By end_balance = By.xpath("//*[@id=\"w0\"]/strong[2]");//期末人民币余额合计

    public static By not_found = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td/div");//没有找到数据。
}
