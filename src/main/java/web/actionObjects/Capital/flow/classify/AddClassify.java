package web.actionObjects.Capital.flow.classify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.common.SwitchToWindow;
import web.pageObjects.Capital.flow.classify.AddClassifyPage;
import web.pageObjects.Capital.flow.classify.IndexPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/6
 * Time: 上午11:20
 * 资金管理->银行流水管理->银行流水科目管理->分类管理
 * 新增分类
 */
public class AddClassify extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param parent_classify
     * @param classify_name
     * @param classify_code
     * @param classify_desc
     * @param expect_result
     */
    public void addClassify(String case_number, String parent_classify, String classify_name, String classify_code,
                            String classify_desc, String expect_result) {
        //点击【新增分类】
        ElementOperation.buttonClick(driver, IndexPage.create_classify);
        //焦点移至新打开的页面
        String title = "新建分类";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
        //核对 当前页面标题是否为 新建分类
        Assert.assertEquals(driver.getTitle(), "新建分类");
        Reporter.log("当前页面 = 期望页面");
        //选择 父级分类
        ElementOperation.linkClick(driver, AddClassifyPage.parent_classify_1);
        ElementOperation.editOnly(driver, AddClassifyPage.parent_classify_2, parent_classify);
        ElementOperation.linkClick(driver, AddClassifyPage.parent_classify_1);

        //输入 分类名称、编码、说明
        ElementOperation.editInput(driver, AddClassifyPage.classify_name, classify_name);
        ElementOperation.editInput(driver, AddClassifyPage.classify_code, classify_code);
        ElementOperation.editInput(driver, AddClassifyPage.classify_desc, classify_desc);

        //点击【新增】
        ElementOperation.buttonClick(driver, AddClassifyPage.button_create);
        Reporter.log("新增操作完成");

        //焦点移至新打开的页面
        String title_2 = "分类列表";
        switchAction.switchToWindow(title_2);
        Reporter.log("分类新增成功");
    }
}
