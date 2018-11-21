package web.pageObjects.Capital.flow.bankFlow;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/4
 * Time: 下午4:36
 * 资金管理->银行流水管理->板块流水列表
 */
public class DiffPlateReport {
        public static By date_start = By.name("date_start");//入账日期开始
        public static By date_end = By.name("date_end");//入账日期截止
        public static By plate = By.name("plate");//选择板块
        public static By search = By.name("explode");//搜索
        public static By download = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='搜索'])[1]/following::button[1]");//搜索
        public static By reset = By.linkText("重置搜索条件");//重置搜索条件

        public static By count = By.xpath("//*[@id=\"confirm-grid\"]/div[1]/b[2]");//小字，数据总数

        //未找到数据
        public static By unfound = By.xpath("//*[@id=\"confirm-grid\"]/table/tbody/tr/td/div");
}
