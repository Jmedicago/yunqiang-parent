<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="orderVerifyGrid" class="easyui-datagrid" title="<spring:message code="mu.order.verify"/>" data-options="
        rownumbers: true,
        fit: true,
        method: 'post',
        pagination: true,
        pageSize: 10,
        striped: true,
        singleSelect: true,
        toolbar: '#orderVerifyTB',
        url:'/order-verify/json'">
            <thead>
            <tr>
                <th data-options="field: 'id', checkbox: true"></th>
                <th data-options="field: 'orderSn', width: 80"><spring:message code="order.sn"/></th>
                <th data-options="field: 'stockId', width: 80,sortable:true, formatter: stockFormatter"><spring:message code="order.store"/></th>
                <th data-options="field: 'status', width: 80,sortable:true, formatter: orderVerilyStateFormatter"><spring:message code="order.state"/></th>
                <th data-options="field: 'totalMoney', width: 100, formatter: MXF.priceFormatter"><spring:message code="order.price.total"/></th>
                <%--<th data-options="field: 'createTime', width: 130, halign: 'center', align: 'center', formatter: MXF.dateTimeFormatter"><spring:message code="order.submit.time"/></th>--%>
                <th data-options="field: 'confirmTime', width: 130,sortable:true, formatter: MXF.dateTimeFormatter"><spring:message code="order.submit.time"/></th>
                <th data-options="field: 'shipTime', width: 130, halign: 'center', align: 'center', formatter: MXF.dateTimeFormatter"><spring:message code="order.ship.time"/></th>
                <th data-options="field: 'finishedTime', width: 130, halign: 'center', align: 'center', formatter: MXF.dateTimeFormatter"><spring:message code="order.confirm.time"/></th>
                <th data-options="field: 'digest', width:200"><spring:message code="order.detail"/></th>
                <th data-options="field: 'remark', width:200">备注</th>
            </tr>
            </thead>
        </table>
        <div id="orderVerifyTB">
            <div>
                <%--<a href="#" data-cmd="del" mustsel data-options="disabled:true" class="easyui-linkbutton"
                       iconCls="icon-remove" plain="true">
                <spring:message code="common.delete"/>
                </a>--%>
                <a href="#" data-cmd="editOrderDetail" title="<spring:message code="common.edit"/>" height="747"
                   width="590" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-edit" plain="true">
                    <spring:message code="common.edit"/>
                </a>
                <span class="buttonSplit">&nbsp;</span>
                <a href="#" data-cmd="sendShip" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-ok" plain="true">
                    <spring:message code="order.send"/>
                </a>
                <a href="#" data-cmd="printOrder" class="easyui-linkbutton"
                   iconCls="icon-print" plain="true">
                    点货清单
                </a>
            </div>
            <div class="searchForm">
                <form>
                    <spring:message code="order.sn"/>：
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
        if (v == 0) return '<red><spring:message code="order.state.dsp"/></red>';
        if (v == 1) return '<blue><spring:message code="order.state.dfh"/></blue>';
        if (v == 2) return '<orange>已打印</orange>';
        if (v == 3) return '<green><spring:message code="order.state.ddwc"/></green>';
        if (v == 5) return '<yellow><spring:message code="order.state.ddgb"/></yellow>';
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
            MXF.error("<spring:message code="message.select"/>！");
            return;
        }
        /*MXF.openDialog('#editOrderVerifyWindow', '订单号：' + row.orderSn, '/order/edit?id=' + row.id, function () {

        }, 590, 747);*/
        MXF.openDialog('#editOrderVerifyWindow', '订单号：' + row.orderSn, '/order-verify/edit?o=1&id=' + row.id, function () {

        }, 800, 747, true);
    }

    function sendShip() {
        var row = $('#orderVerifyGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("<spring:message code="message.select"/>！");
            return;
        }
        MXF.confirm('<spring:message code="message.confirm"/>？', function () {
            $.get('/order-verify/sendShip?orderId=' + row.id, function (res) {
                if (res.success) {
                    $('#orderVerifyGrid').datagrid('reload');
                    MXF.alert(res.info + res.message, res.success);
                }
            });
        }, function () {
            MXF.alert('<spring:message code="message.cancel"/>', true);
        })
    }

    function printOrder() {
        var row = $('#orderVerifyGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("<spring:message code="message.select"/>！");
            return;
        }
        /*MXF.ajaxing(true);
        $.get('/order-verify/check-print?id=' + row.id, function (res) {
            MXF.ajaxing(false);
            if (res.success) {
                window.open('/order-verify/print?id=' + row.id);
                /!* MXF.openDialog('#editOrderVerifyWindow', '打印出库单', '/order-verify/print?id=' + row.id, function () {
                }, 606, 2000); *!/
            } else {
                MXF.alert(res.message, false);
            }
        })*/
        window.open('/order-verify/print?id=' + row.id);
    }
</script>