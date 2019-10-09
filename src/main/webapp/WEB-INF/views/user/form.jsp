<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form method="post" style="width: 600px; margin: 30px auto;" action="/user/store">
    <input type="hidden" name="id" value="${sysUser.id}"/>
    <div class="input-div">
        <label class="label-top"><spring:message code="user.name"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="username" value="${sysUser.username}"
               data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="user.email"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="email" value="${sysUser.email}">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="user.phone"/></label>
        <input id="brandCombo" class="easyui-textbox theme-textbox-radius" name="phone" value="${sysUser.phone}">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="user.roles"/></label>
        <select id="rolesCombobox" class="easyui-combobox" name="roleIds"
                data-options="required:true,valueField: 'id',textField: 'name',onLoadSuccess:initRolesValue,
			multiple:true,url:'/role/list'"
                style="width:420px;">
        </select>
    </div>
    <div class="input-div" style="text-align: center; margin-top: 35px">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this)"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>
<script>

    function initRolesValue() {
        console.log('[roles]', ${selectRoles});
        if (Array.isArray(${selectRoles})) {
            $('#rolesCombobox').combobox('setValues', ${selectRoles});
        }
    }

</script>