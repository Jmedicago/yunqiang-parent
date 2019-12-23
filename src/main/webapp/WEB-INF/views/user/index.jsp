<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="userGrid" class="easyui-datagrid" title="<spring:message code="mu.sys.user"/>"
               data-options="rownumbers:true,fit:true,method:'get',
				pagination:true,pageSize:10,striped:true,singleSelect:true,
				toolbar:'#userTB',url:'/user/json'">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'username',width:100"><spring:message code="user.name"/></th>
                <th data-options="field:'roleIds',width:100,formatter: function(val, row){ return MXF.cellTooltipFormatter(row.roleList)}"><spring:message code="user.roles"/></th>
                <th data-options="field:'manager',width:100">负责人</th>
                <th data-options="field:'phone',width:100">联系电话</th>
                <th data-options="field:'stockIds',width:100,formatter: function(val, row){return MXF.cellTooltipFormatter(row.stockPath)}">归属区域</th>
                <th data-options="field:'remark', width:150, formatter:MXF.cellTooltipFormatter">门店描述</th>
                <th data-options="field:'status',width:50,formatter:userStateFormatter"><spring:message code="common.status"/></th>
                <%--<th data-options="field:'email',width:180"><spring:message code="user.email"/></th>--%>
                <th data-options="field:'createTime',width:200,formatter:MXF.dateTimeFormatter"><spring:message code="common.createTime"/></th>
            </tr>
            </thead>
        </table>
        <div id="userTB">
            <div>
                <a href="#" data-cmd="add" title="<spring:message code="common.add"/>" remote="false" class="easyui-linkbutton" iconCls="icon-add" plain="true">
                    <spring:message code="common.add"/>
                </a>
                <a href="#" data-cmd="edit" title="<spring:message code="common.edit"/>" mustsel remote="false" data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-edit" plain="true">
                    <spring:message code="common.edit"/>
                </a>
                <a href="#" data-cmd="del" title="<spring:message code="common.delete"/>" msg="<spring:message code="message.delete"/>" mustsel remote="false" data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-no" plain="true">
                    <spring:message code="common.delete"/>
                </a>
                <span class="buttonSplit">&nbsp;</span>
                <a href="#" data-cmd="modifyPsw" class="easyui-linkbutton" title="<spring:message code="user.btn.mpass"/>" mustsel remote="false"
                   iconCls="icon-lock-password" plain="true">
                    <spring:message code="user.btn.mpass"/>
                </a>
            </div>
            <div class="searchForm">
                <form>
                    <spring:message code="user.name"/>:
                    <input class="easyui-textbox theme-textbox-radius" name="username" style="width:100px;">
                    <a href="javascript:;" data-cmd="search" class="easyui-linkbutton button-default"><spring:message code="common.search"/></a>
                    <a href="javascript:;" data-cmd="resetSearch" class="easyui-linkbutton"><spring:message code="common.reset"/></a>
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
        if (v == 0) return '<green><spring:message code="status.normal"/></green>';
        if (v == 1) return '<red><spring:message code="status.disable"/></red>';
        if (v == 2) return '<orange><spring:message code="status.lock"/></orange>';
    }
    
    function modifyPsw() {
    	var row = $('#userGrid').datagrid('getSelected');
    	if (!row) {
    		MXF.alert('请选择一条记录，在继续操作！', false);
    	}
    	MXF.openDialog('modifyPswWindow', '修改密码', '/user/modify-psw?id=' + row.id, function() {
    		
    	}, 600, 350);
    }
</script>

