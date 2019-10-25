<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <form id="stockQuarterlyForm" action="/stock-quarterly/store" method="post" style="display: none">
        <input type="hidden" name="id" value="${finStockQuarterly.id}"/>
        <input type="hidden" name="stockId" value="${finStockQuarterly.stockId}"/>
    </form>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region: 'west', title: '进', split: true, border: true" style="width:400px;">
            <form id="intStockQuarterlyForm" style="width: 390px; margin-top: 30px;">
                <div class="input-div">
                    <label class="label-top">进货总值</label>
                    <input class="easyui-textbox theme-textbox-radius" id="purchTotal" name="purchTotal"
                           value="${finStockQuarterly.purchTotal * 0.01}"
                           style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">上季度欠款</label>
                    <input class="easyui-textbox theme-textbox-radius" id="beforeArrears" name="beforeArrears"
                           value="${finStockQuarterly.beforeArrears * 0.01}"
                           style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">上季度零钱</label>
                    <input class="easyui-textbox theme-textbox-radius" id="beforeChange" name="beforeChange"
                           value="${finStockQuarterly.beforeChange * 0.01}"
                           style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">上季度库存货值</label>
                    <input class="easyui-textbox theme-textbox-radius" id="beforeInventory" name="beforeInventory"
                           value="${finStockQuarterly.beforeInventory * 0.01}"
                           style="width: 250px">
                </div>
            </form>
        </div>
        <div data-options="region: 'center', title: '出', border: true">
            <form id="outStockQuarterlyForm" style="width: 390px; margin-top: 30px;">
                <div class="input-div">
                    <label class="label-top">销售额</label>
                    <input class="easyui-textbox theme-textbox-radius" id="salesTotal" name="salesTotal"
                           value="${finStockQuarterly.salesTotal * 0.01}"
                           style="width: 250px">
                </div>
            </form>
        </div>
        <div data-options="region: 'east', title: '存', border: true" style="width:400px;">
            <form id="saveStockQuarterlyForm" style="width: 390px; margin-top: 30px;">
                <div class="input-div">
                    <label class="label-top">当前季度欠款</label>
                    <input class="easyui-textbox theme-textbox-radius" id="arrears" name="arrears"
                           value="${finStockQuarterly.arrears * 0.01}"
                           style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">当前季度零钱</label>
                    <input class="easyui-textbox theme-textbox-radius" id="changes" name="changes"
                           value="${finStockQuarterly.changes * 0.01}" data-options="
                                onChange: function(oldValue, newValue) {
                                    var purchTotal = $('#purchTotal').textbox('getValue');
                                    var beforeArrears = $('#beforeArrears').textbox('getValue');
                                    var beforeChange = $('#beforeChange').textbox('getValue');
                                    var beforeInventory = $('#beforeInventory').textbox('getValue');
                                    var inTotal = parseFloat(purchTotal) + parseFloat(beforeArrears) + parseFloat(beforeChange) +parseFloat(beforeInventory);
                                    //console.log('in', inTotal);

                                    var arrears = $('#arrears').textbox('getValue');
                                    var inventory = $('#inventory').textbox('getValue');
                                    var saveTotal = parseFloat(arrears) + parseFloat(inventory) + parseFloat(oldValue);
                                    //console.log('save', saveTotal);

                                    var salesTotal = $('#salesTotal').textbox('getValue');
                                    var outTotal = parseFloat(salesTotal);
                                    //console.log('out', outTotal);

                                    var pl = outTotal + saveTotal - inTotal;
                                    //console.log('pl', pl);

                                    $('#pl').text(MXF.priceFormatter(pl * 100));
                                }" style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">当前季度库存货值</label>
                    <input class="easyui-textbox theme-textbox-radius" id="inventory" name="inventory"
                           value="${finStockQuarterly.inventory * 0.01}" data-options="
                                onChange: function(oldValue, newValue) {
                                    var purchTotal = $('#purchTotal').textbox('getValue');
                                    var beforeArrears = $('#beforeArrears').textbox('getValue');
                                    var beforeChange = $('#beforeChange').textbox('getValue');
                                    var beforeInventory = $('#beforeInventory').textbox('getValue');
                                    var inTotal = parseFloat(purchTotal) + parseFloat(beforeArrears) + parseFloat(beforeChange) +parseFloat(beforeInventory);
                                    //console.log('in', inTotal);

                                    var arrears = $('#arrears').textbox('getValue');
                                    var changes = $('#changes').textbox('getValue');
                                    var saveTotal = parseFloat(arrears) + parseFloat(changes) + parseFloat(oldValue);
                                    console.log('save', saveTotal);

                                    var salesTotal = $('#salesTotal').textbox('getValue');
                                    var outTotal = parseFloat(salesTotal);
                                    //console.log('out', outTotal);

                                    var pl = outTotal + saveTotal - inTotal;
                                    //console.log('pl', pl);

                                    $('#pl').text(MXF.priceFormatter(pl * 100));
                                }" style="width: 250px">
                </div>
            </form>
        </div>
    </div>
    <div style="position: absolute; height: 46px; width: 100%; z-index: 1000; border-top: 1px solid #aaa;">
        <div style="float: left">
            <div style="height: 46px; line-height: 46px; font-size: 14px; margin-left: 30px;">
                <span style="font-weight: 600;">盈亏：</span>
                <span id="pl" class="money">${finStockQuarterly.pl * 0.01}</span>
            </div>
        </div>
        <div style="margin: 2px 30px 0 0; float: right">
            <a class="easyui-linkbutton button-lg button-default" onclick="saveStockQuarterly()"><spring:message
                    code="common.submit"/></a>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        MXF.getTabContentHeight();

        // 金额格式化
        OSREC.CurrencyFormatter.formatAll({selector: '.money', currency: 'CNY'});
    });

    function saveStockQuarterly() {
        var data = {
            id: $('#stockQuarterlyForm').find('[name=id]').val(),
            changes: $('#changes').textbox('getValue'),
            inventory: $('#inventory').textbox('getValue'),
            pl: MXF.priceParse($('#pl').text())
        };
        if (data) {
            $.post('/stock-quarterly/store', data, function (res) {
                if (res.success) {
                    MXF.alert('提交成功！', true);
                    $('#stockQuarterlyGrid').datagrid('reload');
                } else {
                    MXF.alert('提交失败！', false);
                }
            });
        }
    }

</script>
