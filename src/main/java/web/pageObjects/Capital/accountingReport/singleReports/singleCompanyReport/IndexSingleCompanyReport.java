package web.pageObjects.Capital.accountingReport.singleReports.singleCompanyReport;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午5:07
 * 会计核算->月度报表->单体报表->管报税报公司单体报表
 * 报表 元素定位
 */
public class IndexSingleCompanyReport {
    //搜索条件
    public static By month_start = By.id("month_start");//开始月份
    public static By month_end = By.id("month_end");//截止月份
    public static By company_id = By.name("company_id");//公司
    public static By version = By.name("version");//版本

    //按钮
    public static By search = By.name("explode");//搜索
    public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='搜索'])[1]/following::button[1]");//下载
    public static By reset = By.linkText("重置搜索条件");//重置
    public static By add_entry_batch = By.linkText("添加调整分录");//添加调整分录

    //小字
    public static By found = By.xpath("//*[@id=\"fixed-thead1\"]/tr[1]/th[1]");//报表正常生成的情况下，表格首行有title
}
