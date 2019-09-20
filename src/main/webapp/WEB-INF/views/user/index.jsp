<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="userGrid" class="easyui-datagrid" title="${i18n.title}"
               data-options="rownumbers:true,fit:true,method:'get',
				pagination:true,pageSize:10,striped:true,singleSelect:false,
				toolbar:'#userTB',url:'/user/json'">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'username',width:100">${i18n.username}</th>
                <th data-options="field:'email',width:180">电子邮箱</th>
                <th data-options="field:'phone',width:100">手机号码</th>
                <th data-options="field:'status',width:50,formatter:userStateFormatter">状态</th>
                <th data-options="field:'createTime',width:200,formatter:MXF.dateTimeFormatter">创建时间</th>
            </tr>
            </thead>
        </table>
        <div id="userTB">
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
                    <input class="easyui-textbox theme-textbox-radius" name="username" style="width:100px;">
                    <a href="javascript:;" data-cmd="search" class="easyui-linkbutton button-default">查询</a>
                    <a href="javascript:;" data-cmd="resetSearch" class="easyui-linkbutton">重置</a>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        MXF.getTabContentHeight();
    });

    function userStateFormatter(v) {
        if (v == 0) return '<green>正常</green>';
        if (v == 1) return '<red>停用</red>';
        if (v == 2) return '<orange>锁定</orange>';
    }
</script>

