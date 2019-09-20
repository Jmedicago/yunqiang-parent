<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tableGroup">
    <table id="roleGrid" class="easyui-datagrid" title="角色信息"
           data-options="rownumbers:true,fit:true,method:'get',
				pagination:true,pageSize:10,striped:true,singleSelect:false,
				toolbar:'#roleTB',url:'/role/json'">
        <thead>
        <tr>
            <th data-options="field:'id',checkbox:true"></th>
            <th data-options="field:'name',width:100">角色名称</th>
            <th data-options="field:'description',width:180">角色描述</th>
        </tr>
        </thead>
    </table>
    <div id="roleTB">
        <div>
            <a href="#" data-cmd="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">
                <span i18n="table.add">添加</span>
            </a>
            <a href="#" data-cmd="edit" mustsel remote="true" data-options="disabled:true" class="easyui-linkbutton"
               iconCls="icon-edit" plain="true">
                <span i18n="table.edit">编辑</span>
            </a>
        </div>
        <div class="searchForm">
            <form>
                用户名:
                <input class="easyui-textbox theme-textbox-radius" name="role" style="width:100px;">
                <a href="javascript:;" data-cmd="search" class="easyui-linkbutton button-default">查询</a>
                <a href="javascript:;" data-cmd="resetSearch" class="easyui-linkbutton">重置</a>
            </form>
        </div>
    </div>
</div>

<script>

</script>

