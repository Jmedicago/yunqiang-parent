<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <form id="stockDailyForm" action="/stock-daily/store" method="post" style="display: none">
        <input type="hidden" name="id" value="${finStockDaily.id}"/>
        <input type="hidden" name="stockId" value="${finStockDaily.stockId}"/>
    </form>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region: 'west', title: '进', split: true, border: true" style="width:400px;">
            <form id="inStockDailyForm" style="width: 390px; margin-top: 30px;">
                <div class="input-div">
                    <label class="label-top">日收入</label>
                    <input id="income" class="easyui-textbox theme-textbox-radius" name="income"
                           value="${finStockDaily.income * 0.01}" data-options="
                                onChange: function(oldValue, newValue) {
                                    var expendTotal = MXF.priceParse($('#expendTotal').text());
                                    var income = $(this).numberbox('getValue');
                                    if (income) {
                                        income = parseFloat(income);
                                        $('#salesTotal').text(MXF.priceFormatter((expendTotal + income) * 100));
                                    } else {
                                        $('#salesTotal').text(MXF.priceFormatter(expendTotal * 100));
                                    }
                                }"
                           style="width: 250px">
                </div>
            </form>
        </div>
        <div data-options="region: 'center', title: '支出', border: true">
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
						   url: '/stock-daily-expend-item/json?dailyId=${finStockDaily.id}'">
                    <thead>
                    <tr>
                        <th data-options="field: 'expendItemId', width: 120, halign: 'center', align: 'center', formatter: formatterExpendItem">类别</th>
                        <th data-options="field: 'detail', width: 250, halign: 'center', align: 'left'">项目</th>
                        <th data-options="field: 'amount', width: 167, halign: 'center', align: 'center', formatter: MXF.priceFormatter">金额</th>
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
                    <div style="height: 40px; line-height: 40px; font-size: 14px; text-align: right; padding: 0 20px;">
                        <span style="font-weight: 600;">支出总计：</span>
                        <span id="expendTotal"></span>
                    </div>
                </div>
            </div>
        </div>
        <div data-options="region: 'east', title: '存', border: true" style="width:400px;">
            <form id="outStockDailyForm" style="width: 390px; margin-top: 30px;">
                <div class="input-div">
                    <label class="label-top">进货值</label>
                    <input class="easyui-textbox theme-textbox-radius" name="purch" value="${finStockDaily.purch * 0.01}"
                           style="width: 250px">
                </div>
                <div class="input-div">
                    <label class="label-top">欠款</label>
                    <input class="easyui-textbox theme-textbox-radius" name="arrears" value="${finStockDaily.arrears * 0.01}"
                           style="width: 250px">
                </div>
            </form>
        </div>
    </div>
    <div style="position: absolute; height: 46px; width: 100%; z-index: 1000; border-top: 1px solid #aaa;">
        <div style="float: left">
            <div style="height: 46px; line-height: 46px; font-size: 14px; margin-left: 30px;">
                <span style="font-weight: 600;">销售额：</span>
                <span id="salesTotal"></span>
            </div>
        </div>
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

    function formatterExpendItem(val) {
        var category = null;
        $.ajax({
            type: "GET",
            url: "/expend-item/info?id=" + val,
            async: false,
            success: function (data) {
                category = data.category;
            }
        });
        return category;
    }

    function selectExpendItem(index, row) {
        console.log(index, row);
    }

    function loadExpendItemSuccess(data) {
        if (data.footer) {
            // 支出总计
            $('#expendTotal').text(MXF.priceFormatter(data.footer.expendTotal));
            var expendTotal = data.footer.expendTotal * 0.01;
            var income = $('#income').numberbox('getValue');
            if (income) {
                income = parseFloat(income);
                // 销售额
                $('#salesTotal').text(MXF.priceFormatter((expendTotal + income) * 100));
            } else {
                // 销售额
                $('#salesTotal').text(MXF.priceFormatter(expendTotal * 100));
            }
        } else {
            $('#expendTotal').text(MXF.priceFormatter(0));
            var income = $('#income').numberbox('getValue');
            if (income) {
                income = parseFloat(income);
                // 销售额
                $('#salesTotal').text(MXF.priceFormatter(income * 100));
            } else {
                // 销售额
                $('#salesTotal').text(MXF.priceFormatter(0));
            }
        }
    }

    function addStockDailyExpendItem() {
        var editWindow = $('<div id="addStockDailyExpendItemWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: '新增支出明细',
            modal: true,
            height: 350,
            width: 600,
            maximized: false,
            href: '/stock-daily-expend-item/edit',
            onLoad: function () {
                var $form = editWindow.find('form');
                var formData = {
                    dailyId: ${finStockDaily.id},
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
            MXF.error("请选择一个您要编辑的支出明细！");
            return;
        }

        var editWindow = $('<div id="editStockDailyExpendItemWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: '编辑支出明细',
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
            id: $('#stockDailyForm').find('[name=id]').val(),
            income: $('#inStockDailyForm').find('[name=income]').val(),
            purch: $('#outStockDailyForm').find('[name=purch]').val(),
            arrears: $('#outStockDailyForm').find('[name=arrears]').val(),
            expendTotal: MXF.priceParse($('#expendTotal').text()),
            sales: MXF.priceParse($('#salesTotal').text())
        };
        if (data) {
            $.post('/stock-daily/store', data, function (res) {
                if (res.success) {
                    MXF.alert('成功！', true);
                    $('#stockDailyGrid').datagrid('reload');
                } else {
                    MXF.alert('失败，' + res.info, false);
                }
            });
        }
    }

</script>
