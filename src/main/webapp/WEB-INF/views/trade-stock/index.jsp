<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="tradeStockGrid" class="easyui-datagrid" title="贸易采购"
               data-options="rownumbers:true,fit:true,method:'get',
				pagination:true,pageSize:10,striped:true,singleSelect:true,
				toolbar:'#tradeStockTB',url:'/trade-stock/json'">
            <thead>
            <tr>
                <th data-options="field:'createTime',width:150,halign:'center',align:'center',formatter:MXF.dateTimeFormatter">
                    请购时间 <br>（上传请购单的时间）
                </th>
                <th data-options="field:'beforeResource',width:200,halign:'center',align:'center',formatter:beforeTradeOrderFormatter">
                    国外请购
                </th>
                <th data-options="field:'afterResource',width:200,halign:'center',align:'center',formatter:afterTradeOrderFormatter">
                    国内采购确认发货单
                </th>
                <th data-options="field:'confirmTime',width:150,halign:'center',align:'center',formatter:MXF.dateTimeFormatter">
                    确认时间 <br>（确认请购单时间）
                </th>
                <th data-options="field:'status',width:80,halign:'center',align:'center',formatter:treadeStateFormatter">
                    是否到货
                </th>
            </tr>
            </thead>
        </table>
        <div id="tradeStockTB">
            <div>
                <shiro:hasPermission name="stock:pmcer">
                    <a href="#" data-cmd="prTrade" title="请购" remote="false" class="easyui-linkbutton"
                       iconCls="icon-add" plain="true">
                        请购
                    </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="stock:purchaser">
                    <a href="#" data-cmd="poTrade" title="采购" mustsel remote="false" data-options="disabled:true"
                       class="easyui-linkbutton"
                       iconCls="icon-edit" plain="true">
                        采购
                    </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="stock:pmcer">
                    <a href="#" data-cmd="finishTrade" mustsel remote="false" data-options="disabled:true"
                       class="easyui-linkbutton"
                       iconCls="icon-ok" plain="true">
                        完成
                    </a>
                </shiro:hasPermission>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        MXF.getTabContentHeight();
    });

    function treadeStateFormatter(v) {
        if (v == 0) return '<red>待审核</red>';
        if (v == 1) return '<orange>待发货</orange>';
        if (v == 2) return '<blue>已到货</blue>';
    }

    function beforeTradeOrderFormatter(v, row) {
        if (row.beforeResource) {
            var name = '(请购单)';
            name += row.fileName;
            var a = "<a style='text-decoration: underline; color: #0c80d7;' href='" + row.beforeResource + "'>" + name + "</a>";
            return a;
        }
        return '';
    }

    function afterTradeOrderFormatter(v, row) {
        if (row.afterResource) {
            var name = '(采购单)';
            name += row.fileName;
            var a = "<a style='text-decoration: underline; color: #0c80d7;' href='" + row.afterResource + "'>" + name + "</a>";
            return a;
        } else {
            return '';
        }
    }

    function prTrade() {
        MXF.openDialog('plTradeWindow', '外贸请购单', 'trade-stock/pr-trade', function () {
        }, 600, 250);
    }

    function poTrade() {
        var row = $('#tradeStockGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error('请至少选中一条数据');
            return;
        }
        MXF.openDialog('plTradeWindow', '外贸采购单', 'trade-stock/po-trade?id=' + row.id, function () {
        }, 600, 200);
    }

    function finishTrade() {
        var row = $('#tradeStockGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error('请至少选中一条数据');
            return;
        }
        MXF.confirm('是否到货？', function () {
            MXF.ajaxing(true);
            $.get('trade-stock/finish-trade?id=' + row.id, function (res) {
                if (res.success) {
                    MXF.ajaxing(false);
                    MXF.alert('商品导入成功！', true);
                    $('#tradeStockGrid').datagrid('reload');
                } else {
                    MXF.ajaxing(false);
                    MXF.alert('商品导入失败！', false);
                }
            });
        })
    }
</script>

