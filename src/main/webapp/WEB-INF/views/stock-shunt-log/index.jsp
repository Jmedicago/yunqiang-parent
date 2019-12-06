<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="stockShuntLogGrid" class="easyui-datagrid" title="商品分流日志"
               data-options="rownumbers:true,fit:true,method:'get',
				pagination:true,pageSize:10,striped:true,singleSelect:false,
				toolbar:'#stockShuntLogTB',url:'/stock-shunt-log/json'">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'date',width:180,formatter:MXF.dateTimeFormatter">日期</th>
                <th data-options="field:'productTypeFormatter',width:100">类别名称</th>
                <th data-options="field:'sku.skuMainPic',width:80,formatter:stockShuntImageFormatter">图片</th>
                <th data-options="field:'sku.skuName',width:100,formatter:function(val, row){return MXF.cellTooltipFormatter(row.sku.skuName)}">品名</th>
                <th data-options="field:'sku.skuCode',width:150,formatter:function(val, row){return row.sku.skuCode}">
                    货品编码
                </th>
                <th data-options="field:'sku.skuProperties',width:180, formatter:function(val,row){return MXF.cellTooltipFormatter(row.sku.skuPropertiesFormatter)}">属性名</th>
                <th data-options="field:'sku.pack',width:100, formatter:function(val,row){return row.sku.pack}">包装形态</th>
                <th data-options="field:'sku.volume',width:100, formatter:function(val,row){return row.sku.volume}">体积</th>
                <th data-options="field:'sku.costPrice',width:100, formatter:function(val,row){return MXF.priceFormatter(row.sku.costPrice)}">成本价</th>
                <th data-options="field:'sku.marketPrice',width:100, formatter:function(val,row){return MXF.priceFormatter(row.sku.marketPrice)}">批发价</th>
                <th data-options="field:'sku.profit',width:100, formatter:function(val,row){return row.sku.profit}">利润</th>
                <th data-options="field:'sku.supplier',width:100, formatter:function(val,row){return MXF.cellTooltipFormatter(row.sku.supplier)}">供应商</th>
                <th data-options="field:'sku.container',width:100, formatter:function(val,row){return MXF.cellTooltipFormatter(row.sku.container)}">货柜编号</th>
                <th data-options="field:'stockFormatter',width:100">分仓</th>
                <th data-options="field:'amount',width:50">数量</th>
                <th data-options="field:'state',width:50,formatter:stockShuntStateFormatter"><spring:message
                        code="common.status"/></th>
            </tr>
            </thead>
        </table>
        <div id="stockShuntLogTB">
            <div class="searchForm">
                <form>
                    分仓:
                    <select class="easyui-combobox" name="stockId" style="width:100px;">
                        <option value="1062">北部分仓</option>
                        <option value="1050">南部分仓</option>
                    </select>
                    <%--商品编码：
                    <input class="easyui-textbox theme-textbox-radius" name="code" style="width:100px;">--%>
                    日期：
                    <input class="easyui-datetimebox" name="date"
                           data-options="required:true,showSeconds:false" style="width:100px">
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

    function stockShuntImageFormatter(val, row) {
        return '<img onclick="MXF.showImageDialog(this.src)" style="display: block" height="38" width="38" src="' + row.sku.skuMainPic + '"/>';
    }

    function stockShuntStateFormatter(v) {
        if (v == 1) return '<green>已出库</green>';
        if (v == 0) return '<orange>待定</orange>';
    }
</script>

