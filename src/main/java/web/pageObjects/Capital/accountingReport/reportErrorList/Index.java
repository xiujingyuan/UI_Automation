package web.pageObjects.Capital.accountingReport.reportErrorList;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/7
 * Time: 下午3:11
 * 会计核算->月度报表->分析报表->异常统计页
 * 列表元素 定位
 */
public class Index {
    //搜索条件
    public static By cate = By.name("cate");
    public static By version = By.name("version");

    //按钮
    public static By search = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='报表校验异常统计界面'])[3]/following::button[1]");
    public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='搜索'])[1]/following::button[1]");
    public static By reset = By.linkText("重置搜索条件");

    //总数，共XX条数据
    public static By count = By.xpath("//*[@id=\"w1\"]/div/b[2]");

    //未找到数据
    public static By not_found = By.xpath("//*[@id=\"w1\"]/table/tbody/tr/td/div");
}
