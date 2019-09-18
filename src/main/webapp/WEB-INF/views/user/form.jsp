<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<form method="post" action="/user/store">
    <input type="hidden" name="id" value="${sysUser.id}"/>
    <div class="input-div">
        <label class="label-top">用户名</label>
        <input class="easyui-textbox theme-textbox-radius" name="username" value="${sysUser.username}"
               data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top">电子邮箱</label>
        <input class="easyui-textbox theme-textbox-radius" name="email" value="${sysUser.email}">
    </div>
    <div class="input-div">
        <label class="label-top">手机号码</label>
        <input id="brandCombo" class="easyui-textbox theme-textbox-radius" name="phone" value="${sysUser.phone}">
    </div>
    <div class="input-div">
        <label class="label-top">角色列表</label>
        <select id="rolesCombobox" class="easyui-combobox" name="roleIds"
                data-options="required:true,valueField: 'id',textField: 'name',onLoadSuccess:initRolesValue,
			multiple:true,url:'/role/list'"
                style="width:420px;">
        </select>
    </div>
    <div class="input-div">
        <label class="label-top"></label>
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this)">提交</a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)">清空</a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>
<script>

    function initRolesValue() {
        $('#rolesCombobox').combobox('setValues', ${selectRoles});
    }

</script>