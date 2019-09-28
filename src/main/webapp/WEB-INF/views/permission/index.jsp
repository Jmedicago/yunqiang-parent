<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="permissionGrid" class="easyui-treegrid" title="<spring:message code="mu.sys.right"/>"
               data-options="rownumbers:true,fit:true,method:'get',striped:true,url:'/permission/json',idField:'id',treeField:'name'">
            <thead>
            <tr>
                <th data-options="field:'id'">ID</th>
                <th data-options="field:'name',width:180"><spring:message
                        code="permission.name"/></th>
                <th data-options="field:'icon',width:100,formatter:permissionIconFormatter"><spring:message
                        code="permission.icon"/></th>
                <th data-options="field:'url',width:150"><spring:message code="permission.resource"/></th>
                <th data-options="field:'type',width:100,formatter:permissionTypeFormatter"><spring:message
                        code="permission.type"/></th>
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

    function permissionTypeFormatter(v) {
        if (v == 0) return '<blue><spring:message code="common.menu"/></blue>';
        if (v == 1) return '<blue><spring:message code="common.button"/></blue>';
    }

    function permissionIconFormatter(v) {
        if (v == null) {
            return 'default';
        } else {
            return v;
        }
    }

    function formatterOptions(value, row, index) {
        if (row.parentId == 0) {
            var a = '<a href="#" data-cmd="addPermission"><span class="btn btn-add green"><spring:message code="common.add"/></span></a> ';
            var e = '<a href="#"><span class="btn btn-edit disable"><spring:message code="common.edit"/></span></a> ';
            var d = '<a href="#"><span class="btn btn-delete disable"><spring:message code="common.delete"/></span></a> ';
            return a + e + d;
        } else {
            var a = '<a href="#" data-cmd="addPermission"><span class="btn btn-add green"><spring:message code="common.add"/></span></a> ';
            var e = '<a href="#" data-cmd="editPermission"><span class="btn btn-edit default"><spring:message code="common.edit"/></span></a> ';
            var d = '<a href="#" data-cmd="deletePermission"><span class="btn btn-delete red"><spring:message code="common.delete"/></span></a> ';
            return a + e + d;
        }
    }

    function addPermission() {
        MXF.openDialog('addPermissionWin', '<spring:message code="common.add"/>', '/permission/edit', function (data) {
            var node = $('#permissionGrid').treegrid('getSelected');
            $('#permissionForm').form('load', {
                'parentId': node.id
            });
        }, 600, 550);
    }

    function editPermission() {
        var node = $('#permissionGrid').treegrid('getSelected');
        if (node) {
            MXF.openDialog('editPermissionWin', '<spring:message code="common.edit"/>', '/permission/edit?id=' + node.id, null, 600, 550);
        }
    }

    function deletePermission() {
        var node = $('#permissionGrid').treegrid('getSelected');
        if (node.id) {
            MXF.confirm('<spring:message code="message.delete"/>?', function () {
                MXF.ajaxing(true);
                $.post('/permission/delete', {id: node.id}, function (data) {
                    MXF.ajaxing(false);
                    MXF.ajaxFormDone(data);
                    if (data.success) {
                        $('#permissionGrid').treegrid('reload');
                    }
                });
            });
        } else {
            MXF.error('<spring:message code="message.select"/>');
        }
    }

</script>
