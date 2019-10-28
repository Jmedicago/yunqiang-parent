<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form method="post" style="width: 600px; margin: 30px auto;" action="/expend-item/store">
    <input type="hidden" name="id" value="${finExpendItem.id}"/>
    <div class="input-div">
        <label class="label-top">类别</label>
        <input class="easyui-textbox theme-textbox-radius" name="category" value="${finExpendItem.category}"
               data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top">项目</label>
        <input class="easyui-textbox theme-textbox-radius" name="article" value="${finExpendItem.article}">
    </div>
    <div class="input-div">
        <label class="label-top">备注</label>
        <input class="easyui-textbox theme-textbox-radius" data-options="multiline:true" style="height:60px;"
               name="remark" value="${finExpendItem.remark}">
    </div>
    <div class="input-div" style="text-align: center; margin: 35px 0">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this)"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
</form>
