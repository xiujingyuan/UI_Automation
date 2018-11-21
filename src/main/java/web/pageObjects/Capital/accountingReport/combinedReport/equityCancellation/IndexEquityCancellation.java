package web.pageObjects.Capital.accountingReport.combinedReport.equityCancellation;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午6:55
 * 会计核算->月度报表->合并报表->股权抵消报表
 * 页面元素定位
 */
public class IndexEquityCancellation {
    //搜索条件
    public static By month = By.name("month");//月份
    public static By diameter = By.name("diameter");//板块

    //按钮
    public static By search = By.name("explode");//搜索
    public static By reset = By.linkText("重置搜索条件");//重置
    public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='重置搜索条件'])[1]/following::button[1]");//下载

    //小字
    public static By found = By.xpath("//*[@id=\"w1\"]/table/tbody/tr[1]/td[2]");//有数据的情况下，表格首行title下存在项目名称
    public static By not_found = By.xpath("//*[@id=\"w1\"]/table/tbody/tr/td/div");//没有找到数据。
}
