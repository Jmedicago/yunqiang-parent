<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="orderGrid" class="easyui-datagrid" title="<spring:message code="mu.order.pt"/>" data-options="
                   rownumbers: true,
                   fit: true,
                   method: 'post',
				   pagination: true,
				   pageSize: 10,
				   striped: true,
				   singleSelect: true,
				   toolbar: '#orderTB',
				   url:'/order/json'">
            <thead>
            <tr>
                <th data-options="field: 'id', checkbox: true"></th>
                <th data-options="field: 'orderSn', width: 80, halign: 'center', align: 'center'"><spring:message code="order.sn"/></th>
                <th data-options="field: 'stockId', width: 80, halign: 'center', align: 'center', formatter: stockFormatter"><spring:message code="order.store"/></th>
                <th data-options="field: 'status', width: 80, halign: 'center', align: 'center', formatter: orderStateFormatter"><spring:message code="order.state"/></th>
                <th data-options="field: 'totalMoney', width: 100, halign: 'center', align: 'center', formatter: MXF.priceFormatter"><spring:message code="order.price.total"/></th>
                <th data-options="field: 'confirmTime', width: 130, halign: 'center', align: 'center', formatter: MXF.dateTimeFormatter"><spring:message code="order.submit.time"/></th>
                <th data-options="field: 'shipTime', width: 130, halign: 'center', align: 'center', formatter: MXF.dateTimeFormatter"><spring:message code="order.ship.time"/></th>
                <th data-options="field: 'finishedTime', width: 130, halign: 'center', align: 'center', formatter: MXF.dateTimeFormatter"><spring:message code="order.confirm.time"/></th>
                <th data-options="field: 'commentStatus', width: 80, halign: 'center', align: 'center', formatter: commentStateFormatter"><spring:message code="order.state"/></th>
                <th data-options="field: 'digest', width:200,formatter:MXF.cellTooltipFormatter"><spring:message code="order.detail"/></th>
                <th data-options="field: 'remark', width:200,formatter:MXF.cellTooltipFormatter">备注</th>
                <th data-options="field: 'option', width:200,formatter:optionFormatter">操作</th>
            </tr>
            </thead>
        </table>
        <div id="orderTB">
            <div>
                <a href="#" data-cmd="del" msg="确认删除？" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-remove" plain="true">
                    <spring:message code="common.delete"/>
                </a>
                <a href="#" data-cmd="editEditOrder" title="<spring:message code="common.edit"/>" height="747" width="590" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-edit" plain="true">
                    <spring:message code="common.edit"/>
                </a>
                <span class="buttonSplit">&nbsp;</span>
                <a href="#" data-cmd="confirmShip" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-ok" plain="true">
                    <spring:message code="order.btn.recive"/>
                </a>
                <a href="#" data-cmd="productComment" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   plain="true">
                    <i class="iconfont" style="font-size: 14px;">&#xe69b;</i>
                    <spring:message code="order.btn.commit"/>
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

    function orderStateFormatter(v) {
        if (v == 0) return '<red><spring:message code="order.state.dsp"/></red>';
        if (v == 1) return '<blue><spring:message code="order.state.dsh"/></blue>'; <%--<spring:message code="order.state.dfh"/>--%>
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
    
    function optionFormatter(val, row) {
        var a = "<a href='#' onClick='showOrderDetail("+ row.orderSn + "," + row.id +")'><span style='margin-left: 5px;'>查看明细</span></a>";
        return a;
    }
    
    function confirmShip() {
        var row = $('#orderGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("<spring:message code="message.select"/>！");
            return;
        }

        MXF.confirm("确认收货？", function () {
            MXF.ajaxing(true);
            $.get('/order/confirm-finish?orderId=' + row.id, function (res) {
                MXF.ajaxing(false);
                MXF.alert(res.message + ' ' + res.info, res.success);
                $('#orderGrid').datagrid('reload');
            })
        }, function () {

        });

    }
    
    function commentStateFormatter(val) {
        if (val == 0) { return '<red><spring:message code="commit.waitcommit"/></red>'}
        if (val == 1) { return '<green><spring:message code="commit.finishcommit"/></green>'}
    }
    
    function productComment() {
        var row = $('#orderGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("<spring:message code="message.select"/>！");
            return;
        }
        MXF.openDialog('#productCommentWindow', '<spring:message code="commit.dialog.order.commit"/>', '/product-comment/index?orderId=' + row.id, function () {}, 590, 800);
    }
    
    function showOrderDetail(orderSn,id) {
        /*MXF.openDialog('#showOrderDetailWindow', '查看明细', '/order/show?id=' + id, function () {
            
        },590, 800);*/

        MXF.openDialog('#showOrderDetailWindow', '订单号：' + orderSn, '/order/show?id=' + id, function () {

        },590, 800, true);
    }

    function editEditOrder() {
        var row = $('#orderGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("<spring:message code="message.select"/>！");
            return;
        }
        if (row.status == 0) {
            MXF.openDialog('#editOrderVerifyWindow', '订单号：' + row.orderSn, '/order-verify/edit?o=2&id=' + row.id, function () {

            }, 800, 747, true);
        } else {
            MXF.error("不能修改订单信息");
        }
        /*MXF.openDialog('#editOrderVerifyWindow', '订单号：' + row.orderSn, '/order/edit?id=' + row.id, function () {

        }, 590, 747);*/

    }

</script>
