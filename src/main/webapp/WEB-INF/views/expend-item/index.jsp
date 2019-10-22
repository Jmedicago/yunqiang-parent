<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="expendItemGrid" class="easyui-datagrid" title="支出项" data-options="
                    rownumbers: true,
                    fit: true,
                    method:'post',
				    pagination: true,
				    pageSize: 10,
				    striped: true,
				    singleSelect: false,
				    toolbar: '#expendItemTB',
				    url: '/expend-item/json'">
            <thead>
            <tr>
                <th data-options="field:'id', checkbox: true"></th>
                <th data-options="field:'category', width: 100">类型</th>
                <th data-options="field:'article', width: 180">项目</th>
                <th data-options="field: 'remark', width: 200">备注</th>
                <th data-options="field:'createTime',width:200,formatter:MXF.dateTimeFormatter"><spring:message
                        code="common.createTime"/></th>
            </tr>
            </thead>
        </table>
        <div id="expendItemTB">
            <div>
                <a href="#" data-cmd="add" height="350" title="<spring:message code="common.add"/>" remote="false"
                   class="easyui-linkbutton" iconCls="icon-add" plain="true">
                    <spring:message code="common.add"/>
                </a>
                <a href="#" data-cmd="edit" height="350" title="<spring:message code="common.edit"/>" mustsel remote="false"
                   data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-edit" plain="true">
                    <spring:message code="common.edit"/>
                </a>
            </div>
            <div class="searchForm">
                <form>
                    <spring:message code="user.name"/>:
                    <input class="easyui-textbox theme-textbox-radius" name="username" style="width:100px;">
                    <a href="javascript:;" data-cmd="search" class="easyui-linkbutton button-default"><spring:message
                            code="common.search"/></a>
                    <a href="javascript:;" data-cmd="resetSearch" class="easyui-linkbutton"><spring:message
                            code="common.reset"/></a>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        MXF.getTabContentHeight();
    });
</script>

