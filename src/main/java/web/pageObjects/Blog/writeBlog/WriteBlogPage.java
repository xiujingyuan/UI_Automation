package web.pageObjects.Blog.writeBlog;

import org.openqa.selenium.By;

/**
 * Created by as on 2016/11/7.
 */
public class WriteBlogPage {
    public static By posts =By.id("TabPosts");                             //随笔
    public static By newposts =By.linkText("添加新随笔");                // 添加新随笔
    public static By newpostsTitle =By.id("Editor_Edit_txbTitle");     // 随笔标题
    public static String newpostsContentFrame ="Editor_Edit_EditorBody_ifr"; // 随笔内容FrameID
    public static By newpostsContent =By.cssSelector("body");               // 随笔内容
    public static By saveposts =By.id("Editor_Edit_lkbPost");           //发布随笔
    public static By savepostsAsDraft =By.id("Editor_Edit_lkbDraft");   //保存随笔为草稿
    public static By cancelposts =By.id("Editor_Edit_lkbCancel");       //取消随笔
    public static By checkPostSuccess =By.cssSelector("div.MessagePanel div");       //验证发布随笔成功
    public static By checkPostCancel =By.id("nav_draft");                                //验证取消发布随笔
    public static By checkPostAsDraft =By.cssSelector("div.MessagePanel div");       //验证保存随笔为草稿

    public static By articles =By.id("TabArticles");                    //文章
    public static By newarticles=By.linkText("» 添加新文章");             // 添加新文章
    public static By newarticlesTitle =By.id("Editor_Edit_txbTitle");   // 文章标题
    public static String newarticlesFrame ="Editor_Edit_EditorBody_ifr";      // 文章内容FrameID
    public static By newarticlesContent =By.id("tinymce");                 // 文章内容
    public static By savearticles =By.id("Editor_Edit_lkbPost");         //保存文章
    public static By savearticlesAsDraft =By.id("Editor_Edit_lkbDraft");//保存文章为草稿
    public static By cancelarticles =By.id("Editor_Edit_lkbCancel");    //取消文章
    public static By checkArticlesSuccess =By.cssSelector("div.MessagePanel div");       //验证发布文章成功
    public static By checkArticlesCancel =By.linkText("» 草稿箱");                                //验证取消发布文章
    public static By checkArticlesAsDraft =By.cssSelector("div.MessagePanel div");       //验证保存文章为草稿


    public static By diary =By.id("TabDiary");                              //日志
    public static By newdiary=By.linkText("» 添加日记");                    // 添加日记
    public static By newdiaryTitle =By.id("Editor_Edit_txbTitle");        // 日志标题
    public static String newdiaryFrame ="Editor_Edit_EditorBody_ifr";      // 日志内容FrameID
    public static By newdiaryContent =By.id("tinymce");                     // 日志内容
    public static By savediary =By.id("Editor_Edit_lkbPost");             //保存日志
    public static By canceldiary =By.id("Editor_Edit_lkbCancel");         //取消日志
    public static By checkDiarySuccess =By.cssSelector("div.MessagePanel div");       //验证保存日志成功
    public static By checkDiaryCancel =By.linkText("阅读日记");                                //验证取消保存日志


    public static By feedback =By.id("TabFeedback");                        //评论
    public static By links =By.id("TabLinks");                              //链接

    public static By galleries =By.id("TabGalleries");                      //相册

    public static By tabFiles =By.id("TabFiles");                           //文件


    public static By configure=By.id("TabConfigure");                       //设置
    public static By preferences =By.id("TabPreferences");                  //选项

}
