<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tableGroup">
    <table id="stockGrid" class="easyui-treegrid" title="仓库信息"
           data-options="rownumbers:true,fit:true,method:'get',striped:true,url:'/stock/json',idField:'id',treeField:'name'">
        <thead>
        <tr>
            <th data-options="field:'id',checkbox:true"></th>
            <th data-options="field:'name',width:180">仓库名称</th>
            <th data-options="field:'description',width:180,formatter:stackDescFormatter">描述</th>
            <th data-options="field:'status',width:50,formatter:stackStatusFormatter,align:'center'">状态</th>
            <th data-options="field:'createTime',width:180,formatter:MXF.dateTimeFormatter,align:'center'">创建时间</th>
            <th data-options="field:'option',width:180,formatter:formatterOptions,align:'center'">操作</th>
        </tr>
        </thead>
    </table>
</div>

<script>
    function stackDescFormatter(v) {
        if (v) {
            return v.substring(0, 10);
        } else {
            return '未知';
        }
    }
    
    function stackStatusFormatter(v) {
        if (v == 0) { return '<green>正常</green>'}
        if (v == 1) {return '<red>停用</red>'}
    }

    function formatterOptions(value, row, index) {
        var a = '<a href="#" data-cmd="addStock"><span class="btn btn-add green">添加</span></a> ';
        var e = '<a href="#" data-cmd="editStock"><span class="btn btn-edit default">编辑</span></a> ';
        var d = '<a href="#" data-cmd="deleteStock"><span class="btn btn-delete red">删除</span></a> ';
        return a + e + d;
    }

    function addStock() {
        MXF.openDialog('addStockWin', '新增', '/stock/edit', function (data) {
            var node = $('#stockGrid').treegrid('getSelected');
            $('#stockForm').form('load', {
                'parentId': node.id
            });
        });
    }

    function editStock() {
        var node = $('#stockGrid').treegrid('getSelected');
        if (node) {
            MXF.openDialog('editStockWin', '编辑', '/stock/edit?id=' + node.id);
        }
    }

    function deleteStock() {
        var node = $('#stockGrid').treegrid('getSelected');
        if (node.id) {
            MXF.confirm('确认删除?', function () {
                MXF.ajaxing(true);
                $.post('/stock/delete', {id: node.id}, function (data) {
                    MXF.ajaxing(false);
                    MXF.ajaxFormDone(data);
                    if (data.success) {
                        $('#stockGrid').treegrid('reload');
                    }
                });
            });
        } else {
            MXF.error('请至少选中一条数据');
        }
    }

</script>