package web.pageObjects.Capital.accountingReport.combinedReport.relatedPartyCancellation;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午4:05
 * 会计核算->月度报表->合并报表->关联方来往抵消报表
 * 查看明细 列表元素定位
 */
public class DetailRelatedPartyCancellation {
    //搜索条件
    public static By month = By.name("month");//月份
    public static By diameter = By.name("diameter");//板块

    //按钮
    public static By search = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='关联方往来明细'])[3]/following::button[1]");//搜索
    public static By reset = By.linkText("重置搜索条件");//重置

    //小字
    public static By found = By.xpath("//*[@id=\"w1\"]/table/caption");//有数据的情况下，表格首行有title
    public static By not_found = By.xpath("//*[@id=\"w1\"]/table/tbody/tr/td/div");//没有找到数据。
}
