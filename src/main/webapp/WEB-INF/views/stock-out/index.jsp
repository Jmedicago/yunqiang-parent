<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="stockOutGrid" class="easyui-datagrid" title="商品出库"
               data-options="rownumbers: true, fit: true, method: 'get',
				pagination: true, pageSize: 10,striped: true, singleSelect: false,
				toolbar: '#productTB', url: '/product/json'">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'code',width:120"><spring:message code="product.code"/></th>
                <th data-options="field:'name',width:200"><spring:message code="product.name"/></th>
                <th data-options="field:'state',width:80, formatter:productStateFormatter"><spring:message code="product.state"/></th>
            </tr>
            </thead>
        </table>
    </div>
</div>
