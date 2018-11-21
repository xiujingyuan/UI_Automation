package testCases.Demo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.flow.flowBankAccount.Index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/17
 * Time: 下午4:45
 */
public class ArrayListTest extends BaseAction {
    public void test() {

    }

    @Test
    public void list() {
        //前往 账户管理列表页面
        Menu menuAction = new Menu();
        menuAction.gotoFlowBankAccount();

        /** 遍历下拉列表所有选项 **/
        Select selectState = new Select(driver.findElement(Index.search_affiliation_plate));

        // 验证下拉列表不支持多选
        Assert.assertFalse(selectState.isMultiple());

        List<String> act_StateOptions = new ArrayList<String>();//实例化一个list表

        // 判断选择内容
        for (WebElement e : selectState.getOptions()) {
            // 将实际的下拉列表内容注入到act_StateOptions中
            act_StateOptions.add(e.getText());
            System.out.println(e.getText());
        }

        // 预期的选项内容存放在StateOptions
        List<String> expect_StateOptions = Arrays.asList(new String[]{"请选择板块类型", "快牛(原现金贷)",
                "云智", "贷后业务", "流量业务(乾昭)", "腾梭", "游戏业务(芬果)", "商城业务(星游)", "钱牛牛", "外部公司", "其他"});

        //将实际结果与预期的选项内容进行比较。
        Assert.assertEquals(expect_StateOptions.toArray(), act_StateOptions.toArray());

        ElementOperation.selectDropdownValue(driver, Index.search_affiliation_plate, "云智");

        //取选中项的值
        String slectedValue = ElementOperation.getSelectedValue(driver, Index.search_affiliation_plate);
        Assert.assertEquals(slectedValue, "云智");
        System.out.println(slectedValue);
    }
}
