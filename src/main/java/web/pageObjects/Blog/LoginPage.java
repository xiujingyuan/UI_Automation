package web.pageObjects.Blog;

import org.openqa.selenium.By;

/**
 * 定义了登录页面的所有元素
 * Created by Merry on 11/3/16.
 */
public class LoginPage {
//    Blog
    public static By loginMark =By.id("Heading");//登录界面标志
    public static By userNameInput =By.id("input1");//用户名
    public static By passwordInput =By.id("input2");//密码
    public static By loginButton =By.id("signin");//登录
    public static By welcomeAreaLink= By.id("header_user_left");//检测跳转页面
}
