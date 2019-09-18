<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<form method="post" action="/permission/store">
    <input type="hidden" name="id" value="${sysPermission.id}"/>
    <div class="input-div">
        <label class="label-top">权限名称</label>
        <input class="easyui-textbox theme-textbox-radius" name="name" value="${sysPermission.name}"
               data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top">资源图标</label>
        <input class="easyui-textbox theme-textbox-radius" name="icon" value="${sysPermission.icon}">
    </div>
    <div class="input-div">
        <label class="label-top">资源类型</label>
        <select class="easyui-combobox" style="width:420px;" name="type">
            <option value="0">菜单</option>
            <option value="1">按钮</option>
        </select>
    </div>
    <div class="input-div">
        <label class="label-top">资源地址</label>
        <input class="easyui-textbox theme-textbox-radius" name="url" value="${sysPermission.url}">
    </div>
    <div class="input-div">
        <label class="label-top">权限字符串</label>
        <input class="easyui-textbox theme-textbox-radius" name="permission" value="${sysPermission.permission}">
    </div>
    <div class="input-div">
        <label class="label-top">权限描述</label>
        <input class="easyui-textbox theme-textbox-radius" data-options="multiline:true" style="height:60px;"
               name="description" value="${sysPermission.description}">
    </div>
    <div class="input-div">
        <label class="label-top"></label>
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this)">提交</a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)">清空</a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>
