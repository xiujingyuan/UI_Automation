package web.pageObjects.Capital.accountingReport.cashTransactionReport;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午4:29
 * 会计核算->月度报表->分析报表->现金流量表
 * 报表 元素定位
 */
public class IndexCashTransactionReport {
    //搜索条件
    public static By month = By.name("month");//开始日期
    public static By company = By.name("company_id");//公司

    //按钮
    public static By search = By.name("explode");//搜索
    public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='搜索'])[1]/following::button[1]");//下载
    public static By reset = By.linkText("重置搜索条件");//重置

    //小字
    public static By income_sum = By.xpath("//*[@id=\"w1\"]/table/tbody/tr[2]/td[5]");//报表未生成情况下，销售商品，提供劳务收到的现金a合计数据为0
}
