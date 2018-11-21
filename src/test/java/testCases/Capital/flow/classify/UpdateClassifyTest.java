package testCases.Capital.flow.classify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.classify.UpdateClassify;
import web.common.*;
import web.pageObjects.Capital.flow.classify.IndexPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/6
 * Time: 下午3:48
 * 资金管理->银行流水管理->银行流水科目管理->分类管理
 * 更新
 */
public class UpdateClassifyTest extends BaseAction {
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
                "updateClassify.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param search_classify_name
     * @param search_classify_level
     * @param search_classify_code
     * @param search_expect_result
     * @param parent_classify
     * @param classify_name
     * @param classify_code
     * @param classify_desc
     */
    @Test(dataProvider = "data", description = "分类管理_更新分类", enabled = true, groups = {"update"})
    public void updateTest(String case_number, String search_classify_name, String search_classify_level, String search_classify_code,
                           String search_expect_result, String parent_classify, String classify_name,
                           String classify_code, String classify_desc) {
        //更新，输入各项值
        UpdateClassify updateAction = new UpdateClassify();
        updateAction.updateClassify(case_number, search_classify_name, search_classify_level, search_classify_code,
                search_expect_result, parent_classify, classify_name, classify_code, classify_desc);
    }

    /**
     * @param case_number
     * @param search_classify_name
     * @param search_classify_level
     * @param search_classify_code
     * @param search_expect_result
     * @param parent_classify
     * @param classify_name
     * @param classify_code
     * @param classify_desc
     */
    @Test(dataProvider = "data", description = "分类管理_对比更新信息", enabled = true, groups = {"updateConfirm"})
    public void confirmTest(String case_number, String search_classify_name, String search_classify_level, String search_classify_code,
                            String search_expect_result, String parent_classify, String classify_name,
                            String classify_code, String classify_desc) {
        //选择 前面更新的分类名称
        ElementOperation.editInput(driver, IndexPage.classify_name, search_classify_name);
        //点击 搜索
        ElementOperation.buttonClick(driver, IndexPage.search);

        //焦点移至新打开的页面
        String title = "分类列表";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);

        //对比
        //更新后读取对应数据
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

        Reporter.log("更新数据正常。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}
