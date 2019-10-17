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
				   singleSelect: false,
				   toolbar: '#orderTB',
				   url:'/order/json'">
            <thead>
            <tr>
                <th data-options="field: 'id', checkbox: true"></th>
                <th data-options="field: 'orderSn', width: 120">订单号</th>
                <th data-options="field: 'state', width: 60, formatter: orderStateFormatter">状态</th>
                <th data-options="field:'code',width:120"><spring:message code="product.code"/></th>
                <th data-options="field:'name',width:200"><spring:message code="product.name"/></th>
            </tr>
            </thead>
        </table>
        <div id="orderTB">
            <div>
                <a href="#" data-cmd="del" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-remove" plain="true">
                    <spring:message code="common.delete"/>
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

    function orderStateFormatter(v) {
        if (v == 0) return '<yellow>待审批</yellow>';
        if (v == 1) return '<blue>待发货</blue>';
        if (v == 2) return '<orange>待收货</orange>';
        if (v == 3) return '<green>订单完成</green>';
        if (v == 5) return '<red>订单关闭</red>';
    }
</script>
