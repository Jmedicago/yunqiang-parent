<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<form id="permissionForm" method="post" action="/permission/store">
    <input type="hidden" name="id" value="${sysPermission.id}"/>
    <input type="hidden" name="parentId" value="${sysPermission.parentId}"/>
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
        <a class="easyui-linkbutton button-lg button-default" onclick="storePermission(this)">提交</a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)">清空</a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>

<script>
    function storePermission(obj, callbackFn, beforeSubmit) {
        var $form = $(obj);
        if (!$form.is('form')) {
            $form = $(obj).closest('form');
        }
        if (!$form.is('form')) return;
        $form.form('submit', {
            url: $form.attr('action'),
            onSubmit: function (param) {
                var isValid = $(this).form('enableValidation').form('validate');
                if (isValid) {
                    MXF.ajaxing(true);
                }
                if ($.isFunction(beforeSubmit)) {
                    beforeSubmit(param);
                }
                return isValid;	// 返回false终止表单提交
            },
            success: function (text) {
                var data = JSON.parse(text);
                MXF.ajaxing(false);
                MXF.ajaxFormDone(data);
                if (data.success) {
                    $('#permissionGrid').treegrid('reload');
                    $('#addPermissionWin').window('close');
                    if (callbackFn) {
                        callbackFn($form, data);
                    }
                } else {
                    MXF.error(data.message + data.info);
                }
            }
        });
    }
</script>
