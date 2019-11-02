<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap" style="height: 759px">
    <div class="tableGroup">
        <table id="productCommentGrid" class="easyui-datagrid" title="订单评价"
               data-options="
				rownumbers: true,
				fit: true,
				method: 'get',
				pagination: false,
				pageSize: 10,
				striped: true,
				singleSelect: false,
				url: '/order-detail/json?orderId=${orderId}'">
            <thead>
            <tr>
                <th data-options="field: 'skuMainPic', width:50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                    图片
                </th>
                <th data-options="field: 'name', width: 120, halign: 'center', align: 'center'">商品名</th>
                <th data-options="field: 'skuProperties', width: 200, halign: 'center', align: 'left'">属性</th>
                <th data-options="field: 'options', width:100, halign: 'center', align: 'center', formatter: optionsFormatter">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script type="text/javascript">
    function skuMainPicFormatter(value) {
        if (value) {
            return '<img style="display: block" height="38" width="38" src="' + value + '"/>';
        }
        return '';
    }
    
    function optionsFormatter(val, row) {
        if (row.isComment) {
            return "<a style='color: green'>评价完成</a>";
        } else {
            return "<a style='color: red' onclick='comment(" + JSON.stringify(row) + ")'>立即评价</a>";
        }
    }
    
    function comment(obj) {
        MXF.openDialog('productCommentFormWindow', '编辑', '/product-comment/edit?orderId=' + obj.orderId + '&productId=' + obj.productId + '&detailId=' + obj.id, function () {}, 600, 350);
    }
</script>
