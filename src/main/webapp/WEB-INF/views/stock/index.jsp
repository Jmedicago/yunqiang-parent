<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="tableGroup">
    <table id="stockGrid" class="easyui-treegrid" title="库存信息"
           data-options="rownumbers:true,fit:true,method:'get',striped:true,url:'/stock/json',idField:'id',treeField:'name',toolbar:'#stockTB'">
        <thead>
        <tr>
            <th data-options="field:'id',checkbox:true"></th>
            <th data-options="field:'name',width:180">仓库名称</th>
        </tr>
        </thead>
    </table>
    <div id="stockTB">
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