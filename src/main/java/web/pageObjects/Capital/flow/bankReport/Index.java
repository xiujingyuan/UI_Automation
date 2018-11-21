package web.pageObjects.Capital.flow.bankReport;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/7
 * Time: 上午11:06
 * 资金管理->银行流水管理->银行流水相关报表->资金日报报表
 * 列表元素 定位
 */
public class Index {
    //搜索条件
    public static By affiliation_plate = By.name("plate");//请选择板块类型
    public static By min_trade_date = By.name("min_trade_date");//交易开始日期
    public static By max_trade_date = By.name("max_trade_date");//交易结束日期

    //按钮
    public static By search = By.name("export");//搜索
    public static By reset = By.linkText("重置搜索条件");//重置搜索条件
    public static By export = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='重置搜索条件'])[1]/following::button[1]");//导出

    //小字，共XX条数据
    public static By sum_count = By.xpath("//*[@id=\"w0\"]/div/b[2]");

    //列表中
    public static By first_date = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[1]");//首行数据中的日期

    //未找到数据
    public static By unfound = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td/div");
}
