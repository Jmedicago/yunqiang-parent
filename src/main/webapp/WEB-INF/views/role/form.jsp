<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form method="post" action="/role/store">
    <input type="hidden" name="id" value="${sysRole.id}"/>
    <div class="input-div">
        <label class="label-top"><spring:message code="role.name"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="name" value="${sysRole.name}"
               data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="role.code"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="role" value="${sysRole.role}">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="role.desc"/></label>
        <input class="easyui-textbox theme-textbox-radius" data-options="multiline:true" style="height:60px;"
               name="description" value="${sysRole.description}">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="role.permissions"/></label>
        <select id="permissionCombotree" class="easyui-combotree" style="width:420px;" name="permissionIds"
                data-options="url:'/permission/tree',required:true,multiple:true,onLoadSuccess:initPermissionsValue"></select>
    </div>
    <div class="input-div" style="text-align: center; margin-top: 35px">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this)"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>
<script type="text/javascript">

    function initPermissionsValue() {
        $('#permissionCombotree').combotree('setValues', ${selectPermissions});
    }

</script>