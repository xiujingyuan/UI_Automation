package web.pageObjects.Capital.accountingReport.combinedReport.diameterCombinedReport.diameterCombinedCompanyReport;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 上午10:58
 * 会计核算->月度报表->合并报表->管报税报公司合并报表->查看明细数据
 * 列表元素定位
 */
public class IndexCompanyReport {
    //搜索条件
    public static By month_start = By.name("month_start");//开始月份
    public static By month_end = By.name("month_end");//截止月份
    public static By diameter = By.name("diameter");//板块
    public static By version = By.name("version");//版本

    //按钮
    public static By search = By.name("explode");//搜索
    public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='搜索'])[1]/following::button[1]");//下载
    public static By reset = By.linkText("重置搜索条件");//重置

    //小字
    public static By found = By.xpath("//*[@id=\"fixed-thead1\"]/tr[1]/th[1]");//有数据的情况下，表格首行title下存在月度损益表
}
