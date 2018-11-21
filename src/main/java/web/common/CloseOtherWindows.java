package web.common;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/10/8
 * Time: 下午12:26
 * 关闭其他窗口
 */
public class CloseOtherWindows extends BaseAction {

    public void close() {
        String currentWindow = driver.getWindowHandle();

        //关闭所有其他窗口，只保留当前焦点所在窗口
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            driver.switchTo().window(window);
            String m = driver.getWindowHandle();

            if (!m.equals(currentWindow)) {
                driver.close();
            }
        }
    }

    @Override
    public void test() {

    }
}
