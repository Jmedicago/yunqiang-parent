<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form id="productTypeForm" method="post" action="/product-type/store">
    <input type="hidden" name="id" value="${bisProductType.id}"/>
    <input type="hidden" name="parentId" value="${bisProductType.parentId}"/>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.type.name"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="name" value="${bisProductType.name}" placeholder="<spring:message code="message.input"/>"
               data-options="required:true">
    </div>
    <div class="input-div" style="margin-top: 35px;text-align: center">
        <a class="easyui-linkbutton button-lg button-default" onclick="storeProductType(this)"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>

<script>
    function storeProductType(obj, callbackFn, beforeSubmit) {
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
                    $('#productTypeGrid').treegrid('reload');
                    $('#addProductTypeWin').window('close');
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
