package web.actionObjects.Blog.writeBlog;

import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Blog.writeBlog.WriteBlogPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Merry as on 2016/11/7.
 */
public class WriteBlog extends BaseAction {
    private static final Logger logger = LoggerFactory.getLogger(WriteBlog.class);
    public static void writeBlogTest(WebDriver driver, String caseNO,String choose, String title, String content,String postStatus,String check) {
        if (choose.equals("随笔")) {
            ElementOperation.linkClick(driver, WriteBlogPage.posts);
            ElementOperation.linkClick(driver, WriteBlogPage.newposts);
            ElementOperation.editInput(driver, WriteBlogPage.newpostsTitle, title);
            driver.switchTo().frame(WriteBlogPage.newpostsContentFrame);
            ElementOperation.editInput(driver, WriteBlogPage.newpostsContent, content);
            driver.switchTo().defaultContent();
               if (postStatus.equals("发布")) {
                   ElementOperation.buttonClick(driver, WriteBlogPage.saveposts);
                   ElementOperation.waitForElementPresent(driver,WriteBlogPage.checkPostSuccess);
                   setCheckPoint(driver,WriteBlogPage.checkPostSuccess,0,check);

               } else if(postStatus.equals("存为草稿")) {
                   ElementOperation.buttonClick(driver, WriteBlogPage.savepostsAsDraft);
                   ElementOperation.waitForElementPresent(driver,WriteBlogPage.checkPostAsDraft);
                   setCheckPoint(driver,WriteBlogPage.checkPostAsDraft,0,check);

               } else if(postStatus.equals("取消")) {
                   ElementOperation.buttonClick(driver, WriteBlogPage.cancelposts);
                   ElementOperation.alertOperation(driver, true);
                   ElementOperation.waitForElementPresent(driver,WriteBlogPage.checkPostCancel);
                   setCheckPoint(driver,WriteBlogPage.checkPostCancel,0,check);

               }else {
                   logger.error("随笔发布状态有误！不应该是："+postStatus);
               }

        }
            else if(choose.equals("文章")){
            ElementOperation.linkClick(driver, WriteBlogPage.articles);
            ElementOperation.linkClick(driver, WriteBlogPage.newarticles);
            ElementOperation.editInput(driver, WriteBlogPage.newarticlesTitle, title);
            driver.switchTo().frame(WriteBlogPage.newarticlesFrame);
            ElementOperation.editInput(driver, WriteBlogPage.newarticlesContent, content);
            driver.switchTo().defaultContent();
            if (postStatus.equals("发布")) {
                ElementOperation.buttonClick(driver, WriteBlogPage.savearticles);
                ElementOperation.waitForElementPresent(driver,WriteBlogPage.checkArticlesSuccess);
                setCheckPoint(driver,WriteBlogPage.checkArticlesSuccess,0,check);

            } else if(postStatus.equals("存为草稿")) {
                ElementOperation.buttonClick(driver, WriteBlogPage.savearticlesAsDraft);
                ElementOperation.waitForElementPresent(driver,WriteBlogPage.checkArticlesAsDraft);
                setCheckPoint(driver,WriteBlogPage.checkArticlesAsDraft,0,check);

            } else if(postStatus.equals("取消")) {
                ElementOperation.buttonClick(driver, WriteBlogPage.cancelarticles);
                ElementOperation.alertOperation(driver, true);
                ElementOperation.waitForElementPresent(driver,WriteBlogPage.checkArticlesCancel);
                setCheckPoint(driver,WriteBlogPage.checkArticlesCancel,0,check);

            }else {
                logger.error("文章发布状态有误！不应该是："+postStatus);
            }
        }
        else if(choose.equals("日记")) {
            ElementOperation.linkClick(driver, WriteBlogPage.diary);
            ElementOperation.linkClick(driver, WriteBlogPage.newdiary);
            ElementOperation.editInput(driver, WriteBlogPage.newdiaryTitle, title);
            driver.switchTo().frame(WriteBlogPage.newdiaryFrame);
            ElementOperation.editInput(driver, WriteBlogPage.newdiaryContent, content);
            driver.switchTo().defaultContent();
            if (postStatus.equals("保存")) {
                ElementOperation.buttonClick(driver, WriteBlogPage.savediary);
                ElementOperation.waitForElementPresent(driver,WriteBlogPage.checkDiarySuccess);
                setCheckPoint(driver,WriteBlogPage.checkDiarySuccess,0,check);

            } else if(postStatus.equals("取消")) {
                ElementOperation.buttonClick(driver, WriteBlogPage.canceldiary);
                ElementOperation.alertOperation(driver, true);
                ElementOperation.waitForElementPresent(driver,WriteBlogPage.checkDiaryCancel);
                setCheckPoint(driver,WriteBlogPage.checkDiaryCancel,0,check);

            }else {
                logger.error("日志保存状态有误！不应该是："+postStatus);
            }
        }
        else if(choose.equals("评论")) {

        }
        else if(choose.equals("链接")) {

        }
        else if(choose.equals("相册")) {

        }
        else if(choose.equals("文件")) {

        }
        else if(choose.equals("设置")) {

        }
        else if(choose.equals("选项")) {

        }
        else{
            logger.error("选择有误！不应该是："+choose);
        }
    }

    public void test() {

    }
}