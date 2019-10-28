<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="stockQuarterlyGrid" class="easyui-datagrid" title="店员季度表报" data-options="
                    rownumbers: true,
                    fit: true,
                    method:'post',
				    pagination: true,
				    pageSize: 10,
				    striped: true,
				    singleSelect: false,
				    toolbar: '#stockQuarterlyTB',
				    url: '/stock-quarterly/json'">
            <thead>
            <tr>
                <th data-options="field: 'id', checkbox: true"></th>
                <th data-options="field: 'year',width: 100, halign: 'center', align: 'center'">年份</th>
                <th data-options="field: 'quarterly', width: 100, halign: 'center', align: 'center', formatter: quarterFormatter">季度</th>
                <th data-options="field: 'stockId', width: 180, halign: 'center', align: 'center', formatter: stockFormatter">零售店名</th>
                <th data-options="field: 'purchTotal', width: 180, halign: 'center', align: 'center', formatter: MXF.priceFormatter">当前季度进货总值</th>
                <th data-options="field: 'beforeArrears', width: 200, halign: 'center', align: 'center', formatter: MXF.priceFormatter">上季度欠款</th>
                <th data-options="field: 'beforeInventory', width: 200, halign: 'center', align: 'center', formatter: MXF.priceFormatter">上季度库存货值</th>
                <th data-options="field: 'beforeChange', width: 200, halign: 'center', align: 'center', formatter: MXF.priceFormatter">上季度零钱</th>
                <th data-options="field: 'salesTotal', width: 200, halign: 'center', align: 'center', formatter: MXF.priceFormatter">销售额</th>
                <th data-options="field: 'arrears', width: 200, halign: 'center', align: 'center', formatter: MXF.priceFormatter">当前季度欠款</th>
                <th data-options="field: 'changes', width: 200, halign: 'center', align: 'center', formatter: MXF.priceFormatter">当前季度零钱</th>
                <th data-options="field: 'inventory', width: 200, halign: 'center', align: 'center', formatter: MXF.priceFormatter">当前库存货值</th>
                <th data-options="field: 'pl', width: 200, halign: 'center', align: 'center', formatter: MXF.priceFormatter">盈亏</th>
            </tr>
            </thead>
        </table>
        <div id="stockQuarterlyTB">
            <div>
                <a href="#" data-cmd="addStockQuarterly" remote="false"
                   class="easyui-linkbutton" iconCls="icon-add" plain="true">
                    <spring:message code="common.add"/>
                </a>
                <a href="#" data-cmd="editStockQuarterly" title="<spring:message code="common.edit"/>" mustsel
                   remote="false"
                   data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-edit" plain="true">
                    <spring:message code="common.edit"/>
                </a>
            </div>
            <div class="searchForm">
                <form>
                    日期:
                    <input class="easyui-textbox theme-textbox-radius" name="dataTime" style="width:100px;">
                    <a href="javascript:;" data-cmd="search" class="easyui-linkbutton button-default"><spring:message
                            code="common.search"/></a>
                    <a href="javascript:;" data-cmd="resetSearch" class="easyui-linkbutton"><spring:message
                            code="common.reset"/></a>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        MXF.getTabContentHeight();
    });

    function quarterFormatter(val) {
        return "Q" + val;
    }

    function stockFormatter(val, row) {
        var stockName = "";
        $.ajax({
            type: "GET",
            url: "/stock/info?id=" + val,
            async: false,
            success: function (data) {
                stockName = data.name;
                row.stockName = data.name;
            }
        })
        return stockName;
    }

    function addStockQuarterly() {
        MXF.confirm('是否新建当前季度出账明细？', function (res) {
            // 创建当前季度资金出账明细
            $.post('/stock-quarterly/store', function (res) {
                if (res.success) { // 新建成功
                    $('#stockQuarterlyGrid').datagrid('reload');
                    // 打开编辑页面
                    var editWindow = $('<div id="addStockQuarterlyWindow"></div>');
                    editWindow.appendTo('body');
                    $(editWindow).window({
                        title: '当前季度出账明细',
                        modal: true,
                        maximized: true,
                        href: '/stock-quarterly/edit?id=' + res.data.id,
                        onLoad: function () {
                            /*var formData = {
                                id: res.data.id,
                            }
                            $('#stockDailyForm').form(formData);*/
                        },
                        onClose: function () {
                            editWindow.window('destroy');
                        }
                    });
                } else {
                    MXF.alert(res.info, false);
                }
            });
        })
    }

    function editStockQuarterly() {
        var row = $('#stockQuarterlyGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("请选择一个您要编辑的季度表报！");
            return;
        }
        var title = row.stockName + " " + "Q" + row.quarterly + "收支平衡表";
        // 打开编辑页面
        var editWindow = $('<div id="editStockQuarterlyWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: title,
            modal: true,
            maximized: true,
            href: '/stock-quarterly/edit?id=' + row.id,
            onLoad: function () {
                //var $form = editWindow.find('form');
                //$form.data('window',editWindow);
            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });
    }
</script>
