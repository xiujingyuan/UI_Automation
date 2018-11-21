package web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午2:59
 * 会计核算->月度报表->合并报表->口径列表->口径公司列表
 * 元素定位
 */
public class IndexCompanyList {
    //搜索条件
    public static By start_date = By.name("start_date");//开始年月
    public static By end_date = By.name("end_date");//截止年月

    //按钮
    public static By search = By.xpath("//*[@id=\"w0\"]/div[2]/button[1]");//搜索
    public static By reset = By.linkText("重置搜索条件");//重置
    public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='重置搜索条件'])[1]/following::button[1]");//导出
    public static By create_company = By.linkText("新增口径公司");//新增口径公司
    public static By update_company = By.xpath("//*[@id=\"w1\"]/table/tbody/tr[1]/td[1]/a");//更新

    public static By not_found = By.xpath("//*[@id=\"w1\"]/table/tbody/tr/td/div");//没有找到数据。
    public static By sum = By.xpath("//*[@id=\"w1\"]/div/b[2]");//共xx条数据

    public static By text_company_name = By.xpath("//*[@id=\"w1\"]/table/tbody/tr[1]/td[2]");//首行标题下，公司名称
    public static By text_company_plate = By.xpath("//*[@id=\"w1\"]/table/tbody/tr[1]/td[3]");//首行标题下，公司板块
    public static By text_start_date = By.xpath("//*[@id=\"w1\"]/table/tbody/tr[1]/td[4]");//首行标题下，生效开始年月
    public static By text_end_date = By.xpath("//*[@id=\"w1\"]/table/tbody/tr[1]/td[5]");//首行标题下，生效结束年月

}
