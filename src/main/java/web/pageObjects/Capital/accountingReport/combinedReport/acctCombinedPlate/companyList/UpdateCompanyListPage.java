package web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate.companyList;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午4:22
 * 会计核算->月度报表->合并报表->口径列表->口径公司列表->更新口径公司信息
 * 元素定位
 */
public class UpdateCompanyListPage {
    //截止生效年月
    public static By end_date = By.id("acctdiametercompanyrelation-acct_diameter_company_relation_end_date");
    //按钮：提交
    public static By submit = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='截止生效年月'])[1]/following::button[1]");
}
