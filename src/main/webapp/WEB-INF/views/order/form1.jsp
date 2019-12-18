<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap" style="height: 706px;">
    <div class="tableGroup">
        <table id="orderDetailGrid" class="easyui-datagrid" title="<spring:message code="order.detail.list"/>"
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
                    <spring:message code="order.detail.image"/>
                </th>
                <th data-options="field: 'name', width: 120, halign: 'center', align: 'center'"><spring:message
                        code="order.detail.ptname"/></th>
                <th data-options="field: 'skuProperties', width: 200, halign: 'center', align: 'left'"><spring:message
                        code="order.detail.property"/></th>
                <th data-options="field: 'amount', width:100, halign: 'center', align: 'center', formatter: cartAmountFormatter">
                    <spring:message code="order.detail.amount"/></th>
            </tr>
            </thead>
        </table>
        <div id="orderDetailTB">
            <div style="height: 30px;">
            </div>
            <div style="position: absolute; top: 0; left: 0; width: 200px; height: 40px;">
                <div style="height: 40px; line-height: 40px; font-size: 14px; text-align: left; padding: 0 20px;">
                    <span style="font-weight: 600;"><spring:message code="order.detail.volume.total"/>：</span>
                    <span id="volumeTotal"></span>
                </div>
            </div>
        </div>
    </div>

    <div>

        <form role="form" action="null">
            <div class="form-group">
                <label for="title">title</label>
                <input type="text" class="form-control" id="title" placeholder="place input text..">
            </div>

            <div class="form-group">
                <label for="subtitle">subtitle</label>
                <input type="text" class="form-control" id="subtitle" placeholder="place input text..">
            </div>

        </form>

    </div>
</div>

<script type="text/javascript">
    function skuMainPicFormatter(value) {
        if (value) {
            return '<img onclick="MXF.showImageDialog(this.src)" style="display: block" height="38" width="38" src="' + value + '"/>';
        }
        return '';
    }

    function loadOrderDetailSuccess(data) {
        $('#volumeTotal').text(data.footer.volumeTotal.toFixed(2));
    }

    function cartAmountFormatter(value, row, index) {
        row.index = index;
        var obj = JSON.stringify(row);
        var add = "<a class='btn-d default' onclick='addOrderDetailAmount(" + obj + ")'><i style='border: 1px solid #ccc;' class='icon iconfont icon-cart-add'></i></a>";
        var remove = "<a class='btn-d default' onclick='removeOrderDetailAmount(" + obj + ")'><i style='border: 1px solid #ccc;' class='icon iconfont icon-cart-remove'></i></a>";
        var input = "<span style='display: inline-block; width: 30px;'>" + value + "</span>";
        return remove + input + add;
    }

    function addOrderDetailAmount(obj) {
        obj.amount++;
        $.post('/order-detail/addAmount', obj, function (res) {
            if (res.success) {
                // 更新字段
                $("#orderDetailGrid").datagrid("updateRow", {
                    index: obj.index, //行索引
                    row: {
                        amount: obj.amount //行中的某个字段
                    }
                });
                // 更新统计
                orderDetailStatistics(res.data);
            } else {
                MXF.alert(res.message, res.success);
            }
        });
    }

    function removeOrderDetailAmount(obj) {
        obj.amount--;
        $.post('/order-detail/removeAmount', obj, function (res) {
            if (res.success) {
                // 更新字段
                $("#orderDetailGrid").datagrid("updateRow", {
                    index: obj.index, //行索引
                    row: {
                        amount: obj.amount //行中的某个字段
                    }
                });
                // 更新统计
                orderDetailStatistics(res.data);
            } else {
                MXF.alert(res.message, res.success);
            }
        });
    }

    function orderDetailStatistics(data) {
        $('#volumeTotal').text(data.totalVolume);
    }

</script>