<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap" style="height: 759px">
    <div class="tableGroup">
        <table id="productCommentGrid" class="easyui-datagrid" title="<spring:message code="commit.dialog.name"/>"
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
                <th data-options="field: 'name', width: 120, halign: 'center', align: 'center'"><spring:message code="product.name"/></th>
                <th data-options="field: 'skuProperties', width: 200, halign: 'center', align: 'left'"><spring:message code="product.prop.name"/></th>
                <th data-options="field: 'options', width:100, halign: 'center', align: 'center', formatter: optionsFormatter"><spring:message code="common.option"/></th>
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
            return "<a style='color: green'><spring:message code="commit.finishcommit"/></a>";
        } else {
            return "<a style='color: red' onclick='comment(" + JSON.stringify(row) + ")'><spring:message code="commit.waitcommit"/></a>";
        }
    }
    
    function comment(obj) {
        MXF.openDialog('productCommentFormWindow', '<spring:message code="common.edit"/>', '/product-comment/edit?orderId=' + obj.orderId + '&productId=' + obj.productId + '&detailId=' + obj.id, function () {}, 600, 350);
    }
</script>
