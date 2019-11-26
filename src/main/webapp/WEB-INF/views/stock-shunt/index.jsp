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
                           pageSize: 10,
                           striped: true,
                           singleSelect: false,
                           toolbar: '#stockShuntTB',
                           url: '/sku/json'">
            <thead>
            <tr>
                <th data-options="field: 'id', checkbox:true"></th>
                <th data-options="field: 'skuName', width:100, halign: 'center', align: 'center'"><spring:message code="st.out.product"/></th>
                <th data-options="field: 'skuMainPic', width: 50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                    <spring:message code="sku.skuMainPic"/></th>
                <th data-options="field: 'skuProperties', width:150, halign: 'center', align: 'left'"><spring:message code="st.out.property"/></th>
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
                <th data-options="field: 'availableStock', width:50, halign: 'center', align: 'center'">
                    <spring:message code="sku.availableStock"/></th>
                <th data-options="field: 'frozenStock', width:50, halign: 'center', align: 'center'">
                    北仓库存</th>
                <th data-options="field: 'container', width:80, halign: 'center', align: 'center'">
                    <spring:message code="sku.container"/></th>
                <th data-options="field: 'remark', width:100, halign: 'center', align: 'center'"><spring:message code="st.out.remark"/></th>
                <th data-options="field: 'option', width:100, halign: 'center', align: 'center', formatter: optionFormatter"><spring:message code="common.option"/></th>
            </tr>
            </thead>
        </table>
        <div id="stockShuntTB">
            <div class="searchForm" style="border: unset; margin: 0; padding: 5px 5px">
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
<script>
    $(function () {
        MXF.getTabContentHeight();
    });

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
        var a = "<a href='#' onclick='shuntStock(" + obj +")' remote='false' class='easyui-linkbutton' plain='true'><i class='iconfont'>&#xe6f6;</i>分流</a>";
        return a;
    }
    
    function shuntStock(obj) {
        MXF.openDialog("shuntStockDialog", "商品分流", "/stock-shunt/edit?id=" + obj.id, function () {

        }, 600, 300);
    }
</script>

