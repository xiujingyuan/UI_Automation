package web.pageObjects.Capital.accountingReport.singleReports.singleCompanyReport.entryBatch;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/11
 * Time: 下午6:49
 * 会计核算->月度报表->单体报表->管报税报公司单体报表->添加调整分录
 * 添加调整分录 页面元素定位
 */
public class IndexEntryBatch {
    //搜索条件
    public static By elimination = By.name("elimination");//是否参与抵消
    public static By project_name = By.name("project_name");//请输入需要过滤的项目名称
    public static By project_code = By.name("project_code");//请输入需要过滤的科目代码
    public static By project_comment = By.name("project_comment");//请输入需要过滤的调整事项

    //按钮
    public static By search = By.name("explode");//搜索
    public static By reset = By.linkText("重置搜索条件");//重置
    public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='重置搜索条件'])[1]/following::button[1]");//下载

    //小字
    public static By found = By.xpath("//*[@id=\"w2\"]/table/thead/tr/th[1]");//存在调整的情况下，首行有title

    //右下角按钮
    public static By commit = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='下载'])[1]/following::button[1]");//提交
    public static By add_entry_batch = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='下载'])[1]/following::button[2]");//添加
}
