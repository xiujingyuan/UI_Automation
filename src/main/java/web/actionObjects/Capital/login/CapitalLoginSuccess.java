package web.actionObjects.Capital.login;

import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.login.LoginPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/15
 * Time: 上午11:38
 * 用户名密码登录
 */
public class CapitalLoginSuccess extends BaseAction {
    /**
     * @param username
     * @param password
     */
//    登录操作：输入用户名、密码，点击登录
    public void login(String username, String password) {
        ElementOperation.editInput(driver, LoginPage.capital_userName, username);
        ElementOperation.editInput(driver, LoginPage.capital_password, password);
        ElementOperation.buttonClick(driver, LoginPage.capital_loginButton);
    }

    public void test() {

    }
}
