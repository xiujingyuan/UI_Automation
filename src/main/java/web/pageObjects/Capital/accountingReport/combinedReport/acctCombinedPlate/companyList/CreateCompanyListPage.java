package web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午4:55
 * 会计核算->月度报表->合并报表->口径列表->口径公司列表->新增口径公司
 * 元素定位
 */
public class CreateCompanyListPage {
    //口径公司
    public static By company_id = By.name("searchBox");
    public static By check_all = By.name("checkAll");//全选
    public static By check_reverse = By.name("checkReverse");//反选
    //开始生效年月
    public static By start_date = By.id("acctdiametercompanyrelation-acct_diameter_company_relation_start_date");
    //截止生效年月
    public static By end_date = By.id("acctdiametercompanyrelation-acct_diameter_company_relation_end_date");

    //按钮：提交
    public static By submit = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='截止生效年月'])[1]/following::button[1]");
}
