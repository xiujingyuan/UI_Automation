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
 * Time: 下午3:35
 * 资金管理->银行流水管理->银行流水科目管理->分类管理
 * 更新
 */
public class UpdateClassify extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    /**
     * @param case_number
     * @param search_classify_name
     * @param parent_classify
     * @param classify_name
     * @param classify_code
     * @param classify_desc
     */
    public void updateClassify(String case_number, String search_classify_name, String search_classify_level, String search_classify_code,
                               String search_expect_result, String parent_classify, String classify_name,
                               String classify_code, String classify_desc) {
        //搜索需要更新的分类
        SearchClassify searchAction = new SearchClassify();
        searchAction.search(case_number, search_classify_name, search_classify_level, search_classify_code, search_expect_result);

        //点击【更新】
        ElementOperation.buttonClick(driver, IndexPage.edit);
        //焦点移至新打开的页面
        String title = "更新分类";
        SwitchToWindow switchAction = new SwitchToWindow();
        switchAction.switchToWindow(title);
        //核对 当前页面标题是否为 更新分类
        Assert.assertEquals(driver.getTitle(), "更新分类");
        Reporter.log("当前页面 = 期望页面");
        //选择 父级分类
        ElementOperation.linkClick(driver, AddClassifyPage.parent_classify_1);
        ElementOperation.editOnly(driver, AddClassifyPage.parent_classify_2, parent_classify);
        ElementOperation.linkClick(driver, AddClassifyPage.parent_classify_1);

        //输入 分类名称、编码、说明
        ElementOperation.editInput(driver, AddClassifyPage.classify_name, classify_name);
        ElementOperation.editInput(driver, AddClassifyPage.classify_code, classify_code);
        ElementOperation.editInput(driver, AddClassifyPage.classify_desc, classify_desc);

        //点击【更新】
        ElementOperation.buttonClick(driver, AddClassifyPage.button_update);
        Reporter.log("更新操作完成");

        //焦点移至新打开的页面
        String title02 = "分类列表";
        switchAction.switchToWindow(title02);
    }
}
