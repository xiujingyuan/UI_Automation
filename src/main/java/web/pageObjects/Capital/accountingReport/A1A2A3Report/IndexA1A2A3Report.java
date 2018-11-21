package web.pageObjects.Capital.accountingReport.A1A2A3Report;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午2:58
 * 会计核算->月度报表->单体报表->单体A1A2A3报表
 */
public class IndexA1A2A3Report {
    //搜索条件
    public static By month = By.name("month");//月份
    public static By company_id = By.name("company_id");//公司

    //按钮
    public static By search = By.name("explode");//搜索
    public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='搜索'])[1]/following::button[1]");//下载
    public static By reset = By.linkText("重置搜索条件");//重置

    //小字
    public static By found = By.xpath("//*[@id=\"w1\"]/table/thead/tr/th[1]");//报表正常生成的情况下，表格首行有title
    public static By not_found = By.xpath("//*[@id=\"w1\"]/table/tbody/tr/td/div");//没有找到数据。
}