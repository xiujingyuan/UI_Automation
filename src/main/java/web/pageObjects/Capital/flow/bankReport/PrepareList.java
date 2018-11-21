package web.pageObjects.Capital.flow.bankReport;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/7
 * Time: 上午11:06
 * 资金管理->银行流水管理->银行流水相关报表->拨备金核对报表
 * 列表元素 定位
 */
public class PrepareList {
    //搜索条件
    public static By trade_date_start = By.id("trade_date_start");//开始日期
    public static By trade_date_end = By.id("trade_date_end");//结束日期

    //小字，共XX条数据
    public static By count = By.xpath("//*[@id=\"grid\"]/div/b[2]");

    //搜索
    public static By search = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='拨备金实际&理论值核对报表'])[3]/following::button[1]");

    //列表中
    public static By first_date = By.xpath("//*[@id=\"grid\"]/table/tbody/tr[1]/td[1]");//首行数据中的日期

    //未找到数据
    public static By unfound = By.xpath("//*[@id=\"grid\"]/table/tbody/tr/td/div");
}
