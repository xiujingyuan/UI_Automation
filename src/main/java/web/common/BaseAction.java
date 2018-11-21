package web.common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Merry on 4/11/16.
 */
public abstract class BaseAction extends BaseOps{
    public WebDriver driver = WebDrivers.getWebDriver();
    public static String caseId;
    public String caseDescription;

    public static List<CheckPoint> checkPoints = new ArrayList<CheckPoint>();

    public abstract void test();

    //set check points
    public static void setCheckPoint(WebDriver driver, By locatorValue, int index, String expectation){
            WebElement we = null;
            List<WebElement> wes = null;
        String res = "";
        try {
            if (index == 0)
                we = driver.findElement(locatorValue);
            else
                wes = driver.findElements(locatorValue);
            if (we != null) {
                res = we.getText().trim();
            } else {
                res = wes.get(index-1).getText().trim();
            }
        } catch (Exception e) {
            res = null;
        }

        CheckPoint cp = new CheckPoint();
        cp.caseId = caseId;
        cp.actual = res;
        cp.expectation = expectation;
        checkPoints.add(cp);
    }


    public void check() {
        for (int i=0; i<checkPoints.size(); i++) {
            checkPoints.get(i).validate();
        }
        checkPoints = new ArrayList<CheckPoint>();
    }

}
