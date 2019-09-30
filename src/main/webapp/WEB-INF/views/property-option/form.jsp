<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<form style="width: 600px; margin: 30px auto;" action="/property-option/store" method="post">
    <input type="hidden" name="id"/>
    <input type="hidden" name="property"/>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.ppv.name"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="optionValue" data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.ppv.icon"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="optionIcon">
    </div>
    <div class="input-div" style="margin: 35px 0; text-align: center">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this)"><spring:message code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
</form>