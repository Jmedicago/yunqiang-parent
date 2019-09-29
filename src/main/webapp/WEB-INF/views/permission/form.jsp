<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form id="permissionForm" method="post" action="/permission/store">
    <input type="hidden" name="id" value="${sysPermission.id}"/>
    <input type="hidden" name="parentId" value="${sysPermission.parentId}"/>
    <div class="input-div">
        <label class="label-top"><spring:message code="permission.name"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="name" value="${sysPermission.name}" placeholder="<spring:message code="message.input"/>"
               data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="permission.icon"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="icon" value="${sysPermission.icon}">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="permission.type"/></label>
        <select class="easyui-combobox" style="width:420px;" name="type">
            <option value="0"><spring:message code="common.menu"/></option>
            <option value="1"><spring:message code="common.button"/></option>
        </select>
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="permission.resource"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="url" value="${sysPermission.url}">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="permission.string"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="permission" value="${sysPermission.permission}">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="permission.desc"></spring:message></label>
        <input class="easyui-textbox theme-textbox-radius" data-options="multiline:true" style="height:60px;"
               name="description" value="${sysPermission.description}">
    </div>
    <div class="input-div" style="margin: 35px 0;text-align: center">
        <a class="easyui-linkbutton button-lg button-default" onclick="storePermission(this)"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
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
