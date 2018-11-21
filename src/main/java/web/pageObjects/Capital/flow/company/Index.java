package web.pageObjects.Capital.flow.company;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/5
 * Time: 下午12:02
 * 资金管理->银行流水管理->公司管理列表
 * 列表页面 元素定位
 */
public class Index {
    //搜索条件
    public static By affiliation_plate = By.id("companylistsearch-affiliation_plate");//请选择板块类型
    public static By status = By.id("companylistsearch-status");//公司状态
    public static By is_create_account = By.id("companylistsearch-is_create_account");//是否建账
    public static By company_name = By.id("companylistsearch-company_name");//公司名称

    //按钮
    public static By search = By.name("export");//搜索
    public static By create_company = By.linkText("新增公司");//新增公司
    public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='新增公司'])[1]/following::button[1]");//导出
    public static By import_trial = By.linkText("导入科目余额");//导入科目余额
    public static By edit = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='正常运营'])[2]/following::a[1]");//更新
    public static By view = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='正常运营'])[2]/following::a[2]");//查看

    //小字
    public static By sum = By.xpath("//*[@id=\"w0\"]/div/b[2]");
    public static By mark = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td/div");//没有找到数据。

    //列表文案
    public static By text_company_name = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[3]");//公司名称
    public static By text_company_tag = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[4]");//客商代码
    public static By text_affiliation_plate = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[5]");//所属板块
    public static By text_status = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[11]");//状态
    public static By text_is_create_account = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[6]");//是否建账
    public static By text_company_short = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[7]");//公司简称
    public static By text_tax_city = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[8]");//税务所在地
    public static By text_remark = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[12]");//描述

    //查看公司页面
    public static By update = By.linkText("更新");//更新
    public static By delete = By.linkText("删除");//删除
}
