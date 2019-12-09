<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<form id="prProductionBuyerForm" action="/production-buyer/pr-upload" method="post" enctype="multipart/form-data">
    <%--<div class="input-div">
        <label class="label-top"><spring:message code="trade.stock.type"/></label>
        <select class="easyui-combobox" name="type" style="width: 420px;">
            <option value="0"><spring:message code="trade.stock.type1"/></option>
            <option value="1"><spring:message code="trade.stock.type2"/></option>
        </select>
    </div>--%>
    <div class="input-div">
        <label class="label-top"><spring:message code="trade.stpck.uplaod.upload.cgorder"/></label>
        <input class="easyui-filebox theme-textbox-radius" name="uploadFile" data-options="
            required: true,
            buttonText: '<spring:message code="message.select.file"/>',
            accept: '.xlsx,.xls',
            multiple: false" style="width: 420px">
    </div>
    <div class="input-div" style="margin: 35px 0; text-align: center">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this, submitPrProductionBuyerCallBack)"><spring:message code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
</form>

<script>
    function submitPrProductionBuyerCallBack(form, data) {
        $('#productionBuyerGrid').datagrid('reload');
    }
</script>
