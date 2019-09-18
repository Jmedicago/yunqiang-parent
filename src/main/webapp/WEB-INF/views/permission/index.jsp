<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tableGroup">
    <table id="permissionGrid" class="easyui-treegrid" title="权限信息"
           data-options="rownumbers:true,fit:true,method:'get',striped:true,url:'/permission/json',idField:'id',treeField:'name',toolbar:'#permissionTB'">
        <thead>
        <tr>
            <th data-options="field:'id',checkbox:true"></th>
            <th data-options="field:'name',width:180">权限名称</th>
            <th data-options="field:'icon',width:100,formatter:permissionIconFormatter">图标</th>
            <th data-options="field:'url',width:100">资源地址</th>
            <th data-options="field:'type',width:100,formatter:permissionTypeFormatter">资源类型</th>
        </tr>
        </thead>
    </table>
    <div id="permissionTB">
        <div>
            <a href="#" data-cmd="addPermission" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
            <a href="#" data-cmd="editPermission" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        </div>
        <div class="searchForm">
            <form>
                权限名称:
                <input class="easyui-textbox theme-textbox-radius" name="name" style="width:100px;">
                <a href="javascript:;" data-cmd="search" class="easyui-linkbutton button-default">查询</a>
                <a href="javascript:;" data-cmd="resetSearch" class="easyui-linkbutton">重置</a>
            </form>
        </div>
    </div>
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

    function addPermission(clickTarget) {
        var row = $('#permissionGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error('请至少选中一条数据');
            return;
        }

        var isRemote = false;
        if ('true' == $(clickTarget).attr('remote') || true == $(clickTarget).attr('remote')) {
            isRemote = true;
        }

        var editWindow = $('<div></div>');
        editWindow.appendTo('body');
        editWindow.window({
            title: '新增',
            width: 600,
            height: 450,
            modal: true,
            closed: false,
            border: false,
            href: 'permission/edit',
            onLoad: function (data) {
                console.log(row);
                /*try {
                    var retJson = $.parseJSON(data);
                    MXF.ajaxFormDone(retJson);
                    editWindow.window('destroy');
                    return;
                } catch (e) {
                }
                var $form = editWindow.find('form');
                $form.data('window', editWindow);*/
            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });

    }

    function editPermission(clickTarget) {
        if ($(clickTarget).hasClass('l-btn-disabled')) return;
        var row = $('#permissionGrid').datagrid('getSelected');
        var editWindow = $('<div id="skuWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: '编辑',
            width: 600,
            height: 450,
            modal: true,
            href: '/permission/edit?id=' + row.id,
            onLoad: function () {
                var $form = editWindow.find('form');
                $form.data('window', editWindow);
            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });
    }
</script>
