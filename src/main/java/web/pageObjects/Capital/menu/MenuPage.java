package web.pageObjects.Capital.menu;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/17
 * Time: 下午3:40
 * url跳转定义
 */
public class MenuPage {
    //资金管理->银行流水管理
    public static String flow_company_index_url = "/flow/company/index";//公司管理列表url
    public static String flow_abnormal_watercourse_url = "/flow/flow-abnormal-watercourse/index";//异常流水列表url
    public static String flow_diff_plate_report_url = "/flow/bank-flow/diff-plate-report";//板块流水列表url
    public static String flow_bank_flow_url = "/flow/bank-flow/index";//流水列表url
    public static String flow_everyday_account_balance_index_url = "/flow/everyday-account-balance/index";//账户余额列表url
    public static String flow_bank_account_url = "/flow/flow-bank-account/index";//账户管理列表url

    //资金管理->银行流水管理->银行流水科目管理
    public static String flow_classify_index_url = "/flow/classify/index";//分类管理url

    //资金管理->银行流水管理->银行流水相关报表
    public static String flow_prepare_theory_money_list_url = "/prepare-theory-money/list";//拨备金核对报表url
    public static String flow_plate_balance_index_url = "/flow/flow-plate-balance/index";//资金日报报表url

    //会计核算->月度报表
    public static String acct_trial_balance_index_url = "/accounting/acct-trial-balance/index";//科目余额url

    //会计核算->月度报表->单体报表
    public static String report_single_company_report_url = "/accounting/report/single-company-report";//单体报表url
    public static String report_capital_derivation_report_url = "/accounting/report/capital-derivation-report";//单体资金推导表url
    public static String report_variation_analysis_report_url = "/accounting/report/variation-analysis-report";//单体变动分析表url

    //会计核算->月度报表->合并报表
    public static String report_view_entry_batch_url = "/accounting/report/view-entry-batch";//调整分录抵消明细url
    public static String acct_combined_plate_index_url = "/accounting/acct-combined-plate/index";//口径列表url
    public static String combined_report_diameter_combined_report_url = "/accounting/combined-report/diameter-combined-report";//管报税报公司合并报表url
    public static String combined_report_equity_cancellation_url = "/accounting/combined-report/equity-cancellation";//股权抵消报表url
    public static String combined_report_related_transaction_cancellation_url = "/accounting/combined-report/related-transaction-cancellation";//关联交易抵消报表url
    public static String combined_report_related_party_cancellation_url = "/accounting/combined-report/related-party-cancellation";//关联方来往抵消报表url

    //会计核算->月度报表->分析报表->往来账报表
    public static String accounting_balance_verify_url = "/accounting/acct-trial-balance/accounting-balance-verify";//公司余额交叉验证表url
    public static String accounting_internal_accounting_url = "/accounting/acct-trial-balance/internal-accounting";//集团内单位往来账url

    //会计核算->月度报表->分析报表
    public static String report_cash_transaction_report_url = "/accounting/report/cash-transaction-report";//现金流量表url
    public static String accounting_report_error_list_url = "/accounting/report/report-error-list";//异常统计页url
    public static String accounting_report_a1a2a3_report_url = "/accounting/report/a1a2a3-report";//单体A1A2A3表url

    //会计核算->月度报表->配置管理
    public static String accounting_account_index_url = "/accounting/accounting-account/index";//科目列表url
}
