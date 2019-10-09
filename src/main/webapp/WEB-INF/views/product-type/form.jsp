<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form id="productTypeForm" style="width: 600px; margin: 0 auto;" method="post" action="/product-type/store">
    <input type="hidden" name="id"/>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.type.name"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="name" value="${bisProductType.name}"
               placeholder="<spring:message code="message.input"/>"
               data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.type.parent"/></label>
        <input id="productTypeTreeCombo" class="easyui-combotree theme-textbox-radius" name="parentId" data-options="url:'/product-type/json', method:'get',
						onLoadSuccess:function(){
							var comboTree = $('#productTypeTreeCombo').combotree('tree');
							$(comboTree).tree('collapseAll');
						}">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.type.sort"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="sort">
    </div>
    <div class="input-div" style="margin: 35px 0; text-align: center">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this, productTypeStoreCallBack)">
            <spring:message code="common.submit"/>
        </a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)">
            <spring:message code="common.reset"/>
        </a>
    </div>
</form>

<script type="text/javascript">
    function productTypeStoreCallBack($form, data) {
        console.log(data);
        if (data.success) {
            //MXF.clearForm($form);
            $('#productTypeTreeCombo').combotree('reload');
            $('#productTypeTree').tree('reload');
        }
    }
</script>
