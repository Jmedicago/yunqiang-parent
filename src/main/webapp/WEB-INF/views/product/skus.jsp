<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div style="padding:10px">
    <a href="#" onclick="addSku()" class="easyui-linkbutton" iconCls="icon-add"><spring:message code="sku.add"/></a>
    <div id="editSkuDiv"
         style="display:none;border:solid 1px #ccc;border-radius:4px;padding:10px;margin-top:10px;overflow:hidden;background:#f9f9f9;">
        <form id="skuForm" action="/product/storeSku" method="post">
            <input type="hidden" name="id"/>
            <input type="hidden" name="productId" value="${product.id}"/>
            <div style="width:250px; float:left; border: 1px solid #ddd; padding: 10px 15px; border-radius: 5px;">
                <h5 style="padding: 0; border: 0;">商品属性信息</h5>
                <table class="table table-very table-basic">
                    <c:forEach items="${skuProperties}" var="p" varStatus="psta">
                        <c:if test="${p.inputMode == 0}">
                            <tr>
                                <td>
                                    <input type="hidden" name="propId" value="${p.id}"/>
                                    <input type="hidden" name="propName" value="${p.name}"/>
                                        ${p.name}</td>
                                <td>
                                    <input type="hidden" name="optionId" value="0"/>
                                    <input class="easyui-textbox" data-options="required:true" style="width:200px;"
                                           name="optionValue"/>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${p.inputMode == 1}">
                            <tr>
                                <td>
                                    <input type="hidden" name="propId" value="${p.id}"/>
                                    <input type="hidden" name="propName" value="${p.name}"/>
                                        ${p.name}</td>
                                <td>
                                    <select name="optionId" class="easyui-combobox" style="width:200px;"
                                            data-options="required: true, valueField: 'id', textField: 'optionValue', onChange: changeOption, url: '/property/getOptions?id=${p.id}'"></select>
                                    <input type="hidden" name="optionValue"/>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </div>
            <div style="width:1000px;float:right;border: 1px solid #ddd; padding: 10px 15px; border-radius: 5px;">
                <h5 style="padding: 0; border: 0;">商品基本信息</h5>
                <table class="table table-very table-basic">
                    <tr>
                        <td><spring:message code="sku.pack"/></td>
                        <td>
                            <input class="easyui-textbox" name="pack"
                                   style="width:200px;"/>
                        </td>
                        <td><spring:message code="sku.volume"/>（m<sup>3</sup>）</td>
                        <td>
                            <input class="easyui-textbox" name="volume"
                                   style="width:200px;"/>
                        </td>
                        <td><spring:message code="sku.container"/></td>
                        <td>
                            <input class="easyui-textbox" name="container"
                                   style="width:200px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><spring:message code="sku.costPrice"/></td>
                        <td>
                            <input class="easyui-textbox" name="costPrice"
                                   style="width:200px;"/>
                        </td>
                        <td><spring:message code="sku.marketPrice"/></td>
                        <td>
                            <input class="easyui-textbox" name="marketPrice"
                                   style="width:200px;"/>
                        </td>
                        <%--<td>&lt;%&ndash;<spring:message code="sku.profit"/>（%）&ndash;%&gt;</td>
                        <td>
                            &lt;%&ndash;<input class="easyui-textbox" name="profit"
                                   style="width:200px;"/>&ndash;%&gt;
                        </td>--%>
                        <td><spring:message code="sku.supplier"/></td>
                        <td>
                            <input class="easyui-textbox" name="supplier"
                                   style="width:200px;"/>
                        </td>
                    </tr>
                    <tr>
                        <%--<td><spring:message code="sku.supplier"/></td>
                        <td>
                            <input class="easyui-textbox" name="supplier"
                                   style="width:200px;"/>
                        </td>--%>
                        <td><spring:message code="sku.availableStock"/></td>
                        <td>
                            <input class="easyui-textbox" name="availableStock"
                                   style="width:200px;"/>
                        </td>
                        <td>备注</td>
                        <td>
                            <input class="easyui-textbox" name="remark"
                                   style="width:200px;"/>
                        </td>
                        <td><%--<spring:message code="sku.frozenStock"/>--%></td>
                        <td>
                            <%--<input class="easyui-textbox" name="frozenStock"
                                   style="width:200px;"/>--%>
                        </td>
                    </tr>
                    <tr>
                        <td><spring:message code="sku.skuMainPic"/></td>
                        <td colspan="4" id="skuPicEditor">
                            <a href="javascript:void(0)" class="easyui-linkbutton onePicUpload"
                               style="background-color: #0c80d7; color: #fff;"><spring:message
                                    code="common.uploadImage"/></a>
                            <input type="hidden" name="skuMainPic" value="${sku.skuMainPic}"/>
                        <td>
                            <a class="easyui-linkbutton button-lg button-default"
                               onclick="MXF.ajaxForm(this, storeSkuCallback, formatSkuPrice)"><spring:message
                                    code="common.submit"/></a>
                            <a class="easyui-linkbutton button-lg button-danger"
                               onclick="$('#editSkuDiv').hide()"><spring:message code="common.reset"/></a>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="sku.skuId"/></th>
            <c:forEach items="${skuProperties}" var="p">
                <th propId="${p.id}">${p.name}</th>
            </c:forEach>
            <th><spring:message code="sku.pack"/></th>
            <th><spring:message code="sku.volume"/>（m<sup>3</sup>）</th>
            <th><spring:message code="sku.container"/></th>
            <th><spring:message code="sku.costPrice"/></th>
            <th><spring:message code="sku.marketPrice"/></th>
            <th><spring:message code="sku.profit"/>（%）</th>
            <th><spring:message code="sku.supplier"/></th>
            <th>总仓库存<%--<spring:message code="sku.availableStock"/>--%></th>
            <%--<th><spring:message code="sku.frozenStock"/></th>--%>
            <th><spring:message code="sku.skuMainPic"/></th>
            <th><spring:message code="common.option"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${skuList}" var="sku">
            <tr>
                <td>${sku.skuCode}</td>
                <c:forEach items="${skuProperties}" var="p">
                    <c:forEach items="${sku.skuPropertyList}" var="op">
                        <c:if test="${p.id == op.propId}">
                            <td>${op.value}</td>
                        </c:if>
                    </c:forEach>
                </c:forEach>
                <td>${sku.pack}</td>
                <td>${sku.volume}</td>
                <td>${sku.container}</td>
                <td><fmt:formatNumber value="${sku.costPrice * 0.01}" type="currency" pattern="00MTn"/></td>
                <td><fmt:formatNumber value="${sku.marketPrice * 0.01}" type="currency" pattern="00MTn"/></td>
                <td>${sku.profit}</td>
                <td title="${sku.supplier}">${fn:substring(sku.supplier, 0, 15)}</td>
                <%--<td>${sku.availableStock}</td>--%>
                <td>${sku.defaultStock}</td>
                <%--<td>${sku.frozenStock}</td>--%>
                <td>
                    <c:if test="${sku.skuMainPic != null && sku.skuMainPic != ''}">
                        <img height="30" width="30" src="${sku.skuMainPic}"/>
                    </c:if>
                </td>
                <td><a class="easyui-linkbutton button-default button-sm" onclick="editSku(${sku.id})"><spring:message
                        code="common.edit"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script type="text/javascript">

    function clearSkuForm() {
        $('#skuForm').find('[name=id]').val('');
        $('#skuForm').find('[textboxname=pack]').textbox('setValue', '');
        $('#skuForm').find('[textboxname=volume]').textbox('setValue', '');
        $('#skuForm').find('[textboxname=container]').textbox('setValue', '');
        $('#skuForm').find('[textboxname=costPrice]').textbox('setValue', '');
        $('#skuForm').find('[textboxname=marketPrice]').textbox('setValue', '');
        $('#skuForm').find('[textboxname=profit]').textbox('setValue', '');
        $('#skuForm').find('[textboxname=supplier]').textbox('setValue', '');
        $('#skuForm').find('[textboxname=availableStock]').textbox('setValue', '');
        $('#skuForm').find('[textboxname=frozenStock]').textbox('setValue', '');
        $('#skuForm').find('[textboxname=skuMainPic]').textbox('setValue', '');
    }

    function addSku() {
        $('#editSkuDiv').show();
        clearSkuForm();
        MXF.initOnePicUploader();
    }

    function editSku(id) {
        $.post('/product/sku/' + id, {}, function (data) {
            $('#editSkuDiv').show();
            data.marketPrice = (data.marketPrice * 0.01).toFixed(2);
            data.costPrice = (data.costPrice * 0.01).toFixed(2);
            data.availableStock = data.defaultStock;
            $('#skuForm').form('load', data);
            //重新渲染
            MXF.initOnePicUploader(data.skuMainPic);

            //对属性集合进行赋值
            var propertyArr = data.skuPropertyList;
            for (var i = 0; i < propertyArr.length; i++) {
                var propSetObj = propertyArr[i];
                var propId = propSetObj.propId;
                var propInput = $('#skuForm').find('input[name="propId"][value="' + propId + '"]');

                var propInputDiv = $(propInput).closest('tr');
                var optionIdInput = propInputDiv.find('[name=optionId]');
                var optionIdValue = propSetObj['optionId'];
                if (0 != optionIdValue) {
                    optionIdInput = propInputDiv.find('select');
                    $(optionIdInput).combobox('setValue', optionIdValue);
                } else {
                    optionIdInput.val(optionIdValue);
                }
                propInputDiv.find('[name=optionValue]').val(propSetObj['value']);
                propInputDiv.find('[textboxname=optionValue]').textbox('setValue', propSetObj['value']);
            }
        });
    }

    function changeOption(newValue, oldValue) {
        var optionValue = $(this).combobox('getText');
        $(this).closest('tr').find('[name=optionValue]').val(optionValue);
    }

    function storeSkuCallback($form, data) {
        $('#skuWindow').window('refresh');
    }

    function formatSkuPrice(param, formData) {
        console.log('before submit..', param);
    }
</script>
