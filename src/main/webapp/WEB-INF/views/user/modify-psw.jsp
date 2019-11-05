<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form method="post" style="width: 600px; margin: 30px auto;" action="/user/store-psw">
    <input type="hidden" name="id" value="${sysUser.id}"/>
    <div class="input-div">
        <label class="label-top">新密码</label>
        <input class="easyui-passwordbox theme-textbox-radius" name="newPsw"
               data-options="required:true" style="width:420px" showEye="false">
    </div>
    <div class="input-div">
        <label class="label-top">确认密码</label>
        <input class="easyui-passwordbox theme-textbox-radius" name="onecNewPsw" style="width:420px" showEye="false">
    </div>
    <div class="input-div" style="text-align: center; margin-top: 35px">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this)"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>