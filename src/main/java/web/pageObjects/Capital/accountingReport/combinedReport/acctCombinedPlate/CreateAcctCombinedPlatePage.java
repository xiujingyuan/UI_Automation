package web.pageObjects.Capital.accountingReport.combinedReport.acctCombinedPlate;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午2:40
 * 会计核算->月度报表->合并报表->口径列表->创建口径
 * 元素定位
 */
public class CreateAcctCombinedPlatePage {
    //口径名称
    public static By acct_combined_plate_name = By.id("acctcombinedplate-acct_combined_plate_name");
    //口径包含的板块
    public static By plate_included = By.name("acct_combined_plate_plates[]");
    //适用合并报表类型
    public static By diameter_cate = By.name("diameter_cates[]");
    //口径简称
    public static By short_plate_name = By.id("acctcombinedplate-acct_combined_plate_name_flag");

    //按钮：save
    public static By save = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='口径简称'])[1]/following::button[1]");
}
