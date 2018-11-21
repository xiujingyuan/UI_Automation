package testCases.Capital.flow.classify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.actionObjects.Capital.flow.classify.DeleteClassify;
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
 * Time: 下午4:14
 * 资金管理->银行流水管理->银行流水科目管理->分类管理
 * 删除分类
 */
public class DeleteClassifyTest extends BaseAction {
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
                "deleteClassify.xlsx");
        return (retObjArr);
    }

    /**
     * @param case_number
     * @param classify_name
     * @param classify_level
     * @param classify_code
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "分类管理_删除分类", enabled = true, groups = {"delete"})
    public void deleteClassify(String case_number, String classify_name, String classify_level, String classify_code,
                               String expect_result) {
        //前往 资金管理->银行流水管理->银行流水科目管理->分类管理
        Menu menuAction = new Menu();
        menuAction.gotoFlowClassifyIndex();

        //删除操作前，记录数据数
        int number_before = Integer.parseInt(driver.findElement(IndexPage.count).getText());

        //删除
        DeleteClassify deleteAction = new DeleteClassify();
        deleteAction.delete(case_number, classify_name, classify_level, classify_code, expect_result);

        //删除操作后，记录数据数
        int number_after = Integer.parseInt(driver.findElement(IndexPage.count).getText());
        //取 数据前后差异数
        int number_diff = number_before - number_after;

        // 1.比较数据前后差异数
        Assert.assertEquals(number_diff, 1);
        //Excel中result值为string，转为int再比较
        int r = Integer.parseInt(expect_result);
        // 2.删除后总条数是否与测试用例中条数相符
        Assert.assertEquals(number_after, r);
        Reporter.log("删除数据条数校验正常。");
    }

    /**
     * @param case_number
     * @param classify_name
     * @param classify_level
     * @param classify_code
     * @param expect_result
     */
    @Test(dataProvider = "data", description = "分类管理_校验是否删除信息", enabled = true, groups = {"deleteConfirm"})
    public void confirmTest(String case_number, String classify_name, String classify_level, String classify_code,
                            String expect_result) {
        //选择 前面删除的分类名称
        ElementOperation.editInput(driver, IndexPage.classify_name, classify_name);
        //点击 搜索，验证是否无数据
        ElementOperation.buttonClick(driver, IndexPage.search);
        ElementOperation.waitForElementPresent(driver, IndexPage.unfound);
        Reporter.log("分类删除成功。");
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log("\n" + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + "\n" + "###### 测试执行完成 ######");
    }
}

