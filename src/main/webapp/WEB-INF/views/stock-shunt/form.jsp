<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form method="post" style="width: 600px; margin: 30px auto;" action="/stock-shunt/shunt">
    <input type="hidden" name="id" value="${bisSku.id}"/>
    <input type="hidden" name="availableStock" value="${bisSku.availableStock}"/>
    <div class="input-div">
        <label class="label-top">北部仓库</label>
        <input class="easyui-textbox theme-textbox-radius" name="frozenStock" value="${bisSku.frozenStock}"
               data-options="required:true">
    </div>
    <div class="input-div" style="text-align: center; margin: 35px 0">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this, function() {
          $('#stockShuntGrid').datagrid('reload');
        })"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>