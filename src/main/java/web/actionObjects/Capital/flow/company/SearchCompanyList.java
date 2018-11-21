package web.actionObjects.Capital.flow.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.actionObjects.Capital.menu.Menu;
import web.common.BaseAction;
import web.common.ElementOperation;
import web.pageObjects.Capital.flow.company.Index;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/5
 * Time: 下午2:16
 * 资金管理->银行流水管理->公司管理列表->搜索
 */
public class SearchCompanyList extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {

    }

    /**
     * @param case_number
     * @param affiliation_plate
     * @param status
     * @param is_create_account
     * @param company_name
     * @param result
     */
    public void search(String case_number, String affiliation_plate, String status, String is_create_account,
                       String company_name, String result) {
        //前往 资金管理->银行流水管理->公司管理列表
        Menu menuAction = new Menu();
        menuAction.gotoFlowCompanyIndex();

        //选择 板块类型、公司状态、是否建账
        ElementOperation.selectDropdownValue(driver, Index.affiliation_plate, affiliation_plate);
        ElementOperation.selectDropdownValue(driver, Index.status, status);
        ElementOperation.selectDropdownValue(driver, Index.is_create_account, is_create_account);
        //输入 公司名称
        ElementOperation.editInput(driver, Index.company_name, company_name);

        //获取当前窗口的句柄
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);

        //点击 搜索
        ElementOperation.buttonClick(driver, Index.search);
        wait5s();
    }
}
