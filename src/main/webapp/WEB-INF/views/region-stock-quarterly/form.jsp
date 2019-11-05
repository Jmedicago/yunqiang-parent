<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <form id="regionStockQuarterlyForm" action="/region-stock-quarterly/store" method="post" style="display: none">
        <input type="hidden" name="id" value="${finRegionStockQuarterly.id}"/>
        <input type="hidden" name="stockId" value="${finRegionStockQuarterly.stockId}"/>
    </form>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region: 'west', title: '进', split: true, border: true" style="width:400px;">
            <form id="inRegionStockQuarterlyForm" style="width: 390px; margin-top: 30px;">
                <div class="input-div">
                    <label class="label-top">上季度保险柜现金</label>
                    <input class="easyui-textbox theme-textbox-radius" id="beforeSafe" name="beforeSafe"
                           value="${finRegionStockQuarterly.beforeSafe * 0.01}"
                           style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">进货总值</label>
                    <input class="easyui-textbox theme-textbox-radius" id="purchTotal" name="purchTotal"
                           value="${finRegionStockQuarterly.purchTotal * 0.01}"
                           style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">上季度欠款</label>
                    <input class="easyui-textbox theme-textbox-radius" id="beforeArrears" name="beforeArrears"
                           value="${finRegionStockQuarterly.beforeArrears * 0.01}"
                           style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">上季度零钱</label>
                    <input class="easyui-textbox theme-textbox-radius" id="beforeChange" name="beforeChange"
                           value="${finRegionStockQuarterly.beforeChange * 0.01}"
                           style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">上季度库存货值</label>
                    <input class="easyui-textbox theme-textbox-radius" id="beforeInventory" name="beforeInventory"
                           value="${finRegionStockQuarterly.beforeInventory * 0.01}"
                           style="width: 250px">
                </div>
            </form>
        </div>
        <div data-options="region: 'center', title: '出', border: true">
            <form id="outRegionStockQuarterlyForm" style="width: 390px; margin-top: 30px;">
                <div class="input-div">
                    <label class="label-top">总支出</label>
                    <input class="easyui-textbox theme-textbox-radius" id="expendTotal" name="expendTotal"
                           value="${finRegionStockQuarterly.expendTotal * 0.01}"
                           style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">总存银行</label>
                    <input class="easyui-textbox theme-textbox-radius" id="depositTotal" name="depositTotal"
                           value="${finRegionStockQuarterly.depositTotal * 0.01}"
                           style="width: 250px">
                </div>
            </form>
        </div>
        <div data-options="region: 'east', title: '存', border: true" style="width:400px;">
            <form id="saveRegionStockQuarterlyForm" style="width: 390px; margin-top: 30px;">
                <div class="input-div">
                    <label class="label-top">当前季度保险柜现金</label>
                    <input class="easyui-textbox theme-textbox-radius" id="safe" name="safe"
                           value="${finRegionStockQuarterly.safe * 0.01}"
                           style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">当前季度欠款</label>
                    <input class="easyui-textbox theme-textbox-radius" id="arrears" name="arrears"
                           value="${finRegionStockQuarterly.arrears * 0.01}"
                           style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">当前季度零钱</label>
                    <input class="easyui-textbox theme-textbox-radius" id="changes" name="changes"
                           value="${finRegionStockQuarterly.changes * 0.01}"
                           style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">当前季度库存货值</label>
                    <input class="easyui-textbox theme-textbox-radius" id="inventory" name="inventory"
                           value="${finRegionStockQuarterly.inventory * 0.01}"
                           style="width: 250px">
                </div>
            </form>
        </div>
    </div>
    <div style="position: absolute; height: 46px; width: 100%; z-index: 1000; border-top: 1px solid #aaa;">
        <div style="float: left">
            <div style="height: 46px; line-height: 46px; font-size: 14px; margin-left: 30px;">
                <span style="font-weight: 600;">盈亏：</span>
                <span id="pl" class="money">${finRegionStockQuarterly.pl * 0.01}</span>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        MXF.getTabContentHeight();

        // 金额格式化 currency: 'MZN', decimal: '.', group: ','
        OSREC.CurrencyFormatter.formatAll({selector: '.money', currency: 'MZN', decimal: '.', group: ','});
    });
</script>


