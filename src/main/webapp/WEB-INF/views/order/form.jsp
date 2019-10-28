<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="tab-wrap">
	<div class="tableGroup">
		<table id="orderDetailGrid" class="easyui-datagrid" title="订单明细"
			data-options="
				rownumbers: true,
				fit: true,
				method: 'get',
				pagination: true,
				pageSize: 10,
				striped: true,
				singleSelect: false,
				toolbar: '#orderDetailTB',
				url: '/order-detail/json?sn=${bisOrder.id}'">
			<thead>
				<tr>
					<th data-options="field: 'id',checkbox:true"></th>
					<th
						data-options="field: 'skuMainPic', width:50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
						图片</th>
					<th
						data-options="field: 'name', width:100, halign: 'center', align: 'center'">商品名</th>
					<th
						data-options="field: 'skuProperties', width:150, halign: 'center', align: 'left'">属性</th>
					<th
						data-options="field: 'amount', width:100, halign: 'center', align: 'center', formatter: cartAmountFormatter">
						数量</th>
				</tr>
			</thead>
		</table>
		<div id="orderDetailTB">
			<div>
				<a href="#" data-cmd="add"
					title="<spring:message code="common.add"/>" remote="false"
					class="easyui-linkbutton" iconCls="icon-add" plain="true"> <spring:message
						code="common.add" />
				</a> <a href="#" data-cmd="edit"
					title="<spring:message code="common.edit"/>" mustsel remote="false"
					data-options="disabled:true" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true"> <spring:message
						code="common.edit" />
				</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		MXF.getTabContentHeight();
	});
</script>