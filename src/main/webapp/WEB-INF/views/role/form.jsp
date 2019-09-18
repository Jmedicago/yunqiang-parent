<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<form method="post" action="/role/store">
    <input type="hidden" name="id" value="${sysRole.id}"/>
    <div class="input-div">
        <label class="label-top">角色名称</label>
        <input class="easyui-textbox theme-textbox-radius" name="name" value="${sysRole.name}"
               data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top">角色代码</label>
        <input class="easyui-textbox theme-textbox-radius" name="role" value="${sysRole.role}">
    </div>
    <div class="input-div">
        <label class="label-top">角色描述</label>
        <input class="easyui-textbox theme-textbox-radius" data-options="multiline:true" style="height:60px;"
               name="description" value="${sysRole.description}">
    </div>
    <div class="input-div">
        <label class="label-top">权限列表</label>
        <select id="permissionCombotree" class="easyui-combotree" style="width:420px;" name="permissionIds"
                data-options="url:'/permission/tree',required:true,multiple:true,onLoadSuccess:initPermissionsValue"></select>
    </div>
    <div class="input-div">
        <label class="label-top"></label>
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this)">提交</a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)">清空</a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>
<script>

    function initPermissionsValue() {
        $('#permissionCombotree').combotree('setValues', ${selectPermissions});
    }

</script>