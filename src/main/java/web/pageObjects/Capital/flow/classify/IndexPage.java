package web.pageObjects.Capital.flow.classify;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/6
 * Time: 上午10:49
 * 资金管理->银行流水管理->银行流水科目管理->分类管理
 * 列表页面 元素定位
 */
public class IndexPage {
    //搜索条件
    public static By classify_name = By.id("flowclassifysearch-flow_classify_name");//分类名称
    public static By classify_level = By.id("flowclassifysearch-flow_classify_level");//请选择分类级别
    public static By classify_code = By.id("flowclassifysearch-flow_classify_code");//分类编码

    //搜索条件->按钮
    public static By search = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='分类列表'])[3]/following::button[1]");//搜索
    public static By reset = By.linkText("重置");//重置
    public static By create_classify = By.linkText("新增分类");//新增分类

    //抬头的计数小字
    public static By count = By.xpath("//*[@id=\"w0\"]/div/b[2]");//共X条数据
    public static By unfound = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td/div");//没有找到数据。

    //列表中的按钮
    public static By edit = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='更新人'])[1]/following::a[1]");//更新
    public static By view = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='更新人'])[1]/following::a[2]");//查看
    public static By delete = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='更新人'])[1]/following::a[3]");//删除

    //列表中文案
    public static By text_classify_name  = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[2]");//分类名称
    public static By text_parent_classify  = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td[3]");//父级分类
    public static By text_classify_desc  = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td[5]");//说明
    public static By text_classify_code  = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td[6]");//编码

    //查看分类页面
    public static By button_update = By.linkText("更新");//更新
    public static By button_delete = By.linkText("删除");//删除

}
