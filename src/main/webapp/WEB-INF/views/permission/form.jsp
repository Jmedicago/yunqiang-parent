<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form id="permissionForm" style="width: 600px; margin: 0 auto;" method="post" action="/permission/store">
    <input type="hidden" name="id"/>
    <div class="input-div">
        <label class="label-top"><spring:message code="permission.name"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="name" value="${sysPermission.name}"
               placeholder="<spring:message code="message.input"/>"
               data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="permission.parent"/></label>
        <input id="permissionTreeCombo" class="easyui-combotree theme-textbox-radius" name="parentId" data-options="url:'/permission/json', method:'get',
						onLoadSuccess:function() {
							var comboTree = $('#permissionTreeCombo').combotree('tree');
							$(comboTree).tree('collapseAll');
						}">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="permission.icon"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="icon" value="${sysPermission.icon}">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="permission.type"/></label>
        <select class="easyui-combobox" style="width:450px;" name="type">
            <option value="0"><spring:message code="common.menu"/></option>
            <option value="1"><spring:message code="common.button"/></option>
        </select>
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="permission.sort"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="sort">
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
    <div class="input-div" style="margin: 35px 0; padding-left: 100px; text-align: center">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this, permissionStoreCallBack)">
            <spring:message code="common.submit"/>
        </a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)">
            <spring:message code="common.reset"/>
        </a>
    </div>
</form>

<script type="text/javascript">
    function permissionStoreCallBack($form, data) {
        if (data.success) {
            //MXF.clearForm($form);
            $('#permissionTree').tree('reload');
            $('#permissionTreeCombo').combotree('reload');
        }
    }
</script>


