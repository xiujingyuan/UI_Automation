package web.pageObjects.Capital.accountingReport.internalAccounting;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午3:34
 * 会计核算->月度报表->分析报表->往来账报表->集团内单位往来账
 * 列表 元素定位
 */
public class IndexInternalAccounting {
    //搜索条件
    public static By min_create_time = By.name("min_create_time");//开始日期
    public static By max_create_time = By.name("max_create_time");//结束日期
    public static By affiliation_plate = By.xpath("//*[@id=\"clearing-search-form\"]/div[1]/select");//请选择板块
    public static By company_id = By.xpath("//*[@id=\"company-select-div\"]/div/select");//请选择公司

    //按钮
    public static By search = By.name("explode");//搜索
    public static By reset = By.linkText("重置搜索条件");//重置
    public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='重置搜索条件'])[1]/following::button[1]");//下载

    //小字
    public static By sum = By.xpath("//*[@id=\"w0\"]/div/b[2]");//共XX条数据
    public static By not_found = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td/div");//没有找到数据。
}
