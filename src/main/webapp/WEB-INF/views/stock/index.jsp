<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',title:'<spring:message code="mu.st"/>',split:true,border:true"
             style="width:300px;">
            <ul id="stockTree" class="easyui-tree"
                data-options="
                    url: '/stock/json',
					state:'closed',
					method: 'get',
					animate: true,
					onClick: clickStockTreeNode,
					onAfterEdit : updateStockTreeNode,
					onContextMenu: function(e,node){
						e.preventDefault();
						$(this).tree('select',node.target);
						$('#stockTreeContextMenu').menu('show',{
							left: e.pageX,
							top: e.pageY
						});
					},
					onLoadSuccess:function(){
						$('#stockTree').tree('expandAll');
					}">
            </ul>
        </div>
        <div data-options="region:'center',border:true,title:'<spring:message code="common.edit"/>',href:'/stock/edit'"
             style="padding: 15px;"></div>
    </div>
    <!-- 鼠标左键菜单 -->
    <div id="stockTreeContextMenu" class="easyui-menu" style="width: 120px;">
        <div onclick="appendStockTreeNode()" data-options="iconCls:'icon-add'">
            <spring:message code="common.tree.append"/>
        </div>
        <div onclick="removeStockTreeNode()" data-options="iconCls:'icon-remove'">
            <spring:message code="common.tree.remove"/>
        </div>
        <div class="menu-sep"></div>
        <div onclick="expandStockTree()">
            <spring:message code="common.tree.expand"/>
        </div>
        <div onclick="collapseStockTree()">
            <spring:message code="common.tree.collapse"/>
        </div>
        <div onclick="reloadStockTree()">
            <spring:message code="common.tree.reload"/>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        MXF.getTabContentHeight();
    });

    function clickStockTreeNode(node) {
        //$(this).tree('beginEdit',node.target);
        MXF.clearForm($('#stockForm'));
        $('#stockForm').form('load', node);
    }

    function updateStockTreeNode(node) {
        console.debug(node);
    }

    function appendStockTreeNode() {
        var t = $('#stockTree');
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

    function removeStockTreeNode() {
        var node = $('#stockTree').tree('getSelected');
        if (node && node.id != null) {
            MXF.confirm('<spring:message code="message.delete"/>?', function () {
                MXF.ajaxing(true);
                $.post('/stock/delete', {id: node.id}, function (data) {
                    MXF.ajaxing(false);
                    MXF.ajaxFormDone(data);
                    if (data.success) {
                        $('#stockTree').tree('remove', node.target);
                    }
                });
            });
        } else {
            $('#stockTree').tree('remove', node.target);
        }
    }

    function collapseStockTree() {
        var node = $('#stockTree').tree('getSelected');
        $('#stockTree').tree('collapse', node.target);
    }

    function expandStockTree() {
        var node = $('#stockTree').tree('getSelected');
        $('#stockTree').tree('expand', node.target);
    }

    function reloadStockTree() {
        $('#stockTree').tree('reload');
    }

</script>

