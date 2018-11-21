package testCases.Blog.writeBlog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Blog.BlogGarden;
import web.actionObjects.Blog.writeBlog.WriteBlog;
import web.common.BaseAction;
import web.common.ExcelOperation;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Merry as on 2016/11/7.
 */
public class WriteBlogTestCases extends BaseAction {
//    记log日志
    private static final Logger logger = LoggerFactory.getLogger(WriteBlogTestCases.class);
    public void test() {
    }

//    从Excel中读取输入内容
    @DataProvider(name="data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData("","writeBlogs.xlsx");
        return (retObjArr);
    }

    @Test(groups = { "checkintest"},dataProvider = "data")
    public void test(String caseNO,String choose, String title,String content,String postStatus,String checkPoint) {
        String parentWindowId = driver.getWindowHandle();
        BlogGarden blogGarden=new BlogGarden();
        blogGarden.goToWriteBlog();  //打开新的窗口
        Reporter.log("Title：" + driver.getTitle());
        Set<String> allWindowsId = driver.getWindowHandles();
        // 获取所有的打开窗口的句柄
        Iterator<String> it = allWindowsId.iterator();
        while (it.hasNext()) {
            String wind=it.next();
            wait1s();
            if (driver.switchTo().window(wind).getTitle().contains("我的随笔")) {
                driver.switchTo().window(wind);
                Reporter.log("Title：" + driver.getTitle());
                break;
            }
            }
            // window.close();//关闭当前焦点所在的窗口
        WriteBlog writeBlog=new WriteBlog();
        writeBlog.writeBlogTest(driver,caseNO,choose,title,content,postStatus,checkPoint);
        check();// 再次切换回原来的父窗口
        driver.close();
        driver.switchTo().window(parentWindowId);
        wait1s();
        Reporter.log("Title：" + driver.getTitle());

    }
}
