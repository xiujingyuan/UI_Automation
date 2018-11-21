package web.pageObjects.Capital.accountingReport.combinedReport.relatedPartyCancellation;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午3:50
 * 会计核算->月度报表->合并报表->关联方来往抵消报表
 * 列表元素定位
 */
public class IndexRelatedPartyCancellation {
    //搜索条件
    public static By month = By.name("month");//月份
    public static By company_id = By.name("company_id");//公司

    //按钮
    public static By search = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='关联方来往抵消报表'])[4]/following::button[1]");//搜索
    public static By reset = By.linkText("重置搜索条件");//重置
    public static By download = By.name("explode");//下载

    //列表中，首个查看明细
    public static By view = By.linkText("查看明细");

    //小字
    public static By found = By.xpath("//*[@id=\"w1\"]/table/caption");//有数据的情况下，表格首行有title
    public static By not_found = By.xpath("//*[@id=\"w1\"]/table/tbody/tr/td/div");//没有找到数据。
}
