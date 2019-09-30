<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',title:'<spring:message code="mu.sys.right"/>',split:true,border:true"
             style="width:300px;">
            <ul id="permissionTree" class="easyui-tree"
                data-options="
                    url: '/permission/json',
					state:'closed',
					method: 'get',
					animate: true,
					onClick: clickPermissionTreeNode,
					onAfterEdit : updatePermissionTreeNode,
					onContextMenu: function(e,node){
						e.preventDefault();
						$(this).tree('select',node.target);
						$('#permissionTreeContextMenu').menu('show',{
							left: e.pageX,
							top: e.pageY
						});
					},
					onLoadSuccess:function(){
						$('#permissionTree').tree('expandAll');
					}">
            </ul>
        </div>
        <div data-options="region:'center',border:true,title:'<spring:message code="common.edit"/>',href:'/permission/edit'"
             style="padding: 15px;"></div>
    </div>
    <!-- 鼠标左键菜单 -->
    <div id="permissionTreeContextMenu" class="easyui-menu" style="width: 120px;">
        <div onclick="appendPermissionTreeNode()" data-options="iconCls:'icon-add'">
            <spring:message code="common.tree.append"/>
        </div>
        <div onclick="removePermissionTreeNode()" data-options="iconCls:'icon-remove'">
            <spring:message code="common.tree.remove"/>
        </div>
        <div class="menu-sep"></div>
        <div onclick="expandPermissionTree()">
            <spring:message code="common.tree.expand"/>
        </div>
        <div onclick="collapsePermissionTree()">
            <spring:message code="common.tree.collapse"/>
        </div>
        <div onclick="reloadPermissionTree()">
            <spring:message code="common.tree.reload"/>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        MXF.getTabContentHeight();
    });

    function clickPermissionTreeNode(node) {
        //$(this).tree('beginEdit',node.target);
        MXF.clearForm($('#permissionForm'));
        $('#permissionForm').form('load', node);
    }

    function updatePermissionTreeNode(node) {
        console.debug(node);
    }

    function appendPermissionTreeNode() {
        var t = $('#permissionTree');
        var node = t.tree('getSelected');
        var parentNode = (node ? node.target : null);
        var parentNodeId = 1000;
        if (null != node) parentNodeId = node.id;
        t.tree('append', {
            parent: parentNode,
            data: [{
                pid: parentNodeId, text: '<spring:message code="common.tree.node"/>'
            }]
        });
    }

    function removePermissionTreeNode() {
        var node = $('#permissionTree').tree('getSelected');
        if (node && node.id != null) {
            MXF.confirm('<spring:message code="message.delete"/>?', function () {
                MXF.ajaxing(true);
                $.post('/permission/delete', {id: node.id}, function (data) {
                    MXF.ajaxing(false);
                    MXF.ajaxFormDone(data);
                    if (data.success) {
                        $('#permissionTree').tree('remove', node.target);
                    }
                });
            });
        } else {
            $('#permissionTree').tree('remove', node.target);
        }
    }

    function collapsePermissionTree() {
        var node = $('#permissionTree').tree('getSelected');
        $('#permissionTree').tree('collapse', node.target);
    }

    function expandPermissionTree() {
        var node = $('#permissionTree').tree('getSelected');
        $('#permissionTree').tree('expand', node.target);
    }

    function reloadPermissionTree() {
        $('#permissionTree').tree('reload');
    }

</script>
