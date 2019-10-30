<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="tradeStockGrid" class="easyui-datagrid" title="贸易采购"
               data-options="rownumbers:true,fit:true,method:'get',
				pagination:true,pageSize:10,striped:true,singleSelect:false,
				toolbar:'#tradeStockTB',url:'/trade-stock/json'">
            <thead>
            <tr>
                <th data-options="field:'createTime',width:150,halign:'center',align:'center',formatter:MXF.dateTimeFormatter">请购时间 <br>（上传请购单的时间）</th>
                <th data-options="field:'beforeResource',width:150,halign:'center',align:'center',formatter:beforeTradeOrderFormatter">国外请购</th>
                <th data-options="field:'afterResource',width:150,halign:'center',align:'center',formatter:afterTradeOrderFormatter">国内采购确认发货单</th>
                <th data-options="field:'confirmTime',width:150,halign:'center',align:'center',formatter:MXF.dateTimeFormatter">确认时间 <br>（确认请购单时间）</th>
                <th data-options="field:'status',width:80,halign:'center',align:'center',formatter:treadeStateFormatter">是否到货</th>
            </tr>
            </thead>
        </table>
        <div id="#tradeStockTB">
            <div>
                <a href="#" data-cmd="add" title="请购" remote="false" class="easyui-linkbutton" iconCls="icon-add" plain="true">
                       	请购
                </a>
                <a href="#" data-cmd="edit" title="采购" mustsel remote="false" data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-edit" plain="true">
                    	采购
                </a>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        MXF.getTabContentHeight();
    });

    function treadeStateFormatter(v) {
        if (v == 0) return '<red>否</red>';
        if (v == 1) return '<green>是</green>';
    }
    
    function beforeTradeOrderFormatter(v, row) {
    	var name = row.fileName;
    	name += '(请购单).xlsx'; 
    	var a = "<a href='" + row.beforeResource + "'>"+ name +"</a>";
    	return a;
    }
    
    function afterTradeOrderFormatter(v, row) {
		
	}
</script>

