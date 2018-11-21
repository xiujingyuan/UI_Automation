package web.common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * 该类定义了对所有单个页面元素的操作和验证等 ，包括点击按钮 ，点击 链接，在输入框输入内容等等
 * Created by Merry as on 2016/11/1.
 */
public class ElementOperation extends BaseOps {
    private static final Logger logger = LoggerFactory.getLogger(ElementOperation.class);
    private static WebElement element = null;

    //this function is to define web button object and do click action.
    public static void buttonClick(WebDriver driver, By locatorValue) {
        element = driver.findElement(locatorValue);
        waitForElementEnabled(element);
        element.click();
    }

    //this function is to click a radio button.
    public static void radioButtonClick(WebDriver driver, By locatorValue) {
        element = driver.findElement(locatorValue);
        waitForElementEnabled(element);
        element.click();
    }

    //this function is to click a check button.
    public static void checkButtonCheck(WebDriver driver, By locatorValue) {
        element = driver.findElement(locatorValue);
        waitForElementEnabled(element);
        element.click();
    }

    //to click and hold on an element
    public static void clickAndHold(WebDriver driver, By locatorValue) {
        Actions action = new Actions(driver);
        // 鼠标左键在当前停留的位置做单击操作
        action.clickAndHold(driver.findElement(locatorValue)).moveByOffset(1, 1).perform();
    }

    //doubleClick
    public static void doubleClick(WebDriver driver, By locatorValue) {
        element = driver.findElement(locatorValue);
        waitForElementEnabled(element);
        Actions action = new Actions(driver);
        // 鼠标左键在当前停留的位置做单击操作
        action.doubleClick(element);
    }

    //to move cursor to the element
    public static void moveToElement(WebDriver driver, By locatorValue) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(locatorValue)).perform();
    }

    // 下拉框中选择目标选项，locatorValue：选择框定位，selectValue：选项值
    public static void selectDropdownValue(WebDriver driver, By locatorValue, String selectValue) {
        element = driver.findElement(locatorValue);
        new Select(element).selectByVisibleText(selectValue);
    }

    // 取当前选中项的值，locatorValue：选择框定位，selectValue：选项值
    public static String getSelectedValue(WebDriver driver, By locatorValue) {
        element = driver.findElement(locatorValue);
        String selectedValue = new Select(element).getFirstSelectedOption().getText();
        return selectedValue;
    }

    // 输入框中输入内容，输入前清空框内内容
    public static void editInput(WebDriver driver, By locatorValue, String inputValue) {
        element = driver.findElement(locatorValue);
        waitForElementEnabled(element);
        element.clear();
        element.sendKeys(inputValue);
    }

    // 输入框中 纯输入内容
    public static void editOnly(WebDriver driver, By locatorValue, String inputValue) {
        element = driver.findElement(locatorValue);
        waitForElementEnabled(element);
        element.sendKeys(inputValue);
    }

    // 搜索带输入框时，输入框中输入内容 并 点击
    public static void InputClick(WebDriver driver, By locatorValue, String inputValue) {
        element = driver.findElement(locatorValue);
        waitForElementEnabled(element);
        element.clear();
        element.sendKeys(inputValue);
        element.click();
    }

    //this function is to define a link object and do click action.
    public static void linkClick(WebDriver driver, By locatorValue) {
        element = driver.findElement(locatorValue);
        waitForElementEnabled(element);
        element.click();
    }

    //this function is to define a image object and do click action.
    public static void clickImage(WebDriver driver, By locatorValue) {
        element = driver.findElement(locatorValue);
        waitForElementEnabled(element);
        element.click();
    }

    public static void waitForText(WebDriver driver, By locatorValue, String textValue) {
        for (int second = 0; ; second++) {
            if (second >= Integer.parseInt(TestDataHelper.getString("waitTime"))) Assert.fail("waitForText timeout");
            wait1s();
            try {
                element = driver.findElement(locatorValue);
                if (element.getText().trim().contains(textValue) || textValue.equals(element.getText().trim())) break;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public static void waitForElementEnabled(WebElement element) {
        for (int second = 0; ; second++) {
            if (second >= Integer.parseInt(TestDataHelper.getString("waitTime"))) fail("waitForElementEnabled timeout");
            wait1s();
            if (element.isEnabled()) break;
        }

    }

    public static void waitForElementPresent(WebDriver driver, By locatorValue) {
        for (int second = 0; ; second++) {
            if (second >= Integer.parseInt(TestDataHelper.getString("waitTime"))) fail("waitForElementPresent timeout");
            wait1s();
            if (isElementPresent(driver, locatorValue)) break;
        }

    }


    public static void waitForElementPresentNotFail(WebDriver driver, By locatorValue) {
        for (int second = 0; second < Integer.parseInt(TestDataHelper.getString("waitTime")); second++) {
            wait1s();
            if (isElementPresent(driver, locatorValue)) break;
        }
    }

    public static void waitForElementNotPresent(WebDriver driver, By locatorValue) {
        for (int second = 0; ; second++) {
            if (second >= Integer.parseInt(TestDataHelper.getString("waitTime")))
                fail("waitForElementNotPresent timeout");
            wait1s();
            if (!isElementPresent(driver, locatorValue)) break;


        }

    }

    public static void waitForElementDisplayed(WebDriver driver, By locatorValue) {
        for (int second = 0; ; second++) {
            if (second >= Integer.parseInt(TestDataHelper.getString("waitTime")))
                fail("waitForElementDisplayed timeout");
            wait1s();
            if (driver.findElement(locatorValue).isDisplayed()) break;
        }

    }

    public static void waitForElementNotDisplayed(WebDriver driver, By locatorValue) {
        for (int second = 0; ; second++) {
            if (second >= Integer.parseInt(TestDataHelper.getString("waitTime")))
                fail("waitForElementNotDisplayed timeout");
            wait1s();
            if (!driver.findElement(locatorValue).isDisplayed()) break;

        }

    }

    public static boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public static void waitForAlertPresent(WebDriver driver) {
        for (int second = 0; second < Integer.parseInt(TestDataHelper.getString("waitTime")); second++) {
            wait1s();
            if (isAlertPresent(driver)) break;
        }

    }

    public static void alertOperation(WebDriver driver, boolean confirm) {
        waitForAlertPresent(driver);
        if (isAlertPresent(driver)) {
            if (confirm == true)
                driver.switchTo().alert().accept();
            else
                driver.switchTo().alert().dismiss();
        }
    }

    public static void doFilter(ServletRequest request)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding("utf-8");
        String url = req.getRequestURL().toString();
        System.out.println(url);
    }

    public static void closeAlertAndGetItsText(WebDriver driver, String alt_text) {
        String m = driver.switchTo().alert().getText();
        if (m.equals(alt_text)) {
            driver.switchTo().alert().accept();
            Reporter.log("选项：确认");
        } else {
            driver.switchTo().alert().dismiss();
            Reporter.log("选项：取消");
        }
    }
}


