package web.pageObjects.Capital.flow.classify;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/6
 * Time: 上午11:23
 * 资金管理->银行流水管理->银行流水科目管理->分类管理
 * 新增页面 元素定位
 */
public class AddClassifyPage {
    public static By parent_classify_1 = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='父级分类（默认顶级）'])[1]/following::span[1]");//父级分类
    public static By parent_classify_2 = By.id("flowclassify-flow_classify_parent_id");//父级分类，输入框
    public static By classify_name = By.id("flowclassify-flow_classify_name");//分类名称
    public static By classify_code = By.id("flowclassify-flow_classify_code");//编码
    public static By classify_desc = By.id("flowclassify-flow_classify_desc");//说明

    //按钮：新增
    public static By button_create = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='说明'])[1]/following::button[1]");

    //按钮：更新
    public static By button_update = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='说明'])[1]/following::button[1]");
}
