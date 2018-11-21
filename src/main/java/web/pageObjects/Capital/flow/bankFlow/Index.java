package web.pageObjects.Capital.flow.bankFlow;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/3
 * Time: 下午2:58
 * 资金管理->银行流水管理->流水列表 页面元素定位
 */
public class Index {
    //搜索条件
    public static By affiliation_type = By.name("plate");//请选择板块类型
    public static By company_name = By.name("company_id");//请选择公司
    public static By bank_name = By.name("FlowAccountWatercourseSearch[bank_id]");//请选择银行
    public static By bank_account = By.name("FlowAccountWatercourseSearch[bank_account]");//银行账户
    public static By min_roll_in_money = By.id("flowaccountwatercoursesearch-roll_in_money");//最小转入金额
    public static By max_roll_in_money = By.name("max_roll_in_money");//最大转入金额
    public static By min_roll_out_money = By.id("flowaccountwatercoursesearch-roll_out_money");//最小转出金额
    public static By max_roll_out_money = By.name("max_roll_out_money");//最大转出金额
    public static By min_balance = By.id("flowaccountwatercoursesearch-balance");//最小余额
    public static By max_balance = By.name("balance");//最大余额
    public static By min_trade_date = By.name("min_trade_date");//交易开始日期
    public static By max_trade_date = By.name("max_trade_date");//交易结束日期
    public static By side_account = By.id("flowaccountwatercoursesearch-side_account");//对方账号
    public static By side_account_name = By.id("flowaccountwatercoursesearch-side_account_name");//对方账号名
    public static By loan_sign = By.id("flowaccountwatercoursesearch-loan_sign");//借贷标志
    public static By summary = By.id("flowaccountwatercoursesearch-summary");//摘要
    public static By stair_classify = By.id("stair-classify");//请选择一级分类
    public static By second_classify = By.id("second-classify");//请选择二级分类
    public static By three_classify = By.id("three-classify");//请选择三级分类
    public static By money_pro = By.name("FlowAccountWatercourseSearch[money_pro]");//请选择资金性质

    public static By set_format = By.id("set_format");//页面右上角的字段展示设置按钮
    //按钮
    public static By search = By.xpath("//*[@id=\"clearing-search-form\"]/div[2]/button");//搜索
    public static By reset_search = By.linkText("重置搜索条件");//重置搜索条件
    public static By download = By.id("btnDownload");//下载
    public static By sum_roll_in = By.id("cnyString");//转入总额
    public static By sum_roll_out = By.id("usdString");//转出总额


}
