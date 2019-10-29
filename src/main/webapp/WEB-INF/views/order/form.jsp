<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap" style="height: 706px;">
    <div class="tableGroup">
        <table id="orderDetailGrid" class="easyui-datagrid" title="订单明细"
               data-options="
				rownumbers: true,
				fit: true,
				method: 'get',
				pagination: false,
				pageSize: 10,
				striped: true,
				singleSelect: false,
				toolbar: '#orderDetailTB',
				 onLoadSuccess: loadOrderDetailSuccess,
				url: '/order-detail/json?orderId=${bisOrder.id}'">
            <thead>
            <tr>
                <th data-options="field: 'skuMainPic', width:50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                    图片
                </th>
                <th data-options="field: 'name', width: 120, halign: 'center', align: 'center'">商品名</th>
                <th data-options="field: 'skuProperties', width: 200, halign: 'center', align: 'left'">属性</th>
                <th data-options="field: 'amount', width: 100, halign: 'center', align: 'center'">
                    数量
                </th>
            </tr>
            </thead>
        </table>
        <div id="orderDetailTB">
            <div style="height: 30px;">
            </div>
            <div style="position: absolute; top: 0; left: 0; width: 200px; height: 40px;">
                <div style="height: 40px; line-height: 40px; font-size: 14px; text-align: left; padding: 0 20px;">
                    <span style="font-weight: 600;">总体积：</span>
                    <span id="volumeTotal"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function skuMainPicFormatter(value) {
        if (value) {
            return '<img style="display: block" height="38" width="38" src="' + value + '"/>';
        }
        return '';
    }

    function loadOrderDetailSuccess(data) {
        $('#volumeTotal').text(data.footer.volumeTotal.toFixed(2));
    }
</script>