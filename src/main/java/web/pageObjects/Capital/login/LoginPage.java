package web.pageObjects.Capital.login;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/17
 * Time: 下午3:40
 * 登录页面
 */
public class LoginPage {
    //    capital
    public static By capital_unloginMark = By.className("login-box-msg"); //未登录标记-【请登录】
    public static By capital_userName = By.id("loginform-username"); //用户名
    public static By capital_password = By.id("loginform-password"); //密码
    public static By capital_loginButton = By.name("login-button");//登录 按钮
    public static By capital_passportButton = By.xpath("//a[@href=\"/fond/user/auth?authclient=fond\"]");//企业通行证登录 按钮
    public static By capital_loginMark = By.className("hidden-xs");//检测跳转页面，登录成功后右上角登录名显示
    public static By capital_loginSuccess = By.id("w0-success");//登录成功后，页面上的文案提示
    public static By capital_menuText = By.xpath("/html/body/div[1]/div/section[2]/section/div/h2");//登录后，主页面显示文案
}
