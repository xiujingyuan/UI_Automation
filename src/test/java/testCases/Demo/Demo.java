package testCases.Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import web.common.ElementOperation;

/**
 * Created by Merry as on 2016/11/2.
 */
public class Demo {

    public void Test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chrome/chromedriver_mac");
        WebDriver driver=new ChromeDriver();//打开谷歌浏览器

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");//窗口最大化
        driver.get("http://test2caiwu.back.so/site/login");//打开财务系统网站
//        driver.get("https://caiwu.kuainiujinke.com/site/login");//打开财务系统网站

        ElementOperation.editInput(driver, By.id("loginform-username"), "test");
        ElementOperation.editInput(driver, By.id("loginform-password"), "123456");
        ElementOperation.buttonClick(driver,By.name("login-button"));
        ElementOperation.waitForElementPresent(driver,  By.className("content"));//验证登录成功
        System.out.println("校验元素内容："+'\n'+driver.findElement(By.className("content")).getText()+'\n');

//        ElementOperation.alertOperation(driver, true);
        Thread.sleep(3000L);
        driver.quit();
}
    public static void main(String[] args) throws InterruptedException {
        System.out.println("##########################");
        Demo t=new Demo();
        t.Test();
        System.out.print("This is a demo"+'\n');
        System.out.println("##########################");


    }

}