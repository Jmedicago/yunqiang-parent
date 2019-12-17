<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form method="post" style="width: 600px; margin: 30px auto;" action="/inventory/store">
    <input type="hidden" name="skuId" value="${stockShunt.skuId}"/>
    <input type="hidden" name="stockId" value="${stockShunt.stockId}"/>
    <input type="hidden" name="opt" value="${opt}"/>
    <div class="input-div">
        <label class="label-top">数量</label>
        <input class="easyui-textbox theme-textbox-radius" name="amount" data-options="required:true"
               style="width:450px;">
    </div>
    <div class="input-div" style="text-align: center; margin: 35px 0">
        <a id="inventoryFormBtn" class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this, function() {
          $('#shuntStockDialog').window('close');
          $('#inventoryGrid').datagrid('reload');
        })"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>

<script type="text/javascript">
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            $('#inventoryFormBtn').triggerHandler('click');
        }
    });
</script>
