package web.actionObjects.Blog;

import org.openqa.selenium.WebDriver;
import web.common.ElementOperation;
import web.pageObjects.Blog.LoginPage;

/**
 * Created by Merry as on 2016/11/3.
 */
public class LoginSuccessful {
    public static void login(WebDriver driver, String user, String password){
        ElementOperation.editInput(driver, LoginPage.userNameInput, user);
        ElementOperation.editInput(driver, LoginPage.passwordInput, password);
        ElementOperation.buttonClick(driver,LoginPage.loginButton);
    }

}
