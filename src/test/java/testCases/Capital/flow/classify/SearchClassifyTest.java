package testCases.Capital.flow.classify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.classify.SearchClassify;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.CloseOtherWindows;
import web.common.ElementOperation;
import web.common.ExcelOperation;
import web.pageObjects.Capital.flow.classify.IndexPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/6
 * Time: 上午11:06
 * 资金管理->银行流水管理->银行流水科目管理->分类管理
 * 页面正常打开、搜索、重置
 */
public class SearchClassifyTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    @Test(enabled = true, description = "分类管理_检查页面正常打开", groups = {"check_in"})
    public void checkIn() {
        //前往 资金管理->银行流水管理->银行流水科目管理->分类管理
        Menu menuAction = new Menu();
        menuAction.gotoFlowClassifyIndex();

        //检查是否成功进入页面
        driver.getTitle().contains("分类列表");
        Reporter.log("成功进入页面：" + "\n" + driver.getTitle() + "\n");
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/classify/",
                "searchClassify.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param classify_name
     * @param classify_level
     * @param classify_code
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "分类管理_搜索", enabled = true, groups = {"search"})
    public void mixedSearch(String case_number, String classify_name, String classify_level, String classify_code,
                            String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //搜索
        SearchClassify searchAction = new SearchClassify();
        searchAction.search(case_number, classify_name, classify_level, classify_code, expect_result);

        boolean a = ElementOperation.isElementPresent(driver, IndexPage.count);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, IndexPage.unfound);
        Reporter.log(String.valueOf(b));

        if (a) {
            //搜索结果非0时，检查搜索后结果 是否与期望结果一致
            int number1 = Integer.parseInt(driver.findElement(IndexPage.count).getText());
            //Excel中result值为string，转为int再比较
            int r1 = Integer.parseInt(expect_result);
            Assert.assertEquals(number1, r1);

            Reporter.log(String.valueOf(expect_result));
            Reporter.log("数据总条数：" + driver.findElement(IndexPage.count).getText() + "\n");
        } else if (b) {
            //搜索结果为0
            Assert.assertEquals(driver.findElement(IndexPage.unfound).getText(), "没有找到数据。");
            Reporter.log("没有找到数据。");
        }
        Reporter.log("搜索正常。");
    }

    /**
     * @param case_number
     * @param classify_name
     * @param classify_level
     * @param classify_code
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "分类管理_重置搜索", enabled = true, groups = {"search"})
    public void resetSearch(String case_number, String classify_name, String classify_level, String classify_code,
                            String expect_result) {
        //搜索
        SearchClassify searchAction = new SearchClassify();
        searchAction.search(case_number, classify_name, classify_level, classify_code, expect_result);

        boolean a = ElementOperation.isElementPresent(driver, IndexPage.count);
        Reporter.log(String.valueOf(a));
        boolean b = ElementOperation.isElementPresent(driver, IndexPage.unfound);
        Reporter.log(String.valueOf(b));

        if (a) {
            //搜索结果非0时，记录重置前结果
            int reset_before_number = Integer.parseInt(driver.findElement(IndexPage.count).getText());
            //点击重置搜索
            SearchClassify resetSearchAction = new SearchClassify();
            resetSearchAction.resetSearch(driver);
            //记录重置后结果
            int reset_after_number = Integer.parseInt(driver.findElement(IndexPage.count).getText());
            //对比 重置前后
            Assert.assertEquals(reset_before_number, reset_after_number);
            Reporter.log("重置前后数据数一致，均为：" + reset_after_number + "\n");
        } else if (b) {
            //搜索结果为0，记录重置前文案
            String reset_before_text = driver.findElement(IndexPage.unfound).getText();
            //点击重置搜索
            SearchClassify resetSearchAction = new SearchClassify();
            resetSearchAction.resetSearch(driver);
            //记录重置后结果
            String reset_after_text = driver.findElement(IndexPage.unfound).getText();
            //对比 重置前后
            Assert.assertEquals(reset_before_text, reset_after_text);
            Reporter.log("重置前后文案一致，均为：" + reset_after_text + "\n");
        }
        String reset_after_classify_name = driver.findElement(IndexPage.classify_name).getText();
        String reset_after_classify_level = ElementOperation.getSelectedValue(driver, IndexPage.classify_level);
        String reset_after_classify_code = driver.findElement(IndexPage.classify_code).getText();

        Assert.assertEquals(reset_after_classify_level, "请选择分类级别");
        Assert.assertEquals(reset_after_classify_name, "");
        Assert.assertEquals(reset_after_classify_code, "");
        Reporter.log("重置正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
