package web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午2:38
 * 会计核算->月度报表->合并报表->口径列表
 * 元素定位
 */
public class IndexAcctCombinedPlate {
    //按钮：创建口径
    public static By create_acct_combined_plate = By.linkText("创建口径");
    //链接：口径公司列表
    public static By company_list = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[2]/td[1]/a[2]");

    public static By not_found = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td/div");//没有找到数据
    public static By sum_count = By.xpath("//*[@id=\"w0\"]/div/b[2]");//共XX条数据

    //口径列表下text取值，默认取第一行，执行测试用例前，需删除已存在的口径（因为没翻页、没搜索）
    public static By text_plate_name = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[2]");//口径名称
    public static By text_plate_included = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[3]");//口径包含的板块
    public static By text_short_plate_name = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[4]");//口径简称
    public static By text_diameter_cate = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[5]");//口径简称
}
