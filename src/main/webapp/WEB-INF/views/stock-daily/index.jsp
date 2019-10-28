<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="stockDailyGrid" class="easyui-datagrid" title="每日资金进出账明细" data-options="
                    rownumbers: true,
                    fit: true,
                    method:'post',
				    pagination: true,
				    pageSize: 10,
				    striped: true,
				    singleSelect: false,
				    toolbar: '#stockDailyTB',
				    url: '/stock-daily/json'">
            <thead>
            <tr>
                <th data-options="field: 'id', checkbox: true"></th>
                <th data-options="field: 'createTime',width: 200, halign: 'center', align: 'center', formatter: MXF.dateTimeFormatter">
                    日期
                </th>
                <th data-options="field:'stockId', width: 100, halign: 'center', align: 'center', formatter: stockFormatter">仓库名</th>
                <th data-options="field:'income', width: 180, halign: 'center', align: 'center', formatter: MXF.priceFormatter">日收入</th>
                <th data-options="field: 'expendTotal', width: 200, halign: 'center', align: 'center', formatter: MXF.priceFormatter">日支出总计</th>
                <th data-options="field: 'sales', width: 200, halign: 'center', align: 'center', formatter: MXF.priceFormatter">销售额</th>
                <th data-options="field: 'purch', width: 200, halign: 'center', align: 'center', formatter: MXF.priceFormatter">进货值</th>
                <th data-options="field: 'arrears', width: 200, halign: 'center', align: 'center', formatter: MXF.priceFormatter">欠款</th>
            </tr>
            </thead>
        </table>
        <div id="stockDailyTB">
            <div>
                <a href="#" data-cmd="addStockDaily" remote="false"
                   class="easyui-linkbutton" iconCls="icon-add" plain="true">
                    <spring:message code="common.add"/>
                </a>
                <a href="#" data-cmd="editStockDaily" title="<spring:message code="common.edit"/>" mustsel
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

    function addStockDaily() {
        MXF.confirm('是否新建当日资金出账明细？', function (res) {
            // 创建当日资金出账明细
            $.post('/stock-daily/store', function (res) {
                if (res.success) { // 新建成功
                    $('#stockDailyGrid').datagrid('reload');
                    // 打开编辑页面
                    var editWindow = $('<div id="addStockDailyWindow"></div>');
                    editWindow.appendTo('body');
                    $(editWindow).window({
                        title: '当日资金出账明细',
                        modal: true,
                        maximized: true,
                        href: '/stock-daily/edit?id=' + res.data.id,
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

    function editStockDaily() {
        var row = $('#stockDailyGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("请选择一个您要编辑的当日资金出账明细！");
            return;
        }
        // 打开编辑页面
        var editWindow = $('<div id="editStockDailyWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: '当日资金出账明细',
            modal: true,
            maximized: true,
            href: '/stock-daily/edit?id=' + row.id,
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

