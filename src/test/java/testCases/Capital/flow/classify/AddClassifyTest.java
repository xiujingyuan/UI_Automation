package testCases.Capital.flow.classify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.classify.AddClassify;
import web.actionObjects.Capital.menu.Menu;
import web.common.*;
import web.pageObjects.Capital.flow.classify.IndexPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/6
 * Time: 上午11:42
 * 资金管理->银行流水管理->银行流水科目管理->分类管理
 * 新增分类
 */
public class AddClassifyTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeTest
    public void beforeTest() {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
    }

    public void test() {
    }

    //    从Excel中读取输入内容
    @DataProvider(name = "data")
    public Object[][] getData() {
        Object[][] retObjArr = ExcelOperation.getData(
                "flow/classify/",
                "addClassify.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param parent_classify
     * @param classify_name
     * @param classify_code
     * @param classify_desc
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "分类管理_新增分类", enabled = true, groups = {"add"})
    public void addTest(String case_number, String parent_classify, String classify_name, String classify_code,
                        String classify_desc, String expect_result) {
        //关闭其他窗口
        CloseOtherWindows closeAction = new CloseOtherWindows();
        closeAction.close();
        //前往 资金管理->银行流水管理->银行流水科目管理->分类管理
        Menu menuAction = new Menu();
        menuAction.gotoFlowClassifyIndex();

        //新增操作前，记录数据数
        int number_before = Integer.parseInt(driver.findElement(IndexPage.count).getText());

        //新增，输入各项值
        AddClassify addAction = new AddClassify();
        addAction.addClassify(case_number, parent_classify, classify_name, classify_code, classify_desc, expect_result);

        //新增操作后，重新获取数据数
        int number_after = Integer.parseInt(driver.findElement(IndexPage.count).getText());
        // 1.比较数据前后差异数
        int number_diff = number_after - number_before;
        Assert.assertEquals(number_diff, 1);

        //Excel中result值为string，转为int再比较
        int r = Integer.parseInt(expect_result);
        // 2.新增后总条数是否与测试用例中条数相符
        Assert.assertEquals(number_after, r);
        Reporter.log("新增数据条数校验正常。");
    }

    /**
     * @param case_number
     * @param parent_classify
     * @param classify_name
     * @param classify_code
     * @param classify_desc
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "分类管理_对比新增信息", enabled = true, groups = {"addConfirm"})
    public void confirmTest(String case_number, String parent_classify, String classify_name, String classify_code,
                            String classify_desc, String expect_result) {
        //选择 前面新增的分类名称
        ElementOperation.editInput(driver, IndexPage.classify_name, classify_name);
        //点击 搜索
        ElementOperation.buttonClick(driver, IndexPage.search);

        //焦点移至新打开的页面
        String title = "分类列表";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //对比
        //新增后读取对应数据
        String[] excel_a = new String[]{
                parent_classify,
                classify_name,
                classify_code,
                classify_desc
        };

        String[] b = new String[]{
                driver.findElement(IndexPage.text_parent_classify).getText(),
                driver.findElement(IndexPage.text_classify_name).getText(),
                driver.findElement(IndexPage.text_classify_code).getText(),
                driver.findElement(IndexPage.text_classify_desc).getText(),
        };

        Reporter.log(driver.findElement(IndexPage.text_parent_classify).getText());
        Reporter.log(driver.findElement(IndexPage.text_classify_name).getText());
        Reporter.log(driver.findElement(IndexPage.text_classify_code).getText());
        Reporter.log(driver.findElement(IndexPage.text_classify_desc).getText());

        Assert.assertEquals(excel_a, b);

        Reporter.log("新增数据对比正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
