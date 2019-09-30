<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form id="stockForm" style="width: 600px; margin: 0 auto;" method="post" action="/stock/store">
    <input type="hidden" name="id"/>
    <div class="input-div">
        <label class="label-top"><spring:message code="stock.name"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="name" value="${bisStock.name}"
               placeholder="<spring:message code="message.input"/>"
               data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="stock.parent"/></label>
        <input id="stockTreeCombo" class="easyui-combotree theme-textbox-radius" name="parentId" data-options="url:'/stock/json', method:'get',
						onLoadSuccess:function() {
							var comboTree = $('#stockTreeCombo').combotree('tree');
							$(comboTree).tree('collapseAll');
						}">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="stock.desc"/></label>
        <input class="easyui-textbox theme-textbox-radius" data-options="multiline:true" style="height:60px;"
               name="description" value="${bisStock.description}">
    </div>
    <div class="input-div" style="margin: 35px 0; padding-left: 100px; text-align: center">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this, stockStoreCallBack)">
            <spring:message code="common.submit"/>
        </a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)">
            <spring:message code="common.reset"/>
        </a>
    </div>
</form>

<script type="text/javascript">
    function stockStoreCallBack($form, data) {
        if (data.success) {
            //MXF.clearForm($form);
            $('#stockTree').tree('reload');
            $('#stockTreeCombo').combotree('reload');
        }
    }
</script>