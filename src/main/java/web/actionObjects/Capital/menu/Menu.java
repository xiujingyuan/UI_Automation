package web.actionObjects.Capital.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import web.common.BaseAction;
import web.common.Configurator;
import web.pageObjects.Capital.menu.MenuPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/17
 * Time: 下午3:40
 */

public class Menu extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test() {
    }

    //    进入公司管理列表
    public Menu gotoFlowCompanyIndex() {
        Reporter.log("进入 资金管理->银行流水管理->公司管理列表");
        String final_url = Configurator.getURL() + MenuPage.flow_company_index_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入异常流水列表
    public Menu gotoFlowAbnormalWatercourse() {
        Reporter.log("进入 资金管理->银行流水管理->异常流水列表");
        String final_url = Configurator.getURL() + MenuPage.flow_abnormal_watercourse_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入板块流水列表
    public Menu gotoFlowDiffPlateReport() {
        Reporter.log("进入 资金管理->银行流水管理->板块流水列表");
        String final_url = Configurator.getURL() + MenuPage.flow_diff_plate_report_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入流水列表
    public Menu gotoFlowBankFlow() {
        Reporter.log("进入 资金管理->银行流水管理->流水列表");
        String final_url = Configurator.getURL() + MenuPage.flow_bank_flow_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入账户余额列表
    public Menu gotoFlowEverydayAccountBalanceIndex() {
        Reporter.log("进入 资金管理->银行流水管理->账户余额列表");
        String final_url = Configurator.getURL() + MenuPage.flow_everyday_account_balance_index_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入账户管理列表
    public Menu gotoFlowBankAccount() {
        Reporter.log("进入 资金管理->银行流水管理->账户管理列表");
        String final_url = Configurator.getURL() + MenuPage.flow_bank_account_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入资金管理->银行流水管理->银行流水科目管理->分类管理
    public Menu gotoFlowClassifyIndex() {
        Reporter.log("进入 资金管理->银行流水管理->银行流水科目管理->分类管理");
        String final_url = Configurator.getURL() + MenuPage.flow_classify_index_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入资金管理->银行流水管理->银行流水相关报表->拨备金核对报表
    public Menu gotoFlowPrepareTheoryMoneyList() {
        Reporter.log("进入 资金管理->银行流水管理->银行流水相关报表->拨备金核对报表");
        String final_url = Configurator.getURL() + MenuPage.flow_prepare_theory_money_list_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入资金管理->银行流水管理->银行流水相关报表->资金日报报表
    public Menu gotoFlowPlateBalanceIndex() {
        Reporter.log("进入 资金管理->银行流水管理->银行流水相关报表->资金日报报表");
        String final_url = Configurator.getURL() + MenuPage.flow_plate_balance_index_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->分析报表->异常统计页
    public Menu gotoAccountingReportErrorList() {
        Reporter.log("进入 会计核算->月度报表->分析报表->异常统计页");
        String final_url = Configurator.getURL() + MenuPage.accounting_report_error_list_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->分析报表->现金流量表
    public Menu gotoCashTransactionReport() {
        Reporter.log("进入 会计核算->月度报表->分析报表->现金流量表");
        String final_url = Configurator.getURL() + MenuPage.report_cash_transaction_report_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->科目余额
    public Menu gotoAcctTrialBalanceIndex() {
        Reporter.log("进入 会计核算->月度报表->科目余额");
        String final_url = Configurator.getURL() + MenuPage.acct_trial_balance_index_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->单体报表->管报税报公司单体报表
    public Menu gotoSingleCompanyReport() {
        Reporter.log("进入 会计核算->月度报表->单体报表->管报税报公司单体报表");
        String final_url = Configurator.getURL() + MenuPage.report_single_company_report_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->单体报表->单体资金推导表
    public Menu gotoCapitalDerivationReport() {
        Reporter.log("进入 会计核算->月度报表->单体报表->单体资金推导表");
        String final_url = Configurator.getURL() + MenuPage.report_capital_derivation_report_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->单体报表->单体变动分析表
    public Menu gotoVariationAnalysisReport() {
        Reporter.log("进入 会计核算->月度报表->单体报表->单体变动分析表");
        String final_url = Configurator.getURL() + MenuPage.report_variation_analysis_report_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->配置管理->科目列表
    public Menu gotoAccountingAccountIndex() {
        Reporter.log("进入 会计核算->月度报表->配置管理->科目列表");
        String final_url = Configurator.getURL() + MenuPage.accounting_account_index_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->合并报表->调整分录抵消明细
    public Menu gotoReportViewEntryBatch() {
        Reporter.log("进入 会计核算->月度报表->合并报表->调整分录抵消明细");
        String final_url = Configurator.getURL() + MenuPage.report_view_entry_batch_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->合并报表->口径列表
    public Menu gotoAcctCombinedPlateIndex() {
        Reporter.log("进入 会计核算->月度报表->合并报表->口径列表");
        String final_url = Configurator.getURL() + MenuPage.acct_combined_plate_index_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->合并报表->管报税报公司合并报表
    public Menu gotoDiameterCombinedReport() {
        Reporter.log("进入 进入会计核算->月度报表->合并报表->管报税报公司合并报表");
        String final_url = Configurator.getURL() + MenuPage.combined_report_diameter_combined_report_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->合并报表->股权抵消报表
    public Menu gotoEquityCancellation() {
        Reporter.log("进入 会计核算->月度报表->合并报表->股权抵消报表");
        String final_url = Configurator.getURL() + MenuPage.combined_report_equity_cancellation_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->合并报表->关联交易抵消报表
    public Menu gotoRelatedTransactionCancellation() {
        Reporter.log("进入 会计核算->月度报表->合并报表->关联交易抵消报表");
        String final_url = Configurator.getURL() + MenuPage.combined_report_related_transaction_cancellation_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->合并报表->关联方来往抵消报表
    public Menu gotoRelatedPartyCancellation() {
        Reporter.log("进入 会计核算->月度报表->合并报表->关联方来往抵消报表");
        String final_url = Configurator.getURL() + MenuPage.combined_report_related_party_cancellation_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->分析报表->往来账报表->公司余额交叉验证表
    public Menu gotoAccountingBalanceVerify() {
        Reporter.log("进入 会计核算->月度报表->分析报表->往来账报表->公司余额交叉验证表");
        String final_url = Configurator.getURL() + MenuPage.accounting_balance_verify_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->分析报表->往来账报表->集团内单位往来账
    public Menu gotoInternalAccounting() {
        Reporter.log("进入 会计核算->月度报表->分析报表->往来账报表->集团内单位往来账");
        String final_url = Configurator.getURL() + MenuPage.accounting_internal_accounting_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

    //    进入会计核算->月度报表->单体报表->单体A1A2A3报表
    public Menu gotoA1A2A3Report() {
        Reporter.log("进入 会计核算->月度报表->单体报表->单体A1A2A3报表");
        String final_url = Configurator.getURL() + MenuPage.accounting_report_a1a2a3_report_url; //拼接访问url
        driver.get(final_url);
        return this;
    }

}

