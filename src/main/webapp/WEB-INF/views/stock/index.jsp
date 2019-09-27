<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="stockGrid" class="easyui-treegrid" title="<spring:message code="mu.st"/>"
               data-options="rownumbers:true,fit:true,method:'get',striped:true,url:'/stock/json',idField:'id',treeField:'name'">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'name',width:180"><spring:message code="stock.name"/></th>
                <th data-options="field:'description',width:180,formatter:stackDescFormatter"><spring:message code="stock.desc"/></th>
                <th data-options="field:'status',width:50,formatter:stackStatusFormatter,align:'center'"><spring:message code="common.status"/></th>
                <th data-options="field:'createTime',width:180,formatter:MXF.dateTimeFormatter,align:'center'"><spring:message code="common.createTime"/></th>
                <th data-options="field:'option',width:180,formatter:formatterOptions,align:'center'"><spring:message code="common.option"/></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script>
    $(function () {
        MXF.getTabContentHeight();
    });

    function stackDescFormatter(v) {
        if (v) {
            return v.substring(0, 10);
        } else {
            return '<spring:message code="common.un"/>';
        }
    }
    
    function stackStatusFormatter(v) {
        if (v == 0) { return '<green><spring:message code="status.normal"/></green>'}
        if (v == 1) {return '<red><spring:message code="status.disable"/></red>'}
    }

    function formatterOptions(value, row, index) {
        var a = '<a href="#" data-cmd="addStock"><span class="btn btn-add green"><spring:message code="common.add"/></span></a> ';
        var e = '<a href="#" data-cmd="editStock"><span class="btn btn-edit default"><spring:message code="common.edit"/></span></a> ';
        var d = '<a href="#" data-cmd="deleteStock"><span class="btn btn-delete red"><spring:message code="common.delete"/></span></a> ';
        return a + e + d;
    }

    function addStock() {
        MXF.openDialog('addStockWin', '<spring:message code="common.add"/>', '/stock/edit', function (data) {
            var node = $('#stockGrid').treegrid('getSelected');
            $('#stockForm').form('load', {
                'parentId': node.id
            });
        });
    }

    function editStock() {
        var node = $('#stockGrid').treegrid('getSelected');
        if (node) {
            MXF.openDialog('editStockWin', '<spring:message code="common.edit"/>', '/stock/edit?id=' + node.id);
        }
    }

    function deleteStock() {
        var node = $('#stockGrid').treegrid('getSelected');
        if (node.id) {
            MXF.confirm('<spring:message code="message.delete"/>?', function () {
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
            MXF.error('<spring:message code="message.select"/>');
        }
    }

</script>