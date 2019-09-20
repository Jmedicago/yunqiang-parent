<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tableGroup">
    <table id="permissionGrid" class="easyui-treegrid" title="权限信息"
           data-options="rownumbers:true,fit:true,method:'get',striped:true,url:'/permission/json',idField:'id',treeField:'name'">
        <thead>
        <tr>
            <th data-options="field:'id',checkbox:true"></th>
            <th data-options="field:'name',width:180">权限名称</th>
            <th data-options="field:'icon',width:100,formatter:permissionIconFormatter">图标</th>
            <th data-options="field:'url',width:100">资源地址</th>
            <th data-options="field:'type',width:100,formatter:permissionTypeFormatter">资源类型</th>
            <th data-options="field:'option',width:180,formatter:formatterOptions,align:'center'">操作</th>
        </tr>
        </thead>
    </table>
</div>
<script>
    function permissionTypeFormatter(v) {
        if (v == 0) return '<blue>菜单</blue>';
        if (v == 1) return '<blue>按钮</blue>';
    }

    function permissionIconFormatter(v) {
        if (v == null) {
            return 'default';
        } else {
            return v;
        }
    }

    function formatterOptions(value, row, index) {
        var a = '<a href="#" data-cmd="addPermission"><span class="btn btn-add green">添加</span></a> ';
        var e = '<a href="#" data-cmd="editPermission"><span class="btn btn-edit default">编辑</span></a> ';
        var d = '<a href="#" data-cmd="deletePermission"><span class="btn btn-delete red">删除</span></a> ';
        return a + e + d;
    }

    function addPermission() {
        MXF.openDialog('addPermissionWin', '新增', '/permission/edit', function (data) {
            var node = $('#permissionGrid').treegrid('getSelected');
            $('#permissionForm').form('load', {
                'parentId': node.id
            });
        });
    }

    function editPermission() {
        var node = $('#permissionGrid').treegrid('getSelected');
        if (node) {
            MXF.openDialog('editPermissionWin', '编辑', '/permission/edit?id=' + node.id);
        }
    }

    function deletePermission() {
        var node = $('#permissionGrid').treegrid('getSelected');
        if (node.id) {
            MXF.confirm('确认删除?', function () {
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
            MXF.error('请至少选中一条数据');
        }
    }

</script>
