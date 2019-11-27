<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<form method="post" action="/product/store">
    <input type="hidden" name="id"/>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.name"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="name" data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.productType"/></label>
        <input class="easyui-combotree theme-textbox-radius" name="productType"
               data-options="required: true, url: '/product-type/json', method: 'get', onChange: changeProductType">
    </div>
    <%--<div class="input-div">
        <label class="label-top"><spring:message code="product.stock"/></label>
        <input class="easyui-combotree theme-textbox-radius" name="stock"
               data-options="url: '/stock/json', editable: true, method: 'get'">
    </div>--%>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.code"/></label>
        <input id="code" class="easyui-textbox theme-textbox-radius" name="code" data-options="required:true"
               value="${bisProduct.code}">
    </div>
    <%--<div class="input-div">
        <label class="label-top"><spring:message code="product.resources"/></label>
        <div style="margin-left: 114px; margin-top: -30px;">
           &lt;%&ndash; <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload" style="background-color: #0c80d7; color: #fff;"><spring:message code="common.uploadImage"/></a>
            <input type="hidden" name="resources" value="${resources}"/>&ndash;%&gt;
            <a href="javascript:void(0)" class="easyui-linkbutton onePicUpload" style="background-color: #0c80d7; color: #fff;"><spring:message code="common.uploadImage"/></a>
            <input type="hidden" name="resources" value="${resources}"/>
        </div>
    </div>--%>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.properties"/></label>
        <select id="propertiesCombobox" class="easyui-combobox" name="properties"
                data-options="editable: false,valueField: 'id', textField: 'name', onLoadSuccess: initPropertiesValue,
			multiple: true, url: '/property/json?productType=${productType}'"
                style="width:420px;"><!--required: true,-->
        </select>
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="product.remark"/></label>
        <input class="easyui-textbox theme-textbox-radius" data-options="multiline:true" style="height:60px;"
               name="remark" value="${bisProduct.remark}">
    </div>
    <div class="input-div" style="margin: 35px 0; text-align: center">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this)"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
</form>

<script>

    function initPropertiesValue() {
        $('#propertiesCombobox').combobox('setValues', [${selectProperties}]);
    }

    function changeProductType(newValue, oldValue) {
        //
        $.get('/property/json?productType=' + newValue, function (data) {
            var select = [];
            for (var i = 0; i < data.length; i++) {
                select.push(data[i].id);
            }
            $('#propertiesCombobox').combobox('setValues', select);
        });

        // $('#propertiesCombobox').combobox('setValues', []);
        $('#propertiesCombobox').combobox('reload', '/property/json?productType=' + newValue);
    }

    var resources = '${resources}';
    console.log(resources);
    MXF.initOnePicUploader(resources);

</script>