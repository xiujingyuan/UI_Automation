package web.pageObjects.Capital.accountingReport.accountingBalanceVerify;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午3:00
 * 会计核算->月度报表->分析报表->往来账报表->公司余额交叉验证表
 * 列表 元素定位
 */
public class IndexVerify {
    //搜索条件
    public static By create_time = By.name("create_time");//开始日期
    public static By company_id = By.name("company_id");//请选择公司
    public static By is_hide = By.name("is_hide");//显示差异类型
    public static By affiliation_plate = By.name("plate");//请选择板块

    //按钮
    public static By search = By.name("explode");//搜索
    public static By reset = By.linkText("重置搜索条件");//重置
    public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='重置搜索条件'])[1]/following::button[1]");//下载

    //小字
    public static By sum = By.xpath("//*[@id=\"gridView\"]/div[3]/b[2]");//共XX条数据
    public static By not_found = By.xpath("//*[@id=\"gridView-container\"]/table/tbody/tr/td/div");//没有找到数据。
}
