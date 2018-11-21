package web.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

/**
 * WebDriver工厂类,根据project.properties中配置的browser来生成相应的WebDriver
 * Created by bean on 8/23/16.
 */
public class WebDrivers {

    private static final Logger logger = LoggerFactory.getLogger(WebDrivers.class);

    private static WebDriver driver;

    public static WebDriver getWebDriver() {
        return setDriver();
    }

    /**
     * 根据Configurator配置的browserName来获取相应的WebDriver
     */
    public static WebDriver setDriver() {
        if (driver != null) {
            return driver;
        }
        String browserName = Configurator.getBrowserName();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName(browserName);
        driver = setDriver(desiredCapabilities);
        return driver;
    }

    private static WebDriver setDriver(DesiredCapabilities desiredCapabilities) {
        String browser = desiredCapabilities.getBrowserName();
        String osname = System.getProperty("os.name");
        String arch = "";
        if (browser.toLowerCase().equals(BrowserType.CHROME)) {
            return setChromeDriver(desiredCapabilities, osname, arch);
        } else if (browser.toLowerCase().equals(BrowserType.FIREFOX)) {
            return setFireFoxDriver(desiredCapabilities, osname, arch);
        } else if (browser.toLowerCase().equals(BrowserType.IE)) {
            return setIEDriver(desiredCapabilities, osname, arch);
        } else {
            logger.warn(browser + " is NOT SUPPORTED!!! Use IE as default!");
            return setIEDriver(desiredCapabilities, osname, arch);
        }
    }

    private static WebDriver setChromeDriver(DesiredCapabilities desiredCapabilities, String osname, String arch) {
        String driverpath = "src/main/resources/drivers/chrome/";
        if (osname.equals("Mac OS X")) {
            driverpath += "chromedriver_mac";
        } else if (osname.equals("linux")) {
            if (arch.equals("x86_64")) {
                driverpath += "chromedriver_linux64";
            } else {
                driverpath += "chromedriver_linux32";
            }
        } else {
            //as windows
            driverpath += "chromedriver.exe";
        }

        Reporter.log("set Chrome driver: ostype " + osname + ", arch " + arch);
        System.setProperty("webdriver.chrome.driver", driverpath);
        driver = new ChromeDriver(desiredCapabilities);
        return driver;
    }

    private static WebDriver setFireFoxDriver(DesiredCapabilities desiredCapabilities, String osname, String arch) {
        String driverpath = "src/main/resources/drivers/firefox/";
        if (osname.equals("Mac OS X")) {
            driverpath += "geckodriver_mac";
        } else if (osname.equals("linux")) {
            driverpath += "geckodriver_linux64";
        } else {
            //as windows
            driverpath += "geckodriver.exe";
        }
        Reporter.log("set FIREFOX driver: ostype " + osname + ", arch " + arch);
        System.setProperty("webdriver.gecko.driver", driverpath);
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        driver = new FirefoxDriver(new FirefoxBinary(), profile, desiredCapabilities);
//        driver = new FirefoxDriver(desiredCapabilities);
        return driver;
    }

    /**
     * 设置IE driver,取消保护模式
     * @param desiredCapabilities
     * @return
     */
    private static WebDriver setIEDriver(DesiredCapabilities desiredCapabilities, String osname, String arch) {
        String driverpath = "src/main/resources/drivers/ie/";
        if (arch.equals("64")) {
            driverpath += "IEDriverServer_x64.exe";
        } else {
            //as 32bit arch
            driverpath += "IEDriverServer_32.exe";
        }
        Reporter.log("set IE driver: ostype " + osname + ", arch " + arch);
        System.setProperty("webdriver.ie.driver", driverpath);
        desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        driver = new InternetExplorerDriver(desiredCapabilities);
        return driver;
    }
}
