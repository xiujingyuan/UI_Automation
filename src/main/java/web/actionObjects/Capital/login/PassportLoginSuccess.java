package web.actionObjects.Capital.login;

import org.openqa.selenium.WebDriver;
import web.common.ElementOperation;
import web.pageObjects.Capital.login.LoginPage;
/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/17
 * Time: 下午3:40
 * 通行证登录
 */
public class PassportLoginSuccess {
        /**
         *
         * @param driver
         */
        //    企业通行证
        public static void login(WebDriver driver){
                ElementOperation.buttonClick(driver,LoginPage.capital_passportButton);
        }
}