package web.pageObjects.Capital.flow.flowBankAccount;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/17
 * Time: 下午3:40
 * 资金管理->银行流水管理->账户管理列表 页面元素定位
 */
public class Index {
    //    搜索条件
    public static By search_affiliation_plate = By.name("plate");//请选择板块类型
    public static By search_company_name = By.name("company_id"); //请选择公司
    public static By search_bank_name = By.id("flowbankaccountsearch-bank_name"); //开户行
    public static By search_account_name = By.id("flowbankaccountsearch-account_name"); //账户简称
    public static By search_account_id = By.id("flowbankaccountsearch-account_id"); //银行账户
    public static By search_account_type = By.id("flowbankaccountsearch-account_type"); //账户类型选择
    public static By search_money_pro = By.id("flowbankaccountsearch-money_pro"); //资金性质选择
    public static By search_bank_short_name = By.id("flowbankaccountsearch-bank_short_name"); //银行选择
    public static By search_account_full_name = By.id("flowbankaccountsearch-account_full_name"); //账户名

    //    按钮
    public static By button_search = By.xpath("//*[@id=\"clearing-search-form\"]/div[2]/button[1]"); //搜索
    public static By button_reset = By.linkText("重置搜索条件"); //重置搜索条件
    public static By button_create_account = By.linkText("新增账户"); //新增账户
    public static By button_not_uplode = By.linkText("昨日未上传账户"); //昨日未上传账户
    public static By button_export = By.xpath("//*[@id=\"clearing-search-form\"]/div[2]/button[2]"); //导出

    public static By button_import = By.linkText("导入流水"); //首行，导入流水
    public static By button_view_flow = By.linkText("查看流水"); //查看流水
    public static By button_update_bank = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[4]/a[1]"); //更新账户
    public static By button_view_bank = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[4]/a[2]"); //查看账户

    public static By sum_number = By.xpath("//*[@id=\"w0\"]/div[1]/b[2]"); //共c条数据.
    public static By empty_text = By.xpath("//*[@id=\"w0\"]/table/tbody/tr/td/div"); //没有找到数据。

    //列表文案定位
    public static By text_account_status = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[6]");//账户状态
    public static By text_bank_short_name = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[9]");//所属银行
    public static By text_account_id = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[10]");//银行账户
    public static By text_account_full_name = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[11]");//账户名
    public static By text_account_name = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[12]");//账户简称
    public static By text_money_pro = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[13]");//资金性质
    public static By text_account_type = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[14]");//账户类型
    public static By text_bank_name = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[15]");//开户行
    public static By text_open_account_date = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[16]");//开户时间
    public static By text_account_casher = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[17]");//出纳
    public static By text_account_accountant = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[18]");//会计
    public static By text_remark = By.xpath("//*[@id=\"w0\"]/table/tbody/tr[1]/td[21]");//备注
}
