<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <form id="regionStockDailyForm" action="/region-stock-daily/store" method="post" style="display: none">
        <input type="hidden" name="id" value="${finRegionStockDaily.id}"/>
        <input type="hidden" name="stockId" value="${finRegionStockDaily.stockId}"/>
    </form>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region: 'west', title: '<spring:message code="region.stock.daily.in"/>', split: true, border: true" style="width:400px;">
            <div class="tableGroup">
                <table id="inRegionStockDailyGrid" class="easyui-datagrid" data-options="
                    rownumbers: true,
                    fit: true,
                    method:'post',
				    pagination: false,
				    pageSize: 10,
				    striped: true,
				    singleSelect: false,
				    onLoadSuccess: loadInRegionStockDailySuccess,
				    toolbar: '#inRegionStockDailyTB',
				    url: '/region-stock-daily/children?stockId=${finRegionStockDaily.stockId}&dateTime=${finRegionStockDaily.createTime}'">
                    <thead>
                    <tr>
                        <th data-options="field:'stockId', width: 150, halign: 'center', align: 'center', formatter: stockFormatter">
                            <spring:message code="region.stock.daily.store"/>
                        </th>
                        <th data-options="field:'income', width: 167, halign: 'center', align: 'center', formatter: MXF.priceFormatter">
                            <spring:message code="region.stock.daily.income"/>
                        </th>
                    </tr>
                    </thead>
                </table>
                <div id="inRegionStockDailyTB">
                    <div>
                        <div style="height: 30px; text-align: right; line-height: 30px; padding: 0 10px;">
                            <span style="font-weight: 600;"><spring:message code="region.stock.daily.income.total"/>：</span>
                            <span id="incomeTotal"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div data-options="region: 'center', title: '<spring:message code="region.stock.daily.out"/>', border: true">
            <div class="tableGroup" style="position: relative">
                <table id="stockDailyExpendItemGrid" class="easyui-datagrid"
                       data-options="
                           singleSelect: true,
                           fit: true,
                           method: 'get',
					       pagination: false,
					       striped: true,
					       onSelect: selectExpendItem,
						   onLoadSuccess: loadExpendItemSuccess,
						   toolbar: '#stockDailyExpendItemTB',
						   url: '/stock-daily-expend-item/region?stockId=${finRegionStockDaily.stockId}&dateTime=${finRegionStockDaily.createTime}'">
                    <thead>
                    <tr>
                        <th data-options="field: 'expendItemId', width: 120, halign: 'center', align: 'center', formatter: formatterExpendItem">
                            <spring:message code="region.stock.daily.type"/>
                        </th>
                        <th data-options="field: 'detail', width: 250, halign: 'center', align: 'left'"><spring:message code="region.stock.daily.project"/></th>
                        <th data-options="field: 'amount', width: 167, halign: 'center', align: 'center', formatter: MXF.priceFormatter">
                            <spring:message code="region.stock.daily.project"/>
                        </th>
                    </tr>
                    </thead>
                </table>
                <div id="stockDailyExpendItemTB">
                    <div>
                        <a href="#" data-cmd="addStockDailyExpendItem" title="<spring:message code="common.add"/>"
                           class="easyui-linkbutton" iconCls="icon-add" plain="true">
                            <spring:message code="common.add"/>
                        </a>
                        <a href="#" data-cmd="editStockDailyExpendItem" title="<spring:message code="common.edit"/>"
                           mustsel remote="false"
                           data-options="disabled:true"
                           class="easyui-linkbutton" iconCls="icon-edit" plain="true">
                            <spring:message code="common.edit"/>
                        </a>
                        <a href="#" data-cmd="del" mustsel data-options="disabled:true" class="easyui-linkbutton"
                           iconCls="icon-remove" plain="true">
                            <spring:message code="common.delete"/>
                        </a>
                    </div>
                </div>
                <div style="position: absolute; top: 0; right: 0; width: 240px; height: 40px;">
                    <div style="height: 40px; line-height: 40px; font-size: 14px; text-align: center; padding: 0 20px;">
                        <span style="font-weight: 600;"><spring:message code="region.stock.daily.exprice.total"/>：</span>
                        <span id="expendTotal"></span>
                    </div>
                </div>
            </div>
        </div>
        <div data-options="region: 'east', title: '<spring:message code="region.stock.daily.deposit"/>', border: true" style="width:400px;">
            <form id="outRegionStockDailyForm" style="width: 390px; margin-top: 30px;">
                <div class="input-div">
                    <label class="label-top"><spring:message code="region.stock.daily.safe"/></label>
                    <input id="safe" class="easyui-textbox theme-textbox-radius" name="safe"
                           value="${finRegionStockDaily.safe * 0.01}"
                           style="width: 250px" data-options="
                                onChange: safeChange">
                </div>
                <div class="input-div">
                    <label class="label-top"><spring:message code="region.stock.daily.deposit"/></label>
                    <input id="deposit" class="easyui-textbox theme-textbox-radius" name="deposit"
                           value="${finRegionStockDaily.deposit * 0.01}"
                           style="width: 250px">
                    <a class="easyui-linkbutton" onclick="calSafe()" style="position: absolute; margin-left: 3px;"><spring:message code="region.stock.daily.cal"/></a>
                </div>
            </form>
        </div>
    </div>
    <div style="position: absolute; height: 46px; width: 100%; z-index: 1000; border-top: 1px solid #aaa;">
        <div style="margin: 2px 30px 0 0; float: right">
            <a class="easyui-linkbutton button-lg button-default" onclick="saveStockDaily()"><spring:message
                    code="common.submit"/></a>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        MXF.getTabContentHeight();
    });

    function formatterExpendItem(val, row, index) {
        var category = null;
        $.ajax({
            type: "GET",
            url: "/expend-item/info?id=" + val,
            async: false,
            success: function (data) {
                category = data.category;
                row.categoryFormatter = category;
            }
        });
        return category;
    }

    function stockFormatter(val, row) {
        var stockName = null;
        $.ajax({
            type: "GET",
            url: "/stock/info?id=" + val,
            async: false,
            success: function (data) {
                stockName = data.name;
                row.stockName = stockName;
            }
        })
        return stockName;
    }

    function selectExpendItem(index, row) {
        console.log(index, row);
    }

    function loadInRegionStockDailySuccess(data) {
        if (data.footer) {
            // 累计总额
            $('#incomeTotal').text(MXF.priceFormatter(data.footer.incomeTotal));
        } else {
            $('#incomeTotal').text(MXF.priceFormatter(0));
        }
    }

    function loadExpendItemSuccess(data) {
        if (data.footer) {
            // 支出总计
            $('#expendTotal').text(MXF.priceFormatter(data.footer.expendTotal));
        } else {
            $('#expendTotal').text(MXF.priceFormatter(0));
        }
    }
    
    function calSafe() {
        // 更新存
        var data = $('#stockDailyExpendItemGrid').datagrid('getData');
        if (data.rows) {
            var expendTotal = 0;
            $.each(data.rows, function (index, column) {
                if (column.categoryFormatter == 'C') {
                    expendTotal += column.amount;
                }
            });
            var safe = parseFloat($('#safe').textbox('getValue'));
            var incomeTotal = parseFloat(MXF.priceParse($('#incomeTotal').text()));
            var total = incomeTotal + safe - expendTotal * 0.01;
            $('#deposit').textbox('setValue', total);
        }
    }

    function safeChange(oldValue, newValue) {
        var data = $('#stockDailyExpendItemGrid').datagrid('getData');
        // 更新存
        if (data.rows) {
            var safe = parseFloat(oldValue);
            if (safe) {
                var expendTotal = 0;
                $.each(data.rows, function (index, column) {
                    if (column.categoryFormatter == 'C') {
                        expendTotal += column.amount;
                    }
                });
                var incomeTotal = parseFloat(MXF.priceParse($('#incomeTotal').text()));
                var total = incomeTotal + safe - expendTotal * 0.01;
                $('#deposit').textbox('setValue', total);
            }
        }
    }

    function addStockDailyExpendItem() {
        var editWindow = $('<div id="addStockDailyExpendItemWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: '<spring:message code="region.stock.daily.zcmx"/>',
            modal: true,
            height: 350,
            width: 600,
            maximized: false,
            href: '/stock-daily-expend-item/edit',
            onLoad: function () {
                var $form = editWindow.find('form');
                var formData = {
                    dailyId: ${finRegionStockDaily.id},
                }
                $form.form('load', formData);
            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });
    }

    function editStockDailyExpendItem() {
        var row = $('#stockDailyExpendItemGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("<spring:message code="message.select"/>！");
            return;
        }

        var editWindow = $('<div id="editStockDailyExpendItemWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: '<spring:message code="region.stock.daily.bjzcmx"/>',
            modal: true,
            height: 350,
            width: 600,
            maximized: false,
            href: '/stock-daily-expend-item/edit?id=' + row.id,
            onLoad: function () {
                //var $form = editWindow.find('form');
                //$form.data('window',editWindow);
            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });
    }

    function saveStockDaily() {
        var data = {
            id: $('#regionStockDailyForm').find('[name=id]').val(),
            safe: $('#outRegionStockDailyForm').find('[name=safe]').val(),
            deposit: $('#outRegionStockDailyForm').find('[name=deposit]').val(),
            expendTotal: MXF.priceParse($('#expendTotal').text()),
        };
        if (data) {
            $.post('/region-stock-daily/store', data, function (res) {
                if (res.success) {
                    MXF.alert('<spring:message code="message.success"/>！', true);
                    $('#regionStockDailyGrid').datagrid('reload');
                } else {
                    MXF.alert('<spring:message code="message.fail"/>，' + res.info, false);
                }
            });
        }
    }

</script>
