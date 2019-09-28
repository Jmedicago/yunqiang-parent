<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="productTypeGrid" class="easyui-treegrid" title="<spring:message code="mu.pt.type"/>"
               data-options="rownumbers:true,fit:true,method:'get',striped:true,url:'/product-type/json',idField:'id',treeField:'name'">
            <thead>
            <tr>
                <th data-options="field:'id'">ID</th>
                <th data-options="field:'name',width:180"><spring:message
                        code="product.type.name"/></th>
                <th data-options="field:'option',width:180,formatter:formatterOptions,align:'center'"><spring:message
                        code="common.option"/></th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script>
    $(function () {
        MXF.getTabContentHeight();
    });

    function formatterOptions(value, row, index) {
        if (row.parentId == 0) {
            var a = '<a href="#" data-cmd="addProductType"><span class="btn btn-add green"><spring:message code="common.add"/></span></a> ';
            var e = '<a href="#"><span class="btn btn-edit disable"><spring:message code="common.edit"/></span></a> ';
            var d = '<a href="#"><span class="btn btn-delete disable"><spring:message code="common.delete"/></span></a> ';
            return a + e + d;
        } else {
            var a = '<a href="#" data-cmd="addProductType"><span class="btn btn-add green"><spring:message code="common.add"/></span></a> ';
            var e = '<a href="#" data-cmd="editProductType"><span class="btn btn-edit default"><spring:message code="common.edit"/></span></a> ';
            var d = '<a href="#" data-cmd="deleteProductType"><span class="btn btn-delete red"><spring:message code="common.delete"/></span></a> ';
            return a + e + d;
        }
    }

    function addProductType() {
        MXF.openDialog('addProductTypeWin', '<spring:message code="common.add"/>', '/product-type/edit', function (data) {
            var node = $('#productTypeGrid').treegrid('getSelected');
            $('#productTypeForm').form('load', {
                'parentId': node.id
            });
        }, 600, 250);
    }

    function editProductType() {
        var node = $('#productTypeGrid').treegrid('getSelected');
        if (node) {
            MXF.openDialog('editProductTypeWin', '<spring:message code="common.edit"/>', '/product-type/edit?id=' + node.id, null, 600, 250);
        }
    }

    function deleteProductType() {
        var node = $('#productTypeGrid').treegrid('getSelected');
        if (node.id) {
            MXF.confirm('<spring:message code="message.delete"/>?', function () {
                MXF.ajaxing(true);
                $.post('/product-type/delete', {id: node.id}, function (data) {
                    MXF.ajaxing(false);
                    MXF.ajaxFormDone(data);
                    if (data.success) {
                        $('#productTypeGrid').treegrid('reload');
                    }
                });
            });
        } else {
            MXF.error('<spring:message code="message.select"/>');
        }
    }

</script>
