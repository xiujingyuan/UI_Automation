package web.pageObjects.Capital.flow.flowAbnormalWatercourse;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/4
 * Time: 下午5:32
 * 资金管理->银行流水管理->异常流水列表
 */
public class Index {
    public static By min_create_time = By.name("min_create_time");//创建开始日期
    public static By max_create_time = By.name("max_create_time");//创建结束日期
    public static By bank_account = By.id("flowabnormalwatercoursesearch-flow_abnormal_watercourse_bank_account");//银行账户
    public static By min_trade_date = By.name("min_trade_date");//交易开始日期
    public static By max_trade_date = By.name("max_trade_date");//交易结束日期

    public static By company = By.name("company_id");//请选择公司

    public static By side_account = By.id("flowabnormalwatercoursesearch-flow_abnormal_watercourse_side_account");//对方账号
    public static By side_account_name = By.id("flowabnormalwatercoursesearch-flow_abnormal_watercourse_side_account_name");//对方账户名称
    public static By summary = By.id("flowabnormalwatercoursesearch-flow_abnormal_watercourse_summary");//流水摘要

    public static By search = By.xpath("//*[@id=\"clearing-search-form\"]/div[2]/button");//搜索
    public static By reset = By.linkText("重置搜索条件");//重置搜索条件

    public static By sum_count_text = By.xpath("//*[@id=\"w0\"]/div[1]/b[2]");//小字，数据总数

    //未找到数据
    public static By not_found = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td/div");
}
