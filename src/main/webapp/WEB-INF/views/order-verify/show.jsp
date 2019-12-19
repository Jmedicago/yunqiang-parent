<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap" style="height: 706px;">
    <%--<div class="tableGroup">
        <table id="showOrderDetailGrid" class="easyui-datagrid" title="<spring:message code="order.detail.list"/>"
               data-options="
				rownumbers: true,
				fit: true,
				method: 'get',
				pagination: false,
				pageSize: 50,
				striped: true,
				singleSelect: false,
				toolbar: '#showOrderDetailTB',
				onLoadSuccess: loadOrderDetailSuccess,
				url: '/order-detail/json?orderId=${bisOrder.id}'">
            <thead>
            <tr>
                <th data-options="field: 'skuMainPic', width:50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                    <spring:message code="order.detail.image"/>
                </th>
                <th data-options="field: 'name', width: 120, halign: 'center', align: 'center'"><spring:message code="order.detail.ptname"/></th>
                <th data-options="field: 'skuProperties', width: 200, halign: 'center', align: 'left'"><spring:message code="order.detail.property"/></th>
                <th data-options="field: 'amount', width:100, halign: 'center', align: 'center'"><spring:message code="order.detail.amount"/></th>
            </tr>
            </thead>
        </table>
        <div id="showOrderDetailTB">
            <div style="height: 30px;">
            </div>
            <div style="position: absolute; top: 0; left: 0; width: 200px; height: 40px;">
                <div style="height: 40px; line-height: 40px; font-size: 14px; text-align: left; padding: 0 20px;">
                    <span style="font-weight: 600;"><spring:message code="order.detail.volume.total"/>：</span>
                    <span id="volumeTotal"></span>
                </div>
            </div>
        </div>
    </div>--%>
    <div class="tableGroup">
        <table id="orderVerifyDetailGrid" class="easyui-datagrid"
               data-options="rownumbers: true,
				            fit: true,
				            method: 'get',
				            pagination: false,
				            pageSize: 10,
				            striped: true,
				            singleSelect: false,
				            toolbar: '#orderVerifyDetailTB',
				            onLoadSuccess: loadOrderVerifyDetailSuccess,
				            url: '/order-detail/json?orderId=${bisOrder.id}'">
            <thead>
            <tr>
                <th data-options="field: 'skuMainPic', width:50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                    <spring:message code="order.detail.image"/>
                </th>
                <th data-options="field: 'name', width: 120, halign: 'center', align: 'center'"><spring:message
                        code="order.detail.ptname"/></th>
                <th data-options="field: 'sku.skuCode', width:100, halign: 'center', align: 'center',formatter: function(val, row){ return row.sku.skuCode}">
                    货品编号
                </th>
                <th data-options="field: 'productType', width:150, halign: 'center', align: 'center'">
                    类别名称
                </th>
                <th data-options="field: 'sku.pack', width:50, halign: 'center', align: 'center', formatter: function(val, row){ return row.sku.pack}">
                    包装形态
                </th>
                <th data-options="field: 'skuProperties', width: 200, halign: 'center', align: 'left'">
                    <spring:message code="order.detail.property"/></th>
                <th data-options="field: 'sku.costPrice', width:80, halign: 'center', align: 'center', formatter: function(val, row){ return MXF.priceFormatter(row.sku.costPrice)}">
                    成本价
                </th>
                <th data-options="field: 'sku.marketPrice', width:80, halign: 'center', align: 'center', formatter: function(val, row){ return MXF.priceFormatter(row.sku.marketPrice)}">
                    批发价
                </th>
                <th data-options="field: 'availableStock', width:100, halign: 'center', align: 'center'">
                    库存数量
                </th>
                <th data-options="field: 'amount', width:100, halign: 'center', align: 'center'">
                    需求数量
                </th>
                <th data-options="field: 'realAmount', width:100, halign: 'center', align: 'center'">
                    实际数量
                </th>
                <th data-options="field: 'inputUser', width:100, halign: 'center', align: 'center'">
                    操作人
                </th>
                <%--<th data-options="field: 'option', width:100, halign: 'center', align: 'center',formatter:optionFormatter">
                    操作
                </th>--%>
            </tr>
            </thead>
        </table>
        <div id="orderVerifyDetailTB">
            <div style="height: 30px;">
                <span style="font-weight: 600;"><spring:message code="order.detail.volume.total"/>：</span>
                <span id="orderVerifyVolumeTotal"></span>

                <span style="font-weight: 600;">上货总金额：</span>
                <span id="orderVerifyTotalMoney"></span>

                <span style="font-weight: 600;">总件数：</span>
                <span id="orderVerifyDigest"></span>
            </div>
            <%--<div style="position: absolute; top: 0; left: 0; width: 200px; height: 40px;">
                <div style="height: 40px; line-height: 40px; font-size: 14px; text-align: left; padding: 0 20px;">
                    <span style="font-weight: 600;"><spring:message code="order.detail.volume.total"/>：</span>
                    <span id="orderVerifyVolumeTotal"></span>
                </div>
                <div style="height: 40px; line-height: 40px; font-size: 14px; text-align: left; padding: 0 20px;">
                    <span style="font-weight: 600;">上货总金额：</span>
                    <span id="orderVerifyTotalMoney"></span>
                </div>
                <div style="height: 40px; line-height: 40px; font-size: 14px; text-align: left; padding: 0 20px;">
                    <span style="font-weight: 600;">总件数：</span>
                    <span id="orderVerifyDigest"></span>
                </div>
            </div>--%>
        </div>
    </div>
</div>

<script type="text/javascript">
    /*function skuMainPicFormatter(value) {
        if (value) {
            return '<img onclick="MXF.showImageDialog(this.src)" style="display: block" height="38" width="38" src="' + value + '"/>';
        }
        return '';
    }

    function loadOrderDetailSuccess(data) {
        $('#volumeTotal').text(data.footer.volumeTotal.toFixed(2));
    }
*/
    $(function () {
        MXF.getTabContentHeight();
    });

    function skuMainPicFormatter(value) {
        if (value) {
            return '<img onclick="MXF.showImageDialog(this.src)" style="display: block" height="38" width="38" src="' + value + '"/>';
        }
        return '';
    }

    function loadOrderVerifyDetailSuccess(data) {
        $('#orderVerifyVolumeTotal').text(data.footer.volumeTotal.toFixed(2));
        $('#orderVerifyTotalMoney').text(MXF.priceFormatter(data.footer.totalMoney));
        $('#orderVerifyDigest').text(data.footer.digest);
    }
</script>
