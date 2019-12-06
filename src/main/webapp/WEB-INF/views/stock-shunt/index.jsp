<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="stockShuntGrid" title="<spring:message code="mu.st.shunt"/>" class="easyui-datagrid" data-options="
                           rownumbers: true,
                           fit: true,
                           method: 'post',
                           pagination: true,
                           pageSize: 50,
                           striped: true,
                           singleSelect: false,
                           toolbar: '#stockShuntTB',
                           url: '/sku/es'">
            <thead>
            <tr>
                <th data-options="field: 'id', checkbox:true"></th>
                <th data-options="field:'path',width:150,halign:'center',align:'center',sortable:true"><spring:message
                        code="product.type.name"/></th>
                <%--<th data-options="field: 'skuName', width:100, halign: 'center', align: 'center'"><spring:message code="st.out.product"/></th>--%>
                <th data-options="field: 'skuMainPic', width: 50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                    <spring:message code="sku.skuMainPic"/></th>
                <th data-options="field:'name',width:150,halign:'center',align:'center',sortable:true"><spring:message
                        code="product.name"/></th>
                <th data-options="field:'code',width:150,halign:'center',align:'center',sortable:true"><spring:message
                        code="product.code"/></th>
                <th data-options="field: 'skuProperties', width:150, halign: 'center', align: 'left',formatter:MXF.cellTooltipFormatter">
                    <spring:message code="st.out.property"/></th>
                <th data-options="field: 'pack', width:100, halign: 'center', align: 'center'"><spring:message
                        code="sku.pack"/></th>
                <th data-options="field: 'volume', width:80, halign: 'center', align: 'center'"><spring:message
                        code="sku.volume"/>（m<sup>3</sup>）
                </th>
                <th data-options="field: 'costPrice', width:80, halign: 'center', align: 'center', formatter: MXF.priceFormatter">
                    <spring:message code="sku.costPrice"/></th>
                <th data-options="field: 'marketPrice', width:80, halign: 'center', align: 'center', formatter: MXF.priceFormatter">
                    <spring:message code="sku.marketPrice"/></th>
                <th data-options="field: 'profit', width:50, halign: 'center', align: 'center'"><spring:message
                        code="sku.profit"/>（%）
                </th>
                <th data-options="field: 'supplier', width:80, halign: 'center', align: 'center'">
                    <spring:message code="sku.supplier"/></th>
                <%--<th data-options="field: 'availableStock', width:50, halign: 'center', align: 'center'">
                    <spring:message code="sku.availableStock"/></th>--%>
                <%--<th data-options="field: 'frozenStock', width:50, halign: 'center', align: 'center'">
                    北仓库存</th>--%>
                <th data-options="field: 'defaultStock', width:50, halign: 'center', align: 'center', formatter: function (val, row) {
                    var amount = 0;
                    $.each(row.stockShunt, function (index, item) {
                        if (item.stockId == 1000) {
                            amount = item.amount;
                        }
                    });
                    return amount;
                }">
                    总仓
                </th>
                <th data-options="field: 'northStock', width:150, halign: 'center', align: 'center', formatter: northStockShuntFormatter">
                    北部分仓
                </th>
                <th data-options="field: 'southStock', width:150, halign: 'center', align: 'center', formatter: southStockShuntFormatter">
                    南部分仓
                </th>
                <th data-options="field: 'container', width:80, halign: 'center', align: 'center',formatter:MXF.cellTooltipFormatter">
                    <spring:message code="sku.container"/></th>
                <th data-options="field: 'remark', width:100, halign: 'center', align: 'center',formatter:MXF.cellTooltipFormatter">
                    <spring:message code="st.out.remark"/></th>
                <%-- <th data-options="field: 'option', width:100, halign: 'center', align: 'center', formatter: optionFormatter">
                     <spring:message code="common.option"/></th>--%>
            </tr>
            </thead>
        </table>
        <div id="stockShuntTB">
            <div>
                <a href="#" data-cmd="viewStockShuntLog" remote="false" class="easyui-linkbutton" plain="true">
                    查看日志
                </a>
            </div>
            <div class="searchForm">
                <form>
                    <spring:message code="product.info.name"/>：
                    <input class="easyui-textbox theme-textbox-radius" name="name" style="width:200px;">&nbsp;
                    <spring:message code="product.info.type"/>：
                    <input class="easyui-combotree theme-textbox-radius" name="productType"
                           data-options="url:'/product-type/json',method:'get'" style="width:200px;">
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
<style type="text/css">
    .shunt-amount {
        display: inline-block;
        padding: 5px 20px;
        border: 1px solid #999;
    }

    .shunt-btn {
        display: inline-block;
        padding: 5px 5px;
        border-top: 1px solid #999;
        border-bottom: 1px solid #999;
        border-right: 1px solid #999;
        color: blue !important;
    }
</style>
<script>
    $(function () {
        MXF.getTabContentHeight();
    });

    function northStockShuntFormatter(val, row) {
        var data = {
            amount: 0,
            skuId: row.id,
            stockId: 1062
        };
        $.each(row.stockShunt, function (index, item) {
            if (item.stockId == data.stockId) {
                data.amount = item.amount;
            }
        });
        var text = '<span class="shunt-amount">' + data.amount + '</span>';
        var btn = '<a href="#" onclick="shuntStock(' + data.skuId + ',' + data.stockId + ')" class="shunt-btn">分流</a>';
        return text + btn;
    }

    function southStockShuntFormatter(val, row) {
        var data = {
            amount: 0,
            skuId: row.id,
            stockId: 1050
        };
        $.each(row.stockShunt, function (index, item) {
            if (item.stockId == data.stockId) {
                data.amount = item.amount;
            }
        });
        var text = '<span class="shunt-amount">' + data.amount + '</span>';
        var btn = '<a href="#" onclick="shuntStock(' + data.skuId + ',' + data.stockId + ')"  class="shunt-btn">分流</a>';
        return text + btn;
    }


    function productStateFormatter(value) {
        if (0 == value) {
            return '<red><spring:message code="product.state.scarce"/></red>';
        } else if (1 == value) {
            return '<green><spring:message code="product.state.normal"/></green>';
        }
        return '';
    }

    function skuMainPicFormatter(value) {
        if (value) {
            return '<img style="display: block" height="38" width="38" src="' + value + '"/>';
        }
        return '';
    }

    function optionFormatter(value, row) {
        var obj = JSON.stringify(row);
        var a = "<a href='#' onclick='shuntStock(" + obj + ")' remote='false' class='easyui-linkbutton' plain='true'><i class='iconfont'>&#xe6f6;</i>分流</a>";
        return a;
    }

    function shuntStock(skuId, stockId) {
        MXF.openDialog("shuntStockDialog", "商品分流", "/stock-shunt/edit?skuId=" + skuId + "&stockId=" + stockId, function () {

        }, 600, 300);
    }
    
    function viewStockShuntLog() {
        var tabs = $("#tabs");
        var uri = '/stock-shunt-log';
        var title = '商品分流日志';
        var tab = tabs.tabs("getTab", title);
        console.log('open' + title, tab);
        if (tab) {
            tabs.tabs("select", title);
        } else {
            tabs.tabs('add', {
                title: title,
                href: uri,
                closable: true,
                bodyCls: "content"
            });
        }
    }
</script>

