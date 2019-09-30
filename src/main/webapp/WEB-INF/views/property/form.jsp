<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<form method="post" style="width: 600px; margin: 30px auto;" action="/property/store">
    <input type="hidden" name="id" />
    <input type="hidden" name="productType" />
    <div class="input-div">
        <label class="label-top"><spring:message code="product.prop.name"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="name" data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.prop.type"/></label>
        <input name="type" type="radio" checked="checked" value="0"> <spring:message code="common.accord"/>
        <input name="type"  type="radio"  value="1"> <spring:message code="common.sales"/>
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.prop.mode"/></label>
        <select class="easyui-combobox theme-textbox-radius" style="width:420px;" name="inputMode"
                data-options="required:true,valueField: 'k',textField: 'v',url: '/const/propertyInputMode'"
                style="width:200px">
        </select>
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.prop.inputType"/></label>
        <select class="easyui-combobox theme-textbox-radius" style="width:420px;" name="inputType"
                data-options="valueField: 'k',textField: 'v',url: '/const/propertyInputType'"
                style="width:200px">
        </select>
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="common.remark"/></label>
        <input class="easyui-textbox theme-textbox-radius" data-options="multiline:true" name="remark"  style="height:80px">
    </div>
    <div class="input-div" style="margin: 35px 0;text-align: center">
        <a  class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this)"><spring:message code="common.submit"/></a>
        <a  class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
</form>