package web.pageObjects.Capital.accountingReport.accountingAccount;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/10
 * Time: 上午10:59
 * 会计核算->月度报表->配置管理->科目列表
 * 更新科目页面
 */
public class UpdateAccountingAccountPage {
    //按钮：更新
    public static By button_update = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='科目说明'])[1]/following::button[1]");
}
