<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="tradeStockGrid" class="easyui-datagrid" title="<spring:message code="mu.trade.stock"/>"
               data-options="rownumbers:true,fit:true,method:'get',
				pagination:true,pageSize:10,striped:true,singleSelect:true,
				toolbar:'#tradeStockTB',url:'/trade-stock/json'">
            <thead>
            <tr>
                <th data-options="field:'beforeResource',width:200,halign:'center',align:'center',formatter:beforeTradeOrderFormatter">
                    货柜清单名称
                </th>
                <th data-options="field:'confirmTime',width:150,halign:'center',align:'center',formatter:MXF.dateTimeFormatter">
                    预到货时间
                </th>
                <th data-options="field:'updateTime',width:150,halign:'center',align:'center',formatter:MXF.dateTimeFormatter">
                    到货时间
                </th>
                <%--<th data-options="field:'createTime',width:150,halign:'center',align:'center',formatter:MXF.dateTimeFormatter">
                    <spring:message code="trade.stock.qgtime"/> <br>（<spring:message code="trade.stock.uplaodqgtime"/>）
                </th>--%>
                <%--<th data-options="field:'beforeResource',width:200,halign:'center',align:'center',formatter:beforeTradeOrderFormatter">
                    <spring:message code="trade.stock.gwqgtime"/>
                </th>--%>
                <%--<th data-options="field:'afterResource',width:200,halign:'center',align:'center',formatter:afterTradeOrderFormatter">
                    <spring:message code="trade.stock.gwcgfh"/>
                </th>--%>
                <%--<th data-options="field:'confirmTime',width:150,halign:'center',align:'center',formatter:MXF.dateTimeFormatter">
                    <spring:message code="trade.stock.qrtime"/> <br>（<spring:message code="trade.stock.qrcgtime"/>）
                </th>--%>
                <th data-options="field:'status',width:80,halign:'center',align:'center',formatter:treadeStateFormatter">
                    <spring:message code="trade.stock.qrdh"/>
                </th>
            </tr>
            </thead>
        </table>
        <div id="tradeStockTB">
            <div>
                <shiro:hasPermission name="stock:pmcer">
                    <a href="#" data-cmd="del" msg="确认删除？" remote="false" class="easyui-linkbutton"
                       iconCls="icon-remove" plain="true">
                        删除
                    </a>
                    <a href="#" data-cmd="prTrade" title="上传货柜清单" remote="false"
                       class="easyui-linkbutton"
                       iconCls="icon-add" plain="true">
                        上传货柜清单
                    </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="stock:purchaser">
                    <a href="#" data-cmd="finishTrade" mustsel remote="false" data-options="disabled:true"
                       class="easyui-linkbutton"
                       iconCls="icon-ok" plain="true">
                        到货入库
                    </a>
                </shiro:hasPermission>
                <%--<shiro:hasPermission name="stock:pmcer">
                    <a href="#" data-cmd="prTrade" title="<spring:message code="trade.stock.qg"/>" remote="false"
                       class="easyui-linkbutton"
                       iconCls="icon-add" plain="true">
                        <spring:message code="trade.stock.qg"/>
                    </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="stock:purchaser">
                    <a href="#" data-cmd="poTrade" title="<spring:message code="trade.stock.cg"/>" mustsel
                       remote="false" data-options="disabled:true"
                       class="easyui-linkbutton"
                       iconCls="icon-edit" plain="true">
                        <spring:message code="trade.stock.cg"/>
                    </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="stock:pmcer">
                    <a href="#" data-cmd="finishTrade" mustsel remote="false" data-options="disabled:true"
                       class="easyui-linkbutton"
                       iconCls="icon-ok" plain="true">
                        <spring:message code="trade.stock.finished"/>
                    </a>
                </shiro:hasPermission>--%>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        MXF.getTabContentHeight();
    });

    function treadeStateFormatter(v) {
        if (v == 0) return '<red>待入库</red>';
        if (v == 1) return '<orange><spring:message code="trade.stock.send"/></orange>';
        if (v == 2) return '<blue>已入库</blue>';
    }

    function beforeTradeOrderFormatter(v, row) {
        if (row.beforeResource) {
            var a = "<a style='text-decoration: underline; color: #0c80d7;' href='" + row.beforeResource + "'>" + row.fileName + "</a>";
            return a;
        }
        return '';
    }

    function afterTradeOrderFormatter(v, row) {
        if (row.afterResource) {
            var name = '(<spring:message code="trade.stock.cgorder"/>)';
            name += row.fileName;
            var a = "<a style='text-decoration: underline; color: #0c80d7;' href='" + row.afterResource + "'>" + name + "</a>";
            return a;
        } else {
            return '';
        }
    }

    <%--function prTrade() {
        MXF.openDialog('plTradeWindow', '<spring:message code="trade.stock.wmqgorder"/>', 'trade-stock/pr-trade', function () {
        }, 600, 250);
    }--%>

    function prTrade() {
        MXF.openDialog('plTradeWindow', '上传货柜清单', 'trade-stock/pr-trade', function () {
        }, 600, 250);
    }

    function poTrade() {
        var row = $('#tradeStockGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error('<spring:message code="message.select"/>');
            return;
        }
        MXF.openDialog('plTradeWindow', '<spring:message code="trade.stock.wmcgorder"/>', 'trade-stock/po-trade?id=' + row.id, function () {
        }, 600, 200);
    }

    function finishTrade() {
        var row = $('#tradeStockGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error('<spring:message code="message.select"/>');
            return;
        }
        MXF.confirm('<spring:message code="trade.stock.finished"/>？', function () {
            MXF.ajaxing(true);
            $.get('trade-stock/finish-trade?id=' + row.id, function (res) {
                if (res.success) {
                    MXF.ajaxing(false);
                    MXF.alert('<spring:message code="message.goods.in.success"/>' + res.info, true);
                    $('#tradeStockGrid').datagrid('reload');
                } else {
                    MXF.ajaxing(false);
                    MXF.alert('<spring:message code="message.goods.in.fail"/>！' + res.info, false);
                }
            });
        })
    }
</script>

