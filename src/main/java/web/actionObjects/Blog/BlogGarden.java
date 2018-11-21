package web.actionObjects.Blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Blog.BlogGardenPage;

/**
 * Created by Merry as on 2016/11/4.
 */
public class BlogGarden extends BaseAction {
    private static final Logger logger = LoggerFactory.getLogger(BlogGarden.class);

    public BlogGarden goToFlashMemory() {
        Reporter.log("go to flash memory");
        ElementOperation.buttonClick(driver, BlogGardenPage.flashMemory);
        return this;
    }
    public BlogGarden goToBlog() {
        Reporter.log("go to blog");
        ElementOperation.buttonClick(driver, BlogGardenPage.blog);
        return this;
    }
    public BlogGarden goToWriteBlog() {
        Reporter.log("go to write blog");
        ElementOperation.linkClick(driver, BlogGardenPage.writeBlog);
        return this;
    }

    public void test() {

    }
}
