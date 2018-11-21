package web.pageObjects.Capital.accountingReport.combinedReport.entryBatch;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/12
 * Time: 下午3:15
 * 会计核算->月度报表->合并报表->调整分录抵消明细
 * 元素 定位
 */
public class ViewEntryBatch {
    //搜索条件
    public static By company_id = By.name("company_id");//请选择公司
    public static By project_name = By.name("project_name");//请输入需要过滤的项目名称
    public static By project_code = By.name("project_code");//请输入需要过滤的科目代码
    public static By affiliation_plate = By.name("affiliation_plate");//请选择板块类型
    public static By version = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='参与抵消调整分录明细'])[1]/following::select[3]");//版本
    public static By project_comment = By.name("project_comment");//请输入需要过滤的调整事项

    //按钮
    public static By search = By.name("explode");//搜索
    public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='重置搜索条件'])[1]/following::button[1]");//下载
    public static By reset = By.linkText("重置搜索条件");//重置

    //小字
    public static By found = By.xpath("//*[@id=\"w0\"]/table/thead/tr/th[1]");//报表正常生成的情况下，表格首行有title
}
