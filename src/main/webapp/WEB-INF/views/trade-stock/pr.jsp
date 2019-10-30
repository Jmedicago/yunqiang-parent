<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<form id="prTradeStockForm" action="/trade-stock/upload-pr-trade" method="post" enctype="multipart/form-data">
    <div class="input-div">
        <label class="label-top">类型</label>
        <select class="easyui-combobox" name="type" style="width: 420px;">
            <option value="0">外贸/百货类</option>
            <option value="1">零件</option>
        </select>
    </div>
    <div class="input-div">
        <label class="label-top">上传请购单</label>
        <input class="easyui-filebox theme-textbox-radius" name="uploadFile" data-options="
            required: true,
            buttonText: '选择文件',
            accept: '.xlsx,.xls',
            multiple: false" style="width: 420px">
    </div>
    <div class="input-div" style="margin: 35px 0; text-align: center">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this, submitCallBack)"><spring:message code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
</form>

<script>
    function submitCallBack(form, data) {
        $('#tradeStockGrid').datagrid('reload');
    }
</script>