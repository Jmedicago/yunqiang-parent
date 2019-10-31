<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="orderVerifyGrid" class="easyui-datagrid" title="订单审核" data-options="
                   rownumbers: true,
                   fit: true,
                   method: 'post',
				   pagination: true,
				   pageSize: 10,
				   striped: true,
				   singleSelect: false,
				   toolbar: '#orderVerifyTB',
				   url:'/order-verify/json'">
            <thead>
            <tr>
                <th data-options="field: 'id', checkbox: true"></th>
                <th data-options="field: 'orderSn', width: 80">订单号</th>
                <th data-options="field: 'stockId', width: 80, formatter: stockFormatter">零售店</th>
                <th data-options="field: 'status', width: 80, formatter: orderVerilyStateFormatter">订单状态</th>
                <th data-options="field: 'totalMoney', width: 100, formatter: MXF.priceFormatter">金额总计</th>
                <th data-options="field: 'confirmTime', width: 130, formatter: MXF.dateTimeFormatter">下单时间</th>
                <th data-options="field: 'digest', width:200">明细</th>
            </tr>
            </thead>
        </table>
        <div id="orderVerifyTB">
            <div>
                <a href="#" data-cmd="del" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-remove" plain="true">
                    <spring:message code="common.delete"/>
                </a>
                <a href="#" data-cmd="editOrderDetail" title="<spring:message code="common.edit"/>" height="747" width="590" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-edit" plain="true">
                    <spring:message code="common.edit"/>
                </a>
                <span class="buttonSplit">&nbsp;</span>
                <a href="#" data-cmd="sendShip" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-ok" plain="true">
                    发货
                </a>
                <a href="#" data-cmd="printOrder" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-print" plain="true">
                    打印
                </a>
            </div>
            <div class="searchForm">
                <form>
                    订单编号：
                    <input class="easyui-textbox theme-textbox-radius" name="name" style="width:200px;">&nbsp;
                    <a href="javascript:;" data-cmd="search" class="easyui-linkbutton button-default">
                        <spring:message code="common.search"/>
                    </a>
                    <a href="javascript:;" data-cmd="resetSearch" class="easyui-linkbutton">
                        <spring:message code="common.reset"/>
                    </a>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        MXF.getTabContentHeight();
    });

    function orderVerilyStateFormatter(v) {
        if (v == 0) return '<red>待审批</red>';
        if (v == 1) return '<blue>待发货</blue>';
        if (v == 2) return '<orange>待收货</orange>';
        if (v == 3) return '<green>订单完成</green>';
        if (v == 5) return '<yellow>订单关闭</yellow>';
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
    
    function editOrderDetail() {
        var row = $('#orderVerifyGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("请至少选择一条记录，再继续操作！");
            return;
        }
        MXF.openDialog('#editOrderVerifyWindow', '编辑', '/order/edit?id=' + row.id, function () {

        }, 590, 747);
    }
    
    function sendShip() {
        var row = $('#orderVerifyGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("请至少选择一条记录，再继续操作！");
            return;
        }
        MXF.confirm('确认发货？', function () {
            $.get('/order-verify/sendShip?orderId=' + row.id, function (res) {
                if (res.success) {
                    $('#orderVerifyGrid').datagrid('reload');
                    MXF.alert(res.info + res.message, res.success);
                }
            });
        }, function () {
            MXF.alert('取消发货', true);
        })
    }
    
    function printOrder() {
        var row = $('#orderVerifyGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("请至少选择一条记录，再继续操作！");
            return;
        }
        MXF.ajaxing(true);
        $.get('/order-verify/check-print?id=' + row.id, function (res) {
            MXF.ajaxing(false);
            if (res.success) {
                MXF.openDialog('#editOrderVerifyWindow', '打印出库单', '/order-verify/print?id=' + row.id, function () {
                }, 606, 747);
            } else {
                MXF.alert(res.message, false);
            }
        })
    }
</script>
