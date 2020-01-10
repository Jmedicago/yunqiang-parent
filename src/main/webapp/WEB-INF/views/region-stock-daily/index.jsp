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
				    url: '/region-stock-daily/json'">
            <thead>
            <tr>
                <th data-options="field: 'id', checkbox: true"></th>
                <th data-options="field: 'date',width: 120, halign: 'center', align: 'center', formatter: MXF.dateFormatter">
                    日期
                </th>
                <th data-options="field:'stockId', width: 100, halign: 'center', align: 'center', formatter: stockFormatter">
                    仓库名
                </th>
                <%--<th data-options="field:'incomeSubTotal', width: 100, halign: 'center', align: 'center', formatter: MXF.priceFormatter">
                    日总额
                </th>--%>
                <%--<th data-options="field: 'expendSubTotal', width: 100, halign: 'center', align: 'center', formatter: MXF.priceFormatter">
                    日支出总计
                </th>--%>
                <%--<th data-options="field: 'deposit', width: 100, halign: 'center', align: 'center', formatter: MXF.priceFormatter">
                    保险柜现金
                </th>--%>
                <th data-options="field: 'purch', width: 100, halign: 'center', align: 'center', formatter: MXF.priceFormatter">
                    上货金额
                </th>
                <%--<th data-options="field: 'arrears', width: 100, halign: 'center', align: 'center', formatter: MXF.priceFormatter">
                    最新客商欠款
                </th>--%>
                <%--<th data-options="field: 'sales', width: 100, halign: 'center', align: 'center', formatter: MXF.priceFormatter">
                    日销售额
                </th>--%>
            </tr>
            </thead>
        </table>
        <div id="stockDailyTB">
            <div>
                <%--<a href="#" data-cmd="addStockDaily" remote="false"
                   class="easyui-linkbutton" iconCls="icon-add" plain="true">
                    <spring:message code="common.add"/>
                </a>--%>
                <a href="#" data-cmd="add" remote="false" title="填报" width="600px" ; height="450px;"
                   class="easyui-linkbutton" iconCls="icon-add" plain="true">
                    填报
                </a>
                <a href="#" data-cmd="editDzStockDaily" title="编辑" mustsel width="600px" ; height="450px;"
                   remote="false"
                   data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-edit" plain="true">
                    编辑
                </a>
                <a href="#" data-cmd="showStockDaily" class="easyui-linkbutton"
                   plain="true">
                    <i class="iconfont">&#xe6cb;</i>
                    报表
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
        return row.bisStock.name;
    }

    function editDzStockDaily() {
        var row = $('#stockDailyGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("请选择一个您要编辑的当日资金出账明细！");
            return;
        }
        // 打开编辑页面
        var editWindow = $('<div id="editDzStockDailyWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: '编辑',
            modal: true,
            width: 600,
            height: 450,
            href: '/region-stock-daily/edit?id=' + row.id,
            onLoad: function () {
                //var $form = editWindow.find('form');
                //$form.data('window',editWindow);
            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });
    }

    function showStockDaily() {
        window.open("/report?rn=dz-daily&stockId=${stockId}");
    }
</script>


