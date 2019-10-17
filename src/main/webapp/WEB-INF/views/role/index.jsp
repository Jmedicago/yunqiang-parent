<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="roleGrid" class="easyui-datagrid" title="<spring:message code="mu.sys.role"/>"
               data-options="rownumbers:true,fit:true,method:'get',
				pagination:true,pageSize:10,striped:true,singleSelect:false,
				toolbar:'#roleTB',url:'/role/json'">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'name',width:100"><spring:message code="role.name"/></th>
                <th data-options="field:'description',width:180"><spring:message code="role.desc"/></th>
            </tr>
            </thead>
        </table>
        <div id="roleTB">
            <div>
                <a href="#" data-cmd="add" title="<spring:message code="common.add"/>" remote="false" class="easyui-linkbutton" iconCls="icon-add" plain="true">
                    <spring:message code="common.add"/>
                </a>
                <a href="#" data-cmd="edit" title="<spring:message code="common.edit"/>" mustsel remote="false" data-options="disabled:true"
                   class="easyui-linkbutton"
                   iconCls="icon-edit" plain="true">
                    <spring:message code="common.edit"/>
                </a>
            </div>
            <div class="searchForm">
                <form>
                    <spring:message code="role.name"/>:
                    <input class="easyui-textbox theme-textbox-radius" name="role" style="width:100px;">
                    <a href="javascript:;" data-cmd="search" class="easyui-linkbutton button-default">
                        <spring:message code="common.search"/>
                    </a>
                    <a href="javascript:;" data-cmd="resetSearch" class="easyui-linkbutton">
                        <spring:message code="common.reset"/>
                    </a>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        MXF.getTabContentHeight();
    });
</script>