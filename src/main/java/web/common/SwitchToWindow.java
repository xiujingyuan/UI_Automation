package web.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/20
 * Time: 上午11:43
 * 焦点移至指定title的句柄
 */
public class SwitchToWindow extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void switchToWindow(String title) {
        //焦点移至新打开的页面
        Set<String> presentWindowsId = driver.getWindowHandles();
        Iterator<String> present = presentWindowsId.iterator();
        while (present.hasNext()) {
            String wind = present.next();
            wait1s();
            if (driver.switchTo().window(wind).getTitle().contains(title)) {
                driver.switchTo().window(wind);
                Reporter.log("成功进入页面：" + driver.getTitle());
            }
        }
    }

    @Override
    public void test() {

    }
}
